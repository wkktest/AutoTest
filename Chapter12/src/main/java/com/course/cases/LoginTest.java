package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

public class LoginTest {

    @BeforeTest(groups = "loginTrue",description = "测试准备工作,获取HttpClient对象")
    public void beforeTest(){
        TestConfig.httpclient = HttpClients.createDefault();
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
    }

    @Test(groups = "loginTrue",description = "用户成功登陆接口")
    public void loginTrue() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //下边的代码为写完接口的测试代码
        String result = getResult(loginCase);
        //处理结果，就是判断返回结果是否符合预期
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    @Test(description = "用户登陆失败接口")
    public void loginFalse() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
//        下边的代码为写完接口的测试代码
        String result = getResult(loginCase);
//        //处理结果，就是判断返回结果是否符合预期
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        //添加参数
        JSONObject param = new JSONObject();
        param.put("userName",loginCase.getUserName());
        param.put("password",loginCase.getPassword());
        //设置请求头信息,设置header
        post.setHeader("content-type", "application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        //定义cook 存储cookies信息
        TestConfig.store = new BasicCookieStore();
        //声明一个client对象，用来进行方法的执行,并设置cookies信息
        TestConfig.httpclient = HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();
        //执行post的方法并得到响应结果
        CloseableHttpResponse response = TestConfig.httpclient.execute(post);
        //就是判断返回结果是否符合预期
        int statusCode = response.getStatusLine().getStatusCode();
        String result = EntityUtils.toString(response.getEntity(), "utf-8");

        if (statusCode == 200) {
            System.out.println(result);
        } else {
            System.out.println("访问/login 接口失败");
        }
        return result;
    }

    private String getResult123(LoginCase loginCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("userName",loginCase.getUserName());
        param.put("password",loginCase.getPassword());
        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        CloseableHttpResponse response = TestConfig.httpclient.execute(post);
//        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        return result;
    }

}
