package education.cs.scu.mapper;

import education.cs.scu.entity.User;

public interface UserMapper {
    User doUserLogin(User user) throws Exception;
    int updateVerifyCode(User user) throws Exception;
    void doUserRegist(User user) throws Exception;
}
