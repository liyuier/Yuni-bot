package com.yuier.yuni.common.bilibiliapi;

import com.yuier.yuni.common.bilibiliapi.dto.ApiData;
import com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo.UserCardInfo;
import com.yuier.yuni.common.bilibiliapi.utils.BiliReqUtil;
import com.yuier.yuni.common.enums.BiliAuthenticateMethodEnum;
import com.yuier.yuni.common.service.YuniHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Title: CallBilibili
 * @Author yuier
 * @Package com.yuier.yuni.common.utils
 * @Date 2024/5/16 0:09
 * @description: 调用B站接口的工具
 */
@Component
public class CallBilibili {

    @Autowired
    BiliReqUtil biliReqUtil;

    private String baseUrl = "https://api.bilibili.com/";

    @Value("${headers.cookies.bilibili}")
    private String cookie;

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

    public UserCardInfo getUserCard(Long mid) {
        return this.getUserCard(mid, true);
    }
}
