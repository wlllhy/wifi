package education.cs.scu.service.impl;

import education.cs.scu.component.QueryUsersShopInfo;
import education.cs.scu.entity.UserBean;
import education.cs.scu.entity.UserVisitBean;
import education.cs.scu.mapper.UserVisitMapper;
import education.cs.scu.service.UserVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userVisitService")
public class UserVisitServiceImpl implements UserVisitService {

    @Autowired
    UserVisitMapper userVisitMapper;

    @Autowired
    QueryUsersShopInfo queryUsersShopInfo;
    @Override
    public void addUserVisit(UserVisitBean userFlow) throws Exception {
        userVisitMapper.addUserVisit(userFlow);
    }

    @Override
    public List<UserVisitBean> queryUserVisit(List<Integer> shopIdlist) throws Exception {
        return userVisitMapper.queryUserVisit(shopIdlist);
    }

    @Override
    public List<UserBean> queryUserShop(List<Integer> shopIdlist) throws Exception {
        return userVisitMapper.queryUserShop(shopIdlist);
    }

    @Override
    public List<Integer> queryShopList(String userName) throws Exception {
        UserVisitBean userVisitBean = new UserVisitBean();
        userVisitBean.setTotalFlow(0);
        userVisitBean.setTime(0l);
        userVisitBean.setShopId(0);
        userVisitBean.setShallowVisitRate(0d);
        userVisitBean.setDeepVisitRate(0d);
        userVisitBean.setCheckInRate(0d);
        userVisitBean.setCheckInFlow(0);
        userVisitBean.setMmac("0");
        List<Integer> shopList =  queryUsersShopInfo.getShopId(userName);
        if (shopList == null) {
            return null;
        }
        if (shopList.size() > 0) {
            return shopList;
        } else{
            return null;
        }
    }

}
