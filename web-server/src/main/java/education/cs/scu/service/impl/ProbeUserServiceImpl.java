package education.cs.scu.service.impl;

import education.cs.scu.entity.ProbeUser;
import education.cs.scu.entity.User;
import education.cs.scu.mapper.ProbeUserMapper;
import education.cs.scu.service.ProbeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProbeUserServiceImpl implements ProbeUserService{

    @Autowired
    private ProbeUserMapper probeUserMapper;

    @Override
    public List<ProbeUser> queryProbeUser(User user) throws Exception {
        return probeUserMapper.queryProbeUser(user);
    }

    @Override
    public void setProbeUser(List<ProbeUser> probeUser) throws Exception {
        probeUserMapper.setProbeUser(probeUser);
    }
}
