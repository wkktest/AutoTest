package com.course.testng.Paramter;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {

    @Test(dataProvider = "data")
    public void testDataProvider(String name,int age){
        System.out.println("name =" + name + "; age=" + age);
    }

    @DataProvider(name="data")
    public Object[][] providerData(){
        Object[][] o = new Object[][]{
                {"zs",100},
                {"zs1",110},
                {"zs2",120}
        };
        return o;
    }
    @Test(dataProvider = "methodData")
    public void test1(String name,int age){
        System.out.println("test1 方法 name =" + name + "; age=" + age);
    }
    @Test(dataProvider = "methodData")
    public void test2(String name,int age){
        System.out.println("test2 方法 name =" + name + "; age=" + age);
    }

    @DataProvider(name="methodData")
    public Object[][] methodDataTest(Method method){
        Object[][] result = null;
        if(method.getName().equals("test1")){
            result = new Object[][]{
                    {"z1111s",100},
            };
        }else if(method.getName().equals("test2")){
            result = new Object[][]{
                    {"z2222s1",110},
            };
        }
        return result;
    }
}
