package com.baoge.meeting_manage.manage;

import java.util.*;

/**
 * @Author shaoxubao
 * @Date 2020/7/20 16:28
 */
public class MeetingManagementServiceImpl implements MeetingManagementService {

    /**
     * 显示会议原内容
     */
    @Override
    public void showInputMeeting(Map<String, Integer> inputMeeting) {
        if (inputMeeting == null) {
            return;
        }

        for (Map.Entry<String, Integer> entry : inputMeeting.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + "min");
        }

        System.out.println("============================");
    }

    /**
     * 根据输入会议内容调度出每天的安排
     * 如：
     * 把所有的会议安排在两天(days)内，每天分为上午和下午，上午最多三小时（180min），9点到12点，下午最多四小时（240min），1点到5点。
     * 其实就是给定大小的4个坑，每个会议都是一个固定大小的萝卜，要把这所有的19个萝卜(输入会议内容)装到4个坑中，可以装不满，但萝卜不能有剩余。
     */
    @Override
    public void schedule(Map<String, Integer> inputMeeting, int days) {
        if (days <= 0) {
            return;
        }

        for (int i = 1; i <= days; i++) {
            Map<String, Integer> map_180 = selectMeeting(inputMeeting, 180);
            Map<String, Integer> map_240 = selectMeeting(inputMeeting, 240);

            System.out.println("Track " + i + ":");
            saveSchedule(map_180, true);
            saveSchedule(map_240, false);

            System.out.println("---------------------------");
        }
    }

    private Map<String, Integer> selectMeeting(Map<String, Integer> inputMeeting, int allocationTime) {
        Map<String, Integer> trackMap = new HashMap<>();

        List<String> titleList = new ArrayList<>(inputMeeting.keySet());
        Random random = new Random();
        int randomIndex = 0;
        String randomTitle = null;
        int time = 0;
        int sumTime = 0;
        while (sumTime < allocationTime && titleList.size() > 0) {
            randomIndex = random.nextInt(titleList.size());
            randomTitle = titleList.get(randomIndex);
            time = inputMeeting.get(randomTitle);
            sumTime += time;
            if (sumTime <= allocationTime) {
                trackMap.put(randomTitle, time);
                titleList.remove(randomTitle);
            }
        }

        // Remove entry from titleAndTime which has already schedule
        Set<String> trackMapKeySet = trackMap.keySet();
        Iterator<Map.Entry<String, Integer>> it = inputMeeting.entrySet().iterator();
        while (it.hasNext()) {
            if (trackMapKeySet.contains(it.next().getKey())) {
                it.remove();
            }
        }

        return trackMap;
    }

    /**
     * 保存调度结果
     */
    @Override
    public void saveSchedule(Map<String, Integer> trackMap, boolean isForenoon) {
        if (isForenoon) {
            int sumTime = 0;
            int res = 0;
            String remainderStr = "00";
            for (Map.Entry<String, Integer> entry : trackMap.entrySet()) {
                String title = entry.getKey();
                int time = entry.getValue();
                String timeStr = time == 5 ? "lightning" : time + "";
                switch (res) {
                    case 0:
                        System.out.println("09:" + remainderStr + "AM " + title + " " + timeStr + "min");
                        break;
                    case 1:
                        System.out.println("10:" + remainderStr + "AM " + title + " " + timeStr + "min");
                        break;
                    case 2:
                        System.out.println("11:" + remainderStr + "AM " + title + " " + timeStr + "min");
                        break;
                    default:
                        break;
                }
                sumTime += time;
                res = sumTime / 60;
                int remainder = sumTime % 60;
                if (remainder / 10 == 0) {
                    remainderStr = "0" + remainder;
                } else {
                    remainderStr = remainder + "";
                }
            }
            System.out.println("12:00PM Lunch");
        } else {
            int sumTime = 0;
            int res = 0;
            String remainderStr = "00";
            for (Map.Entry<String, Integer> entry : trackMap.entrySet()) {
                String title = entry.getKey();
                int time = entry.getValue();
                String timeStr = time == 5 ? "lightning" : time + "";
                switch (res) {
                    case 0:
                        System.out.println("01:" + remainderStr + "PM " + title + " " + timeStr + "min");
                        break;
                    case 1:
                        System.out.println("02:" + remainderStr + "PM " + title + " " + timeStr + "min");
                        break;
                    case 2:
                        System.out.println("03:" + remainderStr + "PM " + title + " " + timeStr + "min");
                        break;
                    case 3:
                        System.out.println("04:" + remainderStr + "PM " + title + " " + timeStr + "min");
                        break;
                    default:
                        break;
                }
                sumTime += time;
                res = sumTime / 60;
                int remainder = sumTime % 60;
                if (remainder / 10 == 0) {
                    remainderStr = "0" + remainder;
                } else {
                    remainderStr = remainder + "";
                }
            }
            System.out.println("05:00PM Networking Event");
        }
    }

    /**
     * 显示调度结果
     */
    @Override
    public void showSchedule() {

    }
}
