package com.coures.httpclient.cookies;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {

    private  String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void BeforeTest(){
//        ResourceBundle config = ResourceBundle.getBundle("resources.appplication");
        bundle = ResourceBundle.getBundle("application",Locale.CHINA);
        url = bundle.getString("test.url");
        System.out.println(url);
        System.out.println("888888888888888888888");

    }

    /**
     * 方法1：使用 DefaultHttpClient 方法进行 测试
     * @throws IOException
     */
//    @Test
//    public void testGetCookies() throws IOException {
//        String result;
//        //从配置文件中直接拼写测试的url
//        String uri = bundle.getString("getCookies.uri");
//        String testUrl = this.url + uri;
//        //测试逻辑代码书写
////       HttpClient client = getHttpClient(testUrl);
//        HttpGet get = new HttpGet(testUrl);
//        DefaultHttpClient client = new DefaultHttpClient();
//        HttpResponse response = client.execute(get);
//        result = EntityUtils.toString(response.getEntity(), "gbk");
//        System.out.println(result);
//        //获取cookies信息
//        this.store = client.getCookieStore();
//        List<Cookie> Cookies = this.store.getCookies();
//        for(Cookie cookie : Cookies){
//            String name = cookie.getName();
//            String value = cookie.getValue();
//            System.out.println("cookies nmae = " + name + "\n" + "cookies value = " + value);
//        }
//    }
//    //获取 cookies 使用 DefaultHttpClient 方法
//    private DefaultHttpClient getDefaultHttpClient(HttpGet get) throws IOException {
//        String result;
//        DefaultHttpClient client = new DefaultHttpClient();
//        HttpResponse response = client.execute(get);
//        result = EntityUtils.toString(response.getEntity(), "gbk");
//        System.out.println(result);
//        return client;
//    }
//    //直接获取返回信息
//    private HttpClient getHttpClient(String testUrl) throws IOException {
//        String result;HttpGet get = new HttpGet(testUrl);
//        HttpClient client = new DefaultHttpClient();
//        HttpResponse response = client.execute(get);
//        result = EntityUtils.toString(response.getEntity(),"gbk");
//        System.out.println(result);
//        return client;
//    }
//
//    @Test(dependsOnMethods = {"testGetCookies"})
//    public void testGetWithCookies() throws IOException {
//        String  uri = bundle.getString("test.get.with.cookies");
//        String testUrl = this.url + uri;
//        System.out.println(testUrl + "===============");
//        HttpGet get = new HttpGet(testUrl);
//        DefaultHttpClient client = new DefaultHttpClient();
//        //设置cookies信息  需要依赖  testGetCookies的测试结果 进行返回 this.store
////        System.out.println(this.store  + "--------");
//        client.setCookieStore(this.store);
//        HttpResponse response = client.execute(get);
//        //获取响应的代码状态
//        int statusCode = response.getStatusLine().getStatusCode();
//        System.out.println("statusCode = " + statusCode);
//        //验证判断 返回代码返回
//        if(statusCode==200){
//            String result = EntityUtils.toString(response.getEntity(),"gbk");
//            System.out.println(result);
//        }
//    }


    /**
     * 方法二： 使用closeableHttpClient
     */
    @Test
    public void testgetWithCookesForCloseableHttpClient() throws IOException {
        String result;
        //从配置文件中直接拼写测试的url
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;
        //定义cookie
        this.store = new BasicCookieStore();
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(store).build();
        //逻辑代码
        HttpGet get = new HttpGet(testUrl);
        CloseableHttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        List<Cookie> cookies = store.getCookies();
        for(Cookie cookie:cookies){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("访问/getcookies接口成功，cookie name = "+name+", cookie value = "+value);
        }
        response.close();
        client.close();
    }

    /**
     * CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(this.store).build();
     *
     * @throws IOException
     */
    @Test(dependsOnMethods = {"testgetWithCookesForCloseableHttpClient"})
    public void testGetWithCookiesForClsoeableHttpClient() throws IOException {
        String result;
        String uri = bundle.getString("test.get.with.cookies");
        String testUrl = this.url + uri;
        //this.store 是 上面一个接口 返回的 cookies
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(this.store).build();
        HttpGet get = new HttpGet(testUrl);
        get.setHeader("Content-Type","application/json");
        CloseableHttpResponse response = client.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = "+ statusCode);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        if (statusCode==200){
            System.out.println(result);
        } else {
            System.out.println("访问/get/with/cookies接口失败");
        }
        response.close();
        client.close();

    }

}
