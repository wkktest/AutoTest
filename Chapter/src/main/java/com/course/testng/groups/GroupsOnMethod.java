package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {

    @Test(groups = "a")
    public void test1(){
        System.out.println("groups1  运行");
    }
    @Test(groups = "client")
    public void test2(){
        System.out.println("groups2  运行");
    }
    @BeforeGroups("a")
   public void beforeGroupsServer(){
        System.out.println("=====================");
   }
   @AfterGroups("a")
   public void afterGroupsServer(){
        System.out.println("*********************");
   }


    //    @Test(groups = "client")
//    public void test3(){
//        System.out.println("groups3  运行");
//    }
//    @Test(groups = "client")
//    public void test4(){
//        System.out.println("groups4  运行");
//    }


}
