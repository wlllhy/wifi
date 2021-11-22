package com.cs.scu.mapper;

import com.cs.scu.entity.User;

public interface UserMapper {
    User doUserLogin(User user) throws Exception;
    User doUserVerify(User user) throws Exception;
}
