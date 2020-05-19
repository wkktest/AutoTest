package com.course.cases;


import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import com.course.utils.FastD;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class AddUserTest {


    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口接口")
    public void addUser() throws IOException, InterruptedException {

        SqlSession session = DatabaseUtil.getSqlSession();
        AddUserCase addUserCase = session.selectOne("addUserCase",1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);

//        下边的代码为写完接口的测试代码
        String result = getResult(addUserCase);
        //查询用户看是否添加成功
        Thread.sleep(5000);
        User user = session.selectOne("addUser",addUserCase);
        //处理结果，就是判断返回结果是否符合预期
        Assert.assertEquals(addUserCase.getExpected(),result);
    }

    private String getResult(AddUserCase addUserCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject param = new JSONObject();
        param.put("id",FastD.getint(100,9));
        param.put("userName",addUserCase.getUserName());
        param.put("password",addUserCase.getPassword());
        param.put("sex",addUserCase.getSex());
        param.put("age",addUserCase.getAge());
        param.put("permission",addUserCase.getPermission());
        param.put("isDelete",addUserCase.getIsDelete());
        //设置请求头信息 设置header
        post.setHeader("Content-type", "application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        String result;
        //声明一个client对象，用来进行方法的执行,并设置cookies信息
        TestConfig.httpclient = HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();
        //执行post的方法并得到响应结果
        CloseableHttpResponse response = TestConfig.httpclient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        if(response.getStatusLine().getStatusCode() == 200){
            System.out.println(result);
        }else {
            System.out.println("请求错误：请检查数据：");
        }
        return result;

    }
}
