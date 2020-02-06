package com.course.testng.Suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class SuiteConfig {

    @BeforeSuite
    public void beforeSuit(){
        System.out.println("before  Suite 运行了");
    }
    @AfterSuite
    public void afterSuit(){
        System.out.println("after   Suite 运行了");
    }

    @BeforeTest
    public void BeforeTest(){
        System.out.println("BeforeTest  运行了");
    }
    @AfterTest
    public void AfterTest(){
        System.out.println("AfterTest 运行了");
    }
}
