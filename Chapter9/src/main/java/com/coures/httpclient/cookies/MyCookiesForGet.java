package com.coures.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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

    @BeforeTest
    public void BeforeTest(){
//        ResourceBundle config = ResourceBundle.getBundle("resources.appplication");
            bundle = ResourceBundle.getBundle("application",Locale.CHINA);

        url = bundle.getString("test.url");
        System.out.println(url);
        System.out.println("888888888888888888888");



    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        //从配置文件中直接拼写测试的url
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;
        //测试逻辑代码书写
//       HttpClient client = getHttpClient(testUrl);




        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = getDefaultHttpClient(get);

        //获取cookies信息
        CookieStore store = client.getCookieStore();
        List<Cookie> Cookies = store.getCookies();

        for(Cookie cookie : Cookies){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookies nmae = " + name + "\n" + "cookies value = " + value);
        }


    }

    //获取 cookies 使用 DefaultHttpClient 方法
    private DefaultHttpClient getDefaultHttpClient(HttpGet get) throws IOException {
        String result;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "gbk");
        System.out.println(result);
        return client;
    }

    //直接获取返回信息
    private HttpClient getHttpClient(String testUrl) throws IOException {
        String result;HttpGet get = new HttpGet(testUrl);
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"gbk");
        System.out.println(result);
        return client;
    }


}
