package com.tester.cases;

import com.tester.config.TestConfig;
import com.tester.model.LoginCase;
import com.tester.model.VoucherQueryCase;
import com.tester.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class VoucherQueryTest {
    @Test(dependsOnGroups = "loginOpen",description = "查询券列表正常流")
    public void voucherQueryTrue() throws IOException {
        SqlSession session = DatabaseUtil.getSession();
        VoucherQueryCase voucherQueryCase = session.selectOne("VoucherQueryTest",1);
        System.out.println(voucherQueryCase.toString());
        System.out.println(TestConfig.voucherqueryUrl);
        System.out.println(TestConfig.store);

        //发送请求，获取返回结果
        JSONArray result = getJsonResult(voucherQueryCase);
        System.out.println(result);
    }






    private JSONArray getJsonResult(VoucherQueryCase voucherQueryCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.voucherqueryUrl);
        post.addHeader("content-type","application/x-www-form-urllencoded;charset=utf-8");
        post.setHeader("Accept","application/json");
        String params = "type="+voucherQueryCase.getType();
        StringEntity entity = new StringEntity(params,"utf-8");
        post.setEntity(entity);
        System.out.println(entity);
        BasicClientCookie cookie = new BasicClientCookie("mobile", TestConfig.mobile);
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();
        TestConfig.store.addCookie(cookie);
        System.out.println(cookie);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("返回结果为:"+result);
        List resultList = Arrays.asList(result);
        JSONArray array = new JSONArray(resultList);
        System.out.println(array.toString());
        return array;


    }


}
