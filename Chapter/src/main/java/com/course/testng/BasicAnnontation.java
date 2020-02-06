package com.course.testng;


import org.testng.annotations.*;

public class BasicAnnontation {

    @Test(groups = "1")
    public void testCase1(){
        System.out.println("这是测试用例1");
    }
    @Test(groups = "2")
    public void testCase2(){
        System.out.println("这是测试用例2");
    }

    @BeforeMethod
    public void BeforeMethod(){
        System.out.println("BeforeMethod:在方法之前运行**********");
    }
    @AfterMethod
    public void AfterMethod(){
        System.out.println("AfterMethod:在方法之后运行***********");
    }
    @BeforeClass
    public void BeforeClass(){
        System.out.println("BeforeClass:在类之前运行====================");
    }
    @AfterClass
    public void AfterClass(){
        System.out.println("AfterClass:在类之后运行===================");
    }
    @BeforeSuite
    public void BeforeSuite(){
        System.out.println("BeforeSuite:在 之后运行===================");
    }
    @AfterSuite
    public void AfterSuite(){
        System.out.println("AfterSuite:在 之后运行===================");
    }
    @BeforeGroups
    public void BeforeGroups(){
        System.out.println("BeforeGroups:在 之后运行===================");
    }
    @AfterGroups
    public void AfterGroups(){
        System.out.println("AfterGroups:在 之后运行===================");
    }


}
