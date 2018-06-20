package com.migu.schedule.zhzh;

import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;
import com.migu.schedule.info.TaskInfoDetail;

import java.util.*;

/**
 *
 *
 * @author zhangzhen
 * @version C10 2018年06月20日 
 * @since SDP V300R003C10
 */
public class TaskCore
{
    private static TaskCore taskCore = new TaskCore();
    public static TaskCore getInstance()
    {
        return taskCore;
    }

    private static HashMap<Integer,TaskInfoDetail> taskPool = new HashMap<>();
    private static Queue taskQueue = new LinkedList<TaskInfoDetail>();

    public void init()
    {
        taskPool.clear();
        taskQueue.clear();
    }

    public void offerTaskQueue(TaskInfoDetail taskInfoDetail)
    {
        taskQueue.offer(taskInfoDetail);
    }

    public void removeTaskQueue(int taskId)
    {
        taskQueue.remove(taskId);
    }

    public int addTask(int taskId, int consumption) {
        if(taskId<=0)
        {
            return ReturnCodeKeys.E009;
        }
        if(null == taskPool.get(taskId))
        {
            taskPool.put(taskId,new TaskInfoDetail(taskId,consumption,-1));
            taskQueue.offer(new TaskInfoDetail(taskId,consumption,-1));
            return ReturnCodeKeys.E008;
        }
        return ReturnCodeKeys.E010;
    }

    public int deleteTask(int taskId) {
        if(taskId<=0)
        {
            return ReturnCodeKeys.E009;
        }
        TaskInfoDetail taskInfoDetail = taskPool.get(taskId);
        if(null == taskInfoDetail)
        {
            return ReturnCodeKeys.E012;
        }
        if(-1==taskInfoDetail.getNodeId())
        {
            taskQueue.remove(taskId);
        }
        else
        {
            ScheduleCore.getInstance().removeTaskFromServerNode(taskInfoDetail);
        }
        taskPool.remove(taskId);
        return ReturnCodeKeys.E011;
    }


    public int scheduleTask(int threshold) {
        if(threshold<=0)
        {
            return ReturnCodeKeys.E002;
        }
        if(taskPool.size()==0)
        {
            return ReturnCodeKeys.E014;
        }

        Map serverNode = ScheduleCore.getInstance().getServerNode();
        int nodeCount = serverNode.size();
        int consumptions = 0;

        List<TaskInfoDetail> taskInfoDetailList = new ArrayList<>();
        for(TaskInfoDetail taskInfoDetail:taskPool.values())
        {
            consumptions = consumptions + taskInfoDetail.getConsumption();
            taskInfoDetailList.add(taskInfoDetail);
        }
        int avgconsumption = consumptions/nodeCount;
        List<List<Integer>> tempNode = new ArrayList<>();
        for(int i=0;i<nodeCount;i++)
        {
            List<Integer> tempTasks = new ArrayList<>();

        }


        return ReturnCodeKeys.E013;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        if(null==tasks)
        {
            return ReturnCodeKeys.E016;
        }
        tasks.clear();
        for(TaskInfoDetail taskInfoDetail:taskPool.values())
        {
            TaskInfo taskInfo = new TaskInfo();
            taskInfo.setNodeId(taskInfoDetail.getNodeId());
            taskInfo.setTaskId(taskInfoDetail.getTaskId());
            tasks.add(taskInfo);
        }

        return ReturnCodeKeys.E015;
    }


}
