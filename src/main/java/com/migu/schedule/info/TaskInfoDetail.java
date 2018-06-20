package com.migu.schedule.info;

/**
 *
 *
 * @author zhangzhen
 * @version C10 2018年06月20日 
 * @since SDP V300R003C10
 */
public class TaskInfoDetail extends TaskInfo
{
    private int consumption;

    public int getConsumption()
    {
        return consumption;
    }

    public void setConsumption(int consumption)
    {
        this.consumption = consumption;
    }

    public TaskInfoDetail(int taskId,int consumption,int nodeId)
    {
        this.setTaskId(taskId);
        this.setConsumption(consumption);
        this.setNodeId(nodeId);
    }
}
