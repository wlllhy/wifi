package education.cs.scu.service;

import education.cs.scu.entity.TaskBean;
import org.springframework.stereotype.Service;


@Service
public interface TaskService {
    int addTask(TaskBean taskBean) throws Exception;
}
