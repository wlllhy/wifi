package education.cs.scu.service;


import education.cs.scu.entity.UserVisitTimeBean;

import java.util.List;


public interface UserVisitTimeService {
    List<UserVisitTimeBean> getUserVisitTime(int firstLine, int secondLine);

    String getFirstVisitTIme(int shopId, String mac);
}
