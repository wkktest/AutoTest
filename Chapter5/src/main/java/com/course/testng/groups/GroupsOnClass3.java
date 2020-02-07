package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

@Test(groups = "testgroup3")
public class GroupsOnClass3 {

    @Test(groups = "server")
    public void test1(){
        System.out.println("GroupsOnClass3 这是服务端组的测试方法111");
    }

    @Test(groups = "server")
    public void test2(){
        System.out.println("GroupsOnClass3 这是服务端组的测试方法222");
    }

    @Test(groups = "client")
    public void test3(){
        System.out.println("GroupsOnClass3 这是客户端组的测试方法333");
    }

    @Test(groups = "client")
    public void test4(){
        System.out.println("GroupsOnClass3 这是客户端组的测试方法444");
    }

    @BeforeGroups("server")
    public void beforeGroupsOnServer(){
        System.out.println("GroupsOnClass3 这是服务端组运行之前运行的方法!!!!");
    }

    @AfterGroups("server")
    public void afterGroupsOnServer(){
        System.out.println("GroupsOnClass3 这是服务端组运行之后运行的方法!!!!");
    }

    @BeforeGroups("client")
    public void beforeGroupsOnClient(){
        System.out.println("GroupsOnClass3 这是客户端组运行之前运行的方法!!!!");
    }

    @AfterGroups("client")
    public void afterGroupsOnClient(){
        System.out.println("GroupsOnClass3 这是客户端组运行之后运行的方法!!!!");
    }
}




