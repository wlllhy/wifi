package education.cs.scu.service;

import education.cs.scu.entity.ProbeUser;
import education.cs.scu.entity.User;

import java.util.List;


public interface ProbeUserService {
    List<ProbeUser> queryProbeUser(User user) throws Exception;
    void setProbeUser(List<ProbeUser> probeUser) throws Exception;
}
