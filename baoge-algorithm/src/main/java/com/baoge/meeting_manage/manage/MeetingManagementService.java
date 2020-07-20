package com.baoge.meeting_manage.manage;

import java.util.Map;

/**
 * @Author shaoxubao
 * @Date 2020/7/20 16:22
 */
public interface MeetingManagementService {

    /**
     * 显示会议原内容
     */
    void showInputMeeting(Map<String, Integer> inputMeeting);

    /**
     * 根据输入会议内容调度出每天的安排
     */
    void schedule(Map<String, Integer> inputMeeting, int days);

    /**
     * 保存调度结果
     */
    void saveSchedule(Map<String, Integer> trackMap, boolean isForenoon);

    /**
     * 显示调度结果
     */
    void showSchedule();


}
