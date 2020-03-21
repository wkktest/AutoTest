package com.coures.httpclient.cookies;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private  String url;
    private ResourceBundle bundle;
    private CookieStore store;
    @BeforeTest
    public void BeforeTest(){
        bundle = ResourceBundle.getBundle("application",Locale.CHINA);
        url = bundle.getString("test.url");
        System.out.println(url);
        System.out.println("888888888888888888888");
    }
    @Test
    public void testGetCookies() throws IOException {
        //定义 测试变量
        String result;
        String uriget = bundle.getString("getCookies.uri");
        String testUrl = this.url + uriget;
        // 获取cookies信息
        this.store = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(store).build();
        //测试逻辑
        HttpGet get = new HttpGet(testUrl);
        CloseableHttpResponse response3 = httpclient.execute(get);
        //打印返回结果
        result = EntityUtils.toString(response3.getEntity(), "gbk");
        System.out.println(result);
        //获取cookies信息
        List<Cookie> Cookies = store.getCookies();
        for(Cookie cookie : Cookies){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookies nmae = " + name + "\n" + "cookies value = " + value);
        }
    }
    /**
     *  post with cookies request
     *  depnedsonmethods: testgetcookes path:/getCookies
     *  CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(this.store).build();
     * @throws IOException
     */
    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostMethod() throws IOException {
        String uri = bundle.getString("test.post.with.cookies");
        //拼接最终的测试地址
        String testUrl = this.url + uri;
        //声明一个post的方法
        HttpPost post = new HttpPost(testUrl);
        //添加参数
        JSONObject param = new JSONObject();
        param.put("name", "huhansan");
        param.put("age", "18");
        //设置请求头信息,设置header
        post.setHeader("content-type", "application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        //声明一个client对象，用来进行方法的执行,并设置cookies信息
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(this.store).build();
        //执行post的方法并得到响应结果
        CloseableHttpResponse response = httpclient.execute(post);
        //就是判断返回结果是否符合预期
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = " + statusCode);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        if (statusCode == 200) {
            System.out.println(result);
        } else {
            System.out.println("访问/get/with/cookies接口失败");
        }
        //将返回的响应结果字符串转化为json对象
        JSONObject resultjson = new JSONObject(result);
        //获取到结果值
        String success = (String) resultjson.get("huhansan");
        System.out.println(success);
        Assert.assertEquals("success", success);
        System.out.println("+++++++++++++++++=========================================================================================++++++++++++++++++++++++++++");
    }
    /**
     * 不带cookies的 get请求
     * @throws IOException
     */
//    @Test
//    public void testGetCookies1() throws IOException {
//        String result;
//        //从配置文件中拼接测试的URL
//        String uri=bundle.getString("getCookies.uri");
//        String testUrl=this.url+uri;
//        // 获取cookies信息
//        this.store= new BasicCookieStore();
//        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(store).build();
//        //测试逻辑
//        HttpGet httpget=new HttpGet(testUrl);
//        CloseableHttpResponse response2 = httpclient.execute(httpget);
//        //打印返回值
//        result = EntityUtils.toString(response2.getEntity());
//        System.out.println(result);
//        //读取cookie信息
//        List<Cookie> cookielist = store.getCookies();
//        for(Cookie cookie: cookielist){
//            String name=cookie.getName();
//            String value=cookie.getValue();
//            System.out.println("cookie name =" + name);
//            System.out.println("Cookie value=" + value);
//        }
//        System.out.println("+++++++++++++++++=================================================2222222========================================++++++++++++++++++++++++++++");
//    }
//    @Test(dependsOnMethods = {"testGetCookies1"})
//    public void testPostMethod1() throws IOException {
//        String uri=bundle.getString("test.post.with.cookies");
//        //拼接最终的测试地址
//        String testUrl=this.url+uri;
//        //声明一个post的方法
//        HttpPost post=new HttpPost(testUrl);
//        //添加参数
//        JSONObject param = new JSONObject();
//        param.put("name","huhansan");
//        param.put("age","18");
//        //设置请求头信息,设置header
//        post.setHeader("content-type", "application/json");
//        //将参数信息添加到方法中
//        StringEntity entity = new StringEntity(param.toString(), "utf-8");
//        post.setEntity(entity);
//        //声明一个client对象，用来进行方法的执行,并设置cookies信息
//        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(this.store).build();
//        //执行post的方法并得到响应结果
//        CloseableHttpResponse response = httpclient.execute(post);
//        //就是判断返回结果是否符合预期
//        int statusCode = response.getStatusLine().getStatusCode();
//        System.out.println("statusCode = "+ statusCode);
//        String result=EntityUtils.toString(response.getEntity(),"utf-8");;
//        if (statusCode==200){
//            System.out.println(result);
//        } else {
//            System.out.println("访问/get/with/cookies接口失败");
//        }
//        //将返回的响应结果字符串转化为json对象
//        JSONObject resultjson = new JSONObject(result);
//        //获取到结果值
//        String success = (String) resultjson.get("huhansan");
//        System.out.println(success);
//        Assert.assertEquals("success", success);
//        System.out.println("+++++++++++++++++===========================================22222==============================================++++++++++++++++++++++++++++");
//
//    }

    /**
     * DefaultHttpClient 已弃用：
     * 带cookies的get请求
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
//
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
//
//   @Test(dependsOnMethods = {"testGetCookies"})
//    public void testPostWithCookiesDefaultHttpClient() throws IOException {
//        String uri = bundle.getString("test.post.with.cookies");
//        String testUrl = this.url + uri;
//        //声明一个Client对象，这个方法进行执行
////        CloseableHttpClient client= HttpClients.createDefault();
//        DefaultHttpClient client = new DefaultHttpClient();
//        //声明一个方法，这个方法就是post方法
//        HttpPost post = new HttpPost(testUrl);
//        //添加参数
//        JSONObject param = new JSONObject();
//        param.put("name","huhansan");
//        param.put("age","18");
//        //设置请求头信息  设置header
//        post.setHeader("Content-Type","application/json");
//
//        //将参数信息添加到方法中
//        StringEntity entity = new StringEntity(param.toString(),"utf-8");
//        post.setEntity(entity);
//        //声明一个对象进行相应结果的存储
//        String result;
//        //设置cookies信息
//        client.setCookieStore(this.store);
//
//        //执行post方法
//        HttpResponse response = client.execute(post);
//
//        //获取响应结果
//        int statusCode = response.getStatusLine().getStatusCode();
//        System.out.println("statusCode = " + statusCode);
//        result = EntityUtils.toString(response.getEntity(),"gbk");
//        System.out.println(result);
//        //处理结果，就是判断返回结果是否符合预期
//        //将返回的响应结果字符串转换为json字符串
//        JSONObject resultJson = new JSONObject(result);
//        //获取到结果值
//        String success = (String) resultJson.get("huhansan");
//        String status  = (String) resultJson.get("status");
//        //具体的判断返回结果的值
//        Assert.assertEquals("success",success);
//        Assert.assertEquals("1",status);
//        Assert.assertEquals(200,statusCode);
//
//
//
//    }
}
