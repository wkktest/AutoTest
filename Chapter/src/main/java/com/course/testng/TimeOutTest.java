package com.course.testng;

import org.testng.annotations.Test;

public class TimeOutTest {

    @Test(timeOut = 3000) //毫秒
    public void testSuccess() throws InterruptedException {
        Thread.sleep(2000);
    }
    @Test(timeOut = 3000)
    public void testFail() throws InterruptedException {
        /**
         * 需要重新写个获取当前页面加载的时间 与 timeOut 进行对比，判断是否需要跳过，进行下一个测试
         */

            System.out.println("test");

    }




}
