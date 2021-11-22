package education.cs.scu.service;

import education.cs.scu.entity.UserBean;
import education.cs.scu.entity.UserVisitBean;

import java.util.List;


public interface UserVisitService {
    void addUserVisit(UserVisitBean userFlow) throws Exception;
    List<UserVisitBean>  queryUserVisit(List<Integer> shopIdlist) throws Exception;
    List<UserBean> queryUserShop(List<Integer> shopIdlist) throws Exception;

    List<Integer> queryShopList(String userName) throws Exception;
}
