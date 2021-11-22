package education.cs.scu.mapper;

import education.cs.scu.entity.TaskBean;

import java.util.List;

public interface TaskMapper {

    int addTask(TaskBean taskBean) throws Exception;
}
