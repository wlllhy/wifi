package com.cs.scu.service;

import com.cs.scu.entity.User;

public interface LoginService {
    User doUserLogin(User user) throws Exception;
    User doUserVerify(User user) throws Exception;
}
