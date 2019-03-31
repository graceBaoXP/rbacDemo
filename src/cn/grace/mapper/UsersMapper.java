package cn.grace.mapper;

import cn.grace.pojo.Users;

public interface UsersMapper {

    Users selectUserByName(String username);
}
