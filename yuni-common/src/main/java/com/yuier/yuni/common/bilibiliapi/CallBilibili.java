package com.yuier.yuni.common.bilibiliapi;

import com.yuier.yuni.common.bilibiliapi.dto.ApiData;
import com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo.UserCardInfo;
import com.yuier.yuni.common.bilibiliapi.dto.user.relation.UpSelfRelation;
import com.yuier.yuni.common.bilibiliapi.utils.BiliReqUtil;
import com.yuier.yuni.common.enums.BiliAuthenticateMethodEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: CallBilibili
 * @Author yuier
 * @Package com.yuier.yuni.common.utils
 * @Date 2024/5/16 0:09
 * @description: 调用B站接口的工具
 */
@Slf4j
@Component
public class CallBilibili {

    @Autowired
    BiliReqUtil biliReqUtil;

    private String baseUrl = "https://api.bilibili.com/";

    @Value("${headers.cookies.bilibili}")
    private String cookie;

    /**
     * 获取用户信息
     * @param mid
     * @param getSpacePhoto 是否获取用户主页大图
     * @return
     */
    public UserCardInfo getUserCard(Long mid, Boolean getSpacePhoto) {
        HashMap<String, String> params = new HashMap<>();
        params.put("mid", mid.toString());
        params.put("photo", getSpacePhoto.toString());
        ParameterizedTypeReference<ApiData<UserCardInfo>> typeReference = new ParameterizedTypeReference<>() {};
        ApiData<UserCardInfo> apiData = biliReqUtil.getBiliApiForData(
                baseUrl + "x/web-interface/card",
                params,
                BiliAuthenticateMethodEnum.COOKIE,
                typeReference
        );
        if (apiData.getCode() != 0) {
            return null;
        }
        return apiData.getData();
    }

    /**
     * 查询用户与自己的关系
     * @param fid
     * @return
     */
    public UpSelfRelation getUpSelfRelation(Long fid) {
        HashMap<String, String> params = new HashMap<>();
        params.put("fid", fid.toString());
        ParameterizedTypeReference<ApiData<UpSelfRelation>> typeReference = new ParameterizedTypeReference<>() {};
        ApiData<UpSelfRelation> apiData = biliReqUtil.getBiliApiForData(
                baseUrl + "x/relation",
                params,
                BiliAuthenticateMethodEnum.COOKIE,
                typeReference
        );
        if (apiData.getCode() != 0) {
            return null;
        }
        return apiData.getData();
    }

    /**
     * 关注用户
     * @param fid
     * @return
     */
    public Boolean followUp(Long fid) {
        HashMap<String, String> params = new HashMap<>();
        params.put("fid", fid.toString());
        params.put("act", String.valueOf(1));  // 关注操作码
        params.put("re_src", String.valueOf(11));  // 关注来源空间
        String csrf = "";
        Matcher matcher = Pattern.compile("bili_jct=(\\w+)").matcher(cookie);
        if (matcher.find()) {
            csrf = matcher.group(1);
        } else {
            log.info("Cookie 中没有 bili_jct 字段，请补齐！");
            return false;
        }
        params.put("csrf", csrf);
        ParameterizedTypeReference<ApiData> typeReference = new ParameterizedTypeReference<ApiData>() {};
        ApiData apiData = biliReqUtil.postBiliApi(
                baseUrl + "x/relation/modify",
                params,
                BiliAuthenticateMethodEnum.COOKIE,
                typeReference
        );
        if (apiData.getCode() != 0) {
            log.info(apiData.getCode().toString());
            return false;
        }
        return true;
    }

    public UserCardInfo getUserCard(Long mid) {
        return this.getUserCard(mid, true);
    }
}
