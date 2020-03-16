package com.tester.cases;

import com.tester.config.TestConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class FirstOpenListTest {

    @Test(groups = "getFirstOpenList", description = "获取首拆20条记录")
    public void getFirstOpenList() throws IOException {
        //获取返回结果
        String result = getJsonResult();
        System.out.println(TestConfig.firstOpenListurl);
        System.out.println(result);

        //将返回结果和预期结果对比
//        SqlSession sqlSession = DatabaseUtil.getSession();
//        List<LoginTest> expectedList = sqlSession.selectList("getopenfirstlistTest");
//        System.out.println(expectedList.toString());
        //Assert.assertEquals(expectedList.length(),resultJson.length());

    }

    private String getJsonResult() throws IOException {
        HttpPost post = new HttpPost(TestConfig.firstOpenListurl);
        post.addHeader("content-type","application/x-www-form-urlencoded; charset=utf-8");
        post.setHeader("Accept","application/json");
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //JSONArray jsonArray = new JSONArray(result);
//        JSONObject response1 = JSONObject.getNames(jsonArray);

        return result;
    }

}
