package education.cs.scu.service.impl;

import education.cs.scu.entity.UserVisitTimeBean;
import education.cs.scu.mapper.UserVisitTimeMapper;
import education.cs.scu.service.UserVisitTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service("userVisitTimeService")
public class UserVisitTimeServiceImpl implements UserVisitTimeService {

    @Autowired
    private UserVisitTimeMapper userVisitTimeMapper;

    public String getFirstVisitTIme(int shopId, String mac) {
        String firstTime = null;
        try {
            firstTime = userVisitTimeMapper.getFirstVisitTIme(shopId, mac);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        
        return firstTime;
    }

    public List<UserVisitTimeBean> getUserVisitTime(int firstLine, int secondLine) {
        return userVisitTimeMapper.getUserVisitTime(firstLine,secondLine);
    }
}
