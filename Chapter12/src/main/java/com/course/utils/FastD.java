package com.course.utils;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class FastD {


    public static void main(String[] args) {
        Random s = new Random();
        int a= s.nextInt(100);
        System.out.println(a+9);
    }

    /**
     * 随机返回 a 范围内的 c开始的数
     * @param f 范围
     * @param start 开始数
     * @return
     */
    public static int getint(int f,int start){
        Random s = new Random();
        int b = s.nextInt(f);
        return b+ start;
    }

    @Test
    public void testCase1(){
        // 添加日志
        Reporter.log("判断1是否等于1");
        Assert.assertEquals(1, 1);
    }

    @Test
    public void testCase2(){
        // 添加日志
        Reporter.log("判断1是否等于2");
        Assert.assertEquals(1, 2);
    }

    @Test
    public void logDemo(){
        // 抛出个异常看看啥样式
        throw new RuntimeException("超时啦，超时啦！！！");
    }

    @Test
    public void testCase3(){
        // 添加日志
        Reporter.log("判断name是否等于name");
        Assert.assertEquals("name", "name");
    }






}
