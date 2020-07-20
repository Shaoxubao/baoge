package com.baoge.meeting_manage;

import com.baoge.meeting_manage.manage.MeetingManagementService;
import com.baoge.meeting_manage.manage.MeetingManagementServiceImpl;
import com.baoge.utils.FileTools;

import java.util.Map;

/**
 * @Author shaoxubao
 * @Date 2020/7/20 15:58
 *
 * 会议管理启动类
 */
public class MeetingManagementStart {

    public static void main(String[] args) {

        Map<String, Integer> inputMeeting = FileTools.readMeetingInputToMap("E:\\workspace\\baoge\\baoge-algorithm\\src\\main\\resources\\meeting_input.txt");

        MeetingManagementService meetingManagementService = new MeetingManagementServiceImpl();
        meetingManagementService.showInputMeeting(inputMeeting);


        meetingManagementService.schedule(inputMeeting, 2);
    }

}
