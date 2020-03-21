package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@Api(value = "/", description = "这是post接口")
@RequestMapping("/v1")
public class MyPostMethod {

    //用来装cookies信息
    private static Cookie cookie;


    /**
     * 获取 cookies的  post 接口
     * @param response
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation( value = "登录接口，成功后 获取cookies", httpMethod = "POST")
    public String login(HttpServletResponse response, @RequestParam(value = "userName", required = true) String userName ,//reuired 决定是否是必输项
                       @RequestParam(value = "passWord", required = true) String password ){
        if(userName.equals("hb") && password.equals("123456")){
            cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "用户登录成功!";
        }
        return "用户名或密码错误!";
    }

    /**
     * 获取用户User类信息
     * @param request
     * @param u
     * @return
     */
    @RequestMapping(value = "/getUserList" , method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表 接口", httpMethod = "POST")
    public  String getUserList(HttpServletRequest request, @RequestBody User u){
        User user;
        //获取cookies
        Cookie[] cookies = request.getCookies();
        //验证cookies 是否合法
        if(Objects.isNull(cookies)){
            return "cookies is null";
        }
        for(Cookie  c : cookies){
            if(c.getName().equals("login") && c.getValue().equals("true")
                    && u.getUserName().equals("zs")
                    && u.getPassWord().equals("111")
                    ){
                 user =  new User();
                 user.setName("list");
                 user.setAge("12");
                 user.setSex("man");
                return user.toString();
            }
        }
        return "参数不合法 ，检查从新输入！";
    }
}
