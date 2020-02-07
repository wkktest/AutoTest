package com.course.testng.multThread;


import org.testng.annotations.Test;

public class MultThreadOnAnnotion {
    /**
     * 注解 进行多线程 运行控制
     * invocationCount 线程数的数据
     * threadPoolSize  线程池的数据
     */
    @Test(invocationCount = 5,threadPoolSize = 5)
    public void test(){
        System.out.println(1);
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }
}
