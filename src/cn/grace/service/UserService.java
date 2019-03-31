package cn.grace.service;

import cn.grace.pojo.Users;

public interface UserService {

    Users userLogin(String username,String userpwd);
}
