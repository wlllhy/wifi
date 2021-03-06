package education.cs.scu.mapper.Impl;

import education.cs.scu.entity.ProbeUser;
import education.cs.scu.entity.User;
import education.cs.scu.mapper.ProbeUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.List;

public class ProbeUserMapperImpl implements ProbeUserMapper {
    @Autowired
    private RedisTemplate<String, ProbeUser> redisTemplate;
    private static final String PROBE_USER_KEY = "PROBE_USER";

    /**
     * 返回redis中所有的ProbeUser记录
     */
    @Override
    public List<ProbeUser> queryProbeUser(User user) throws Exception {
        return redisTemplate.opsForList().range(PROBE_USER_KEY, 0, redisTemplate.opsForList().size(PROBE_USER_KEY));
    }

    @Override
    public void setProbeUser(List<ProbeUser> probeUsers) throws Exception {

        redisTemplate.opsForList().leftPushAll(PROBE_USER_KEY, probeUsers);
        /* for (ProbeUser probeUser: probeUsers) {
            redisTemplate.opsForList().leftPush(PROBE_USER_KEY,probeUser);
        }*/

    }


}
