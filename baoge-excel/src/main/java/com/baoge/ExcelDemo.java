package com.baoge;

import com.baoge.utils.ExcelUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public class ExcelDemo {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        String sourceFilePath = "D:\\temp.xlsx";
        String targetFilePath = "D:\\temp1.xlsx";
//        ExcelUtils.fillData(sourceFilePath, 0, 0, 0, "hello",
//                targetFilePath);

        String[] rowDataArr = {"world", "world", "world", "world", "world", "world", "world", "world"};
        ExcelUtils.fillData2(sourceFilePath, 0, 1, 0, rowDataArr,
                targetFilePath);
    }
}
