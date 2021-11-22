package education.cs.scu.mapper;


import education.cs.scu.entity.UserVisitTimeBean;

import java.util.List;

public interface UserVisitTimeMapper {
    void addUserVisitTime(UserVisitTimeBean userVisitTimeBean);

    void addUserVisitTimeByBatch(List<UserVisitTimeBean> userVisitTimeBeanList);

    List<UserVisitTimeBean> getUserVisitTime(int firstLine, int secondLine);

    String getFirstVisitTIme(int shopId, String mac);
}
