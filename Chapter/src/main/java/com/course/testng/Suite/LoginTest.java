package com.course.testng.Suite;

import org.testng.annotations.Test;

public class LoginTest {
    @Test
    public void loginTaobBao(){
        System.out.println("淘宝登录 成功了");
    }
    @Test
    public void logoutTaobBao(){
        System.out.println("淘宝退出 成功了");
    }
}
