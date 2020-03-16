package com.tester.cases;

import com.tester.config.TestConfig;
import com.tester.model.InterfaceName;
import com.tester.model.LoginCase;
import com.tester.utils.ConfigFile;
import com.tester.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginTest {
    /*
    测试准备工作
    1.获取所有接口完整请求url
    2.获取httpcleient
     */
    @BeforeTest(groups = "loginOpen", description = "测试准备工作")
    public void beforeTest() throws IOException {
        TestConfig.defaultHttpClient = new DefaultHttpClient();

        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.isFirstOpenurl = ConfigFile.getUrl(InterfaceName.FIRSTOPEN);
        TestConfig.firstOpenListurl = ConfigFile.getUrl(InterfaceName.FIRSTOPEN);
        TestConfig.shareurl = ConfigFile.getUrl(InterfaceName.SHARE);
        TestConfig.listurl = ConfigFile.getUrl(InterfaceName.LIST);
        TestConfig.receiveListurl  = ConfigFile.getUrl(InterfaceName.RECEIVERLIST);
        TestConfig.firstOpenListurl = ConfigFile.getUrl(InterfaceName.FIRSTOPENLIST);
        TestConfig.receiveurl = ConfigFile.getUrl(InterfaceName.RECEIVER);
        TestConfig.openReceiveurl = ConfigFile.getUrl(InterfaceName.OPENRECEIVE);
        TestConfig.voucherqueryUrl = ConfigFile.getUrl(InterfaceName.VOUCHERQUERY);
    }

    @Test(groups = "loginOpen", description = "已开户用户登陆")
    public void loginOpen() throws IOException {
        SqlSession session = DatabaseUtil.getSession();
        LoginCase loginCase = session.selectOne("loginTest",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //发送请求，获取返回结果
        String result = getResult(loginCase);
        System.out.println(result);

        //将登录用户的手机号返回回去
    }

    @Test(groups = "loginFalse", description = "不存在用户登录")
    public void loginFalse() throws IOException {
        SqlSession session = DatabaseUtil.getSession();
        LoginCase loginCase = session.selectOne("loginTest",2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //发送请求，获取返回结果
        String result = getResult(loginCase);
        System.out.println(result);

        //将登录用户的手机号返回回去
    }

    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post = new HttpPost("http://192.168.25.12:4660/voucherTrade/report");
        String params = "mobile="+loginCase.getMobile();
        post.addHeader("content-type","application/x-www-form-urlencoded; charset=utf-8");
        post.setHeader("Accept", "application/json");
        StringEntity entity = new StringEntity(params,"utf-8");
        post.setEntity(entity);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        String regex = "data\":(.+?),";
        Matcher matcher = Pattern.compile(regex).matcher(result);
        while (matcher.find()){
            String ret = matcher.group(1);
            System.out.println("提取出来的值：" + ret);
        }

        return result;
    }


}
