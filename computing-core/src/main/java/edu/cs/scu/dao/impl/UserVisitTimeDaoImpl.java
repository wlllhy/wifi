package edu.cs.scu.dao.impl;

import edu.cs.scu.bean.UserVisitTimeBean;
import edu.cs.scu.common.constants.AnalysisConstants;
import edu.cs.scu.conf.JedisPoolManager;
import edu.cs.scu.dao.BaseDao;
import redis.clients.jedis.ShardedJedis;

import java.util.List;

public class UserVisitTimeDaoImpl extends BaseDao {

    @Override
    public void add(List<Object> objectList) {
        String key = null;
        ShardedJedis jedis = JedisPoolManager.getResource();

        for (Object o : objectList) {
            UserVisitTimeBean userVisitTimeBean = (UserVisitTimeBean) o;
            key = String.valueOf(userVisitTimeBean.getShopId()) + "||"
                + userVisitTimeBean.getMac();
            jedis.rpush(key, String.valueOf(userVisitTimeBean.getVisitTime()));
        }

        jedis.close();
    }

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public List<Object> get(List<String> keys) {
        return null;
    }

    public long getFirstVisitTime(int shopId, String mac) {
        ShardedJedis jedis = JedisPoolManager.getResource();
        String key = shopId + "||" + mac;
        String firstVisitTime = jedis.lindex(key, 0);
        jedis.close();
        if (firstVisitTime == null) {
            return AnalysisConstants.DEFAULT_FIRST_VISIT_TIME;
        } else {
            return Long.valueOf(firstVisitTime);
        }
    }
}
