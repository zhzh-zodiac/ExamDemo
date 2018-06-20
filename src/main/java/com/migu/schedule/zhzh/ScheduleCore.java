package com.migu.schedule.zhzh;

import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;
import com.migu.schedule.info.TaskInfoDetail;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author zhangzhen
 * @version C10 2018年06月20日 
 * @since SDP V300R003C10
 */
public class ScheduleCore
{
    // 单例
    private static ScheduleCore scheduleCore = new ScheduleCore();
    public static ScheduleCore getInstance()
    {
        return scheduleCore;
    }

    private static HashMap<Integer,List<TaskInfoDetail>> serverNode= new HashMap<>();

    public Map getServerNode()
    {
        return serverNode;
    }

    public void removeTaskFromServerNode(TaskInfoDetail taskInfoDetail)
    {
        if(null == taskInfoDetail)
        {
            return;
        }
        int node = taskInfoDetail.getNodeId();
        int taskId = taskInfoDetail.getTaskId();
        List<TaskInfoDetail> taskInfos = serverNode.get(node);
        for(TaskInfoDetail taskInfo:taskInfos)
        {
            if(taskId==taskInfo.getTaskId())
            {
                taskInfos.remove(taskInfo);
            }
        }

    }

    public void init()
    {
        serverNode.clear();
        TaskCore.getInstance().init();

    }

    public int registerNode(int nodeId) {
        if(nodeId<=0)
        {
            return ReturnCodeKeys.E004;
        }
        if(null!= serverNode.get(nodeId))
        {
            return ReturnCodeKeys.E005;
        }
        serverNode.put(nodeId,new ArrayList<>());
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) {
        if(nodeId<=0)
        {
            return ReturnCodeKeys.E004;
        }
        List<TaskInfoDetail> taskInfos = serverNode.get(nodeId);
        if(null == taskInfos)
        {
            return ReturnCodeKeys.E007;
        }
        for(TaskInfoDetail taskInfo:taskInfos)
        {
            TaskCore.getInstance().offerTaskQueue(taskInfo);
        }
        serverNode.remove(nodeId);
        return ReturnCodeKeys.E006;
    }
}
