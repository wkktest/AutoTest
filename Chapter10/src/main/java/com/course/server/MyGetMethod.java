package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是get接口")
public class  MyGetMethod {

    /**
     * 服务端返回 cookies的不带参数的get请求
     * @param  response
     * @return "恭喜你获得了getcookies";
     *  // HttpServerletRequest 装请求信息的类
     *  // HttpServerletResponse  装响应信息的类
     */
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法获取get的cookies", httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        Cookie cookies = new Cookie("login","true");
        response.addCookie(cookies);
        return "恭喜你获得了getcookies";
    }

    /**
     * 要求客户端携带Cookies 不带参数的get请求 访问
     */
    @RequestMapping(value = "/get/with/Cookies",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带Cookies 不带参数的get请求 访问", httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie [] cookies =  request.getCookies();
        if(Objects.isNull(cookies)){
            return "客户端必须 携带cookies 访问!";
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login")  && cookie.getValue().equals("true")){
                return "恭喜您 访问成功！";
            }
        }
        return "客户端必须 携带cookies 访问!";
    }
    /**
     * 携带 参数 cookies  进行访问的get请求
     *
     */
    @RequestMapping(value = "/get/with/param/cookies", method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get请求 url key=value&key=value", httpMethod = "GET")
    public Map<String,Integer> getcookiesList(@RequestParam Integer start,Integer end,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        //从数据库里取
        Map<String,Integer> myList = new HashMap<>();
        myList.put("xie",20);
        myList.put("xie1",201);
        myList.put("xie2",202);
        myList.put("xie3",203);
//        return myList;
        if(Objects.isNull(cookies)){
            throw new RuntimeException("请求cookies错误 ！ " );
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login") && cookie.getValue().equals("true")){
                if(start == 1 && end ==2 ){
                    return  myList;
                }else {
                    System.out.println("请求参数错误 :  " + "start :" + start + " end :" + end );
                    throw new RuntimeException("请求参数错误 :  " + "start :" + start + " end :" + end );
                }
            }
            throw new RuntimeException("cookies 传入错误" );
        }

        return myList;
    }



    /**
     * 开发一个需要携带参数才能访问的get请求
     * 方式一：url key=value&key=value
     * 模拟获取商品列表
     */
    @RequestMapping(value = "/get/with/param", method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get请求 url key=value&key=value", httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,Integer end){
        //从数据库里取
        Map<String,Integer> myList = new HashMap<>();
        myList.put("xie",20);
        myList.put("xie1",201);
        myList.put("xie1",201);
        myList.put("xie2",202);
        myList.put("xie3",203);
//        return myList;
        if(start == 1 && end ==2){
            return  myList;
        }else {
            System.out.println("请求参数错误 :  " + "start :" + start + " end :" + end );
            throw new RuntimeException("请求参数错误 :  " + "start :" + start + " end :" + end );
        }
    }

    /**
     * 开发一个需要携带参数才能访问的get请求
     * 方式二：
     * url: ip:port/get/with/param/10/20
     */
    @RequestMapping(value = "/get/with/param/{start}/{end}")
    @ApiOperation(value = "需要携带参数才能访问的get请求 url: ip:port/get/with/param/10/20", httpMethod = "GET")
    public  Map myGetList(@PathVariable Integer start,@PathVariable Integer end){
        //从数据库里取
        Map<String,Integer> myGeList = new HashMap<>();
        myGeList.put("xie",20);
        myGeList.put("xie1",201);
//        return myGeList;
        if(start == 1 && end ==2){
            return  myGeList;
        }else {
            System.out.println("请求参数错误 :  " + "start :" + start + " end :" + end );
            throw new RuntimeException("请求参数错误 :  " + "start :" + start + " end :" + end );
        }
    }


    @RequestMapping(value = "/get/with/param/cookies/{start}/{end}")
    @ApiOperation(value = "需要携带参数才能访问的get请求 url: ip:port/get/with/param/10/20", httpMethod = "GET")
    public  Map myGetListCookies(@PathVariable Integer start,@PathVariable Integer end,HttpServletRequest request){
        //从数据库里取
        Map<String,Integer> myGeListC = new HashMap<>();
        myGeListC.put("xie",20);
        myGeListC.put("xie1",201);
//        return myGeList;
        Cookie [] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            throw new RuntimeException("cookies is  null");
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login") && cookie.getValue().equals("trueq")){
                if(start == 1 && end == 2){
                    return  myGeListC;
                }else {
                    System.out.println("请求参数错误 :  " + "start :" + start + " end :" + end );
                    throw new RuntimeException("请求参数错误 :  " + "start :" + start + " end :" + end );
                }
            }
        }

        return myGeListC;
    }


}
