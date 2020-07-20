package com.baoge.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author shaoxubao
 * @Date 2020/7/20 16:04
 */
public class FileTools {

    /**
     * 返回map的key 为会议名称，value 为所用时间
     */
    public static Map<String, Integer> readMeetingInputToMap(String path) {
        Map<String, Integer> titleAndTimeMap = new HashMap<>();
        File file = new File(path);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));

            String line = null;
            Integer itemTime = null; // 每个会议对应的时间
            while ((line = reader.readLine()) != null) {
                int lastBlank = line.lastIndexOf(" ");
                String title = line.substring(0, lastBlank);
                String time = line.substring(lastBlank + 1);
                if (StringUtils.isBlank(title) || StringUtils.isBlank(time)) {
                    continue;
                }

                if (time.equals("lightning")) {
                    itemTime = 5;
                } else {
                    // Remove "min" suffix
                    itemTime = Integer.parseInt(time.substring(0, time.length() - 3));
                }

                titleAndTimeMap.put(title, itemTime);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return titleAndTimeMap;
    }

    public static List<String> readFile(String filename) {
        List<String> list = new ArrayList<>();
        InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(filename);
        if (null == inputStream) {
            System.out.println("未找到文件");
            return null;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String lineStr;
        try {
            while (null != (lineStr = br.readLine())) {
                list.add(lineStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

}
