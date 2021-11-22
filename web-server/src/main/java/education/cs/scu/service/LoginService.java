package education.cs.scu.service;

import education.cs.scu.entity.User;


public interface LoginService {
    User doUserLogin(User user) throws Exception;
    String verifyCode(User user) throws Exception;
    int updateVerifyCode(User user) throws Exception;
    void doUserRegist(User user) throws Exception;
}
