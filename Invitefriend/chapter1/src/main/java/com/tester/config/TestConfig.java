package com.tester.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;


public class TestConfig {
    // 登录接口url
    public static String loginUrl;
    //代金券领取详情查询url
    public static String voucherqueryUrl;

    // 活动信息查询接口url
    public  static String isFirstOpenurl;
    // 首拆（同时创建一轮详情）url
    public static  String firstOpenurl;
    // 分享（用户分享成功后调用）url
    public static String shareurl;
    // 邀请记录url
    public static String listurl;
    // 打款记录url
    public static String receiveListurl;
    // 首拆20记录url
    public static String firstOpenListurl;
    // 领取url
    public static String receiveurl;
    // 开户领取url
    public static String openReceiveurl;

    public static CookieStore store;
    public static String mobile;
    public static DefaultHttpClient defaultHttpClient;
}
