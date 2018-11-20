package com.example.learn.social.weixin.api;

import lombok.Data;


@Data
public class WeixinUserInfo {
    /**
     * 普通用户的标识，对当前开发者帐号唯一
     */
    private String openid;
    /**
     * 普通用户昵称
     */
    private String nickname;
    /**
     * 语言
     */
    private String language;
    /**
     * 普通用户性别，1为男性，2为女性
     */
    private String sex;
    /**
     * 普通用户个人资料填写的省份
     */
    private String province;
    /**
     * 普通用户个人资料填写的城市
     */
    private String city;
    /**
     * 国家，如中国为CN
     */
    private String country;
    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
     */
    private String headimgurl;


    public String getHeadimgurl() {
        return headimgurl;
    }

    public String getOpenid() {
        return openid;
    }

    public String getNickname() {
        return nickname;
    }
}
