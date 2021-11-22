package education.cs.scu.service.impl;

import education.cs.scu.entity.TaskBean;
import education.cs.scu.mapper.TaskMapper;
import education.cs.scu.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public int addTask(TaskBean taskBean) throws Exception {
        return taskMapper.addTask(taskBean);
    }
}
