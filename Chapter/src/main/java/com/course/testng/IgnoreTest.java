package com.course.testng;


import org.testng.annotations.Test;

public class IgnoreTest {
    @Test
    public void  ignoreTest(){
        System.out.println("ignore 执行！");
    }
    @Test(enabled = false)//忽略执行
    public void  ignoreTest2(){
        System.out.println("ignore 执行！");
    }
    @Test(enabled = true)//忽略执行
    public void  ignoreTest3(){
        System.out.println("ignore 执行！");
    }
}
