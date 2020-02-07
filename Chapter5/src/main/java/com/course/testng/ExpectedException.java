package com.course.testng;

import org.testng.annotations.Test;

public class ExpectedException {
    /**
     * 什么时候使用异常测试
     * 期望结果是 异常时，程序跑出异常
     * 结果是异常
     *
     */
    //失败的异常
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExpetion(){
        System.out.println("===========================");
    }

    //成功的异常
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExpetionSuccess(){
        System.out.println("===========================1");
        throw new RuntimeException();
    }
}
