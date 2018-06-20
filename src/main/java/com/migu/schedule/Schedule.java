package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;
import com.migu.schedule.zhzh.ScheduleCore;
import com.migu.schedule.zhzh.TaskCore;

import java.util.List;

/*
*类名和方法不能修改
 */
public class Schedule {


    public int init() {
        ScheduleCore.getInstance().init();
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
        return ScheduleCore.getInstance().registerNode(nodeId);
    }

    public int unregisterNode(int nodeId) {
        return ScheduleCore.getInstance().unregisterNode(nodeId);
    }


    public int addTask(int taskId, int consumption) {
        return TaskCore.getInstance().addTask(taskId,consumption);
    }


    public int deleteTask(int taskId) {
        return TaskCore.getInstance().deleteTask(taskId);
    }


    public int scheduleTask(int threshold) {
        return TaskCore.getInstance().scheduleTask(threshold);
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        return TaskCore.getInstance().queryTaskStatus(tasks);
    }

}
