package edu.cs.scu.dao;

import edu.cs.scu.bean.TaskBean;

import java.util.List;

public interface TaskDao {
    //查询总数
    int getTaskCount();

    // 查询所有任务信息
    List<TaskBean> getTaskInfo();

    //根据条件查询用户信息
    TaskBean getTaskById(Long id);

    void addTask(TaskBean taskBean);
}
