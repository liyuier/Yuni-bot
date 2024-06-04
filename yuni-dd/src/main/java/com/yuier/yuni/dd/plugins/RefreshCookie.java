package com.yuier.yuni.dd.plugins;

import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.bilibiliapi.dto.login.CookieInfo;
import com.yuier.yuni.common.bilibiliapi.dto.login.RefreshCookieRes;
import com.yuier.yuni.common.bilibiliapi.request.CallForCookie;
import com.yuier.yuni.common.detector.order.YuniOrderDefiner;
import com.yuier.yuni.common.detector.order.YuniOrderOptionalArg;
import com.yuier.yuni.common.detector.order.YuniOrderRequiredArg;
import com.yuier.yuni.common.detector.order.matchedout.OrderMatchedOut;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.domain.message.dto.SendMessageDto;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.plugin.interf.YuniOrderPlugin;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.utils.CallOneBot;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.dd.service.SubUperService;
import com.yuier.yuni.dd.service.UperFollowedService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @Title: RefreshCookie
 * @Author yuier
 * @Package com.yuier.yuni.dd.plugins
 * @Date 2024/6/2 3:26
 * @description: 刷新 cookie 相关插件
 */
@Slf4j
@Component
@Plugin(id = "RefreshCookie", listener = MessageTypeEnum.ALL)
public class RefreshCookie implements YuniOrderPlugin {
    @Autowired
    CallForCookie callForCookie;

    @Autowired
    SubUperService subUperService;
    @Autowired
    UperFollowedService uperFollowedService;

    @Autowired
    CallOneBot callOneBot;
    @Autowired
    MessageChainService messageChainService;

    private MessageEvent messageEvent;

    private static final String PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n" +
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLgd2OAkcGVtoE3ThUREbio0Eg\n" +
            "Uc/prcajMKXvkCKFCWhJYJcLkcM2DKKcSeFpD/j6Boy538YXnR6VhcuUJOhH2x71\n" +
            "nzPjfdTcqMz7djHum0qSZA0AyCBDABUqCrfNgCiJ00Ra7GmRj+YCK1NJEuewlb40\n" +
            "JNrRuoEUXpabUzGB8QIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    private String checkCookie = "checkCookie";
    @Override
    public YuniOrderDefiner detectorDefine() {
        return YuniOrderDefiner.build()
                .setOrderHead("cookie")
                .addOption(checkCookie, "-check");
    }

    @Override
    public ResponseResult<T> run(MessageEvent messageEvent, OrderMatchedOut order) {
        this.messageEvent = messageEvent;
        if (order.optionExists(checkCookie)) {
            checkOrRefreshCookie(true);
        }
        return ResponseResult.okResult();
    }

    private void checkOrRefreshCookie(Boolean flag) {
        CookieInfo cookieInfo = callForCookie.checkCookieInfo();
        if (null == cookieInfo) {
            callOneBot.sendMessage(new SendMessageDto(
                    messageEvent.getMessageEventPosition(),
                    messageChainService.buildChain("请手动更新 cookie ！")
            ));
        } else {
            if (cookieInfo.getRefresh() || flag) {
                Long timestamp = cookieInfo.getTimestamp();
                try {
                    String correspondPath = getCorrespondPath(String.format("refresh_%d", timestamp), PUBLIC_KEY);
                    String refreshCsrf = callForCookie.getRefreshCsrf(correspondPath);
                    if (null != refreshCsrf) {
                        RefreshCookieRes refreshCookieRes = callForCookie.refreshCookie(refreshCsrf);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 抄袭自 https://socialsisteryi.github.io/bilibili-API-collect/docs/login/cookie_refresh.html#java 的神秘代码
     * @param plaintext
     * @param publicKeyStr
     * @return
     * @throws Exception
     */
    public String getCorrespondPath(String plaintext, String publicKeyStr) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        publicKeyStr = publicKeyStr
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replace("\n", "")
                .trim();
        byte[] publicBytes = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicBytes);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        String algorithm = "RSA/ECB/OAEPPadding";
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // Encode the plaintext to bytes
        byte[] plaintextBytes = plaintext.getBytes("UTF-8");

        // Add OAEP padding to the plaintext bytes
        OAEPParameterSpec oaepParams = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey, oaepParams);
        // Encrypt the padded plaintext bytes
        byte[] encryptedBytes = cipher.doFinal(plaintextBytes);
        // Convert the encrypted bytes to a Base64-encoded string
        return new BigInteger(1, encryptedBytes).toString(16);
    }

    @Override
    public String description() {
        return "刷新 cookie 相关的插件";
    }
}
