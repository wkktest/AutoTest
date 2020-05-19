package com.course.model;

import lombok.Data;

@Data
public class GetUserListCase {
    private int UserId;
    private String userName;
    private String age;
    private String sex;
    private String expected;
}
