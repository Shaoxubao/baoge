package com.baoge;

import com.baoge.utils.ExcelUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelDemo {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        String sourceFilePath = "D:\\temp.xlsx";
        String targetFilePath = "D:\\temp1.xlsx";
//        ExcelUtils.fillData(sourceFilePath, 0, 0, 0, "hello",
//                targetFilePath);

//        String[] rowDataArr = {"world", "world", "world", "world", "world", "world", "world", "world"};
//        ExcelUtils.fillData2(sourceFilePath, 0, 1, 0, rowDataArr,
//                targetFilePath);

        sourceFilePath = "D:\\负荷.xlsx";
        Map<String, List<String>> rowDataMap = new LinkedHashMap<>();
        rowDataMap.put("8203300324", Arrays.asList("51.92", "51.98", "52.00", "52.12", "52.23", "52.23", "52.29", "52.11", "52.04", "51.92", "51.83", "51.72", "51.73", "51.83", "51.91", "51.84", "51.80", "51.74", "51.92", "52.03", "52.20", "52.16", "52.18", "52.03", "51.87", "51.77", "51.74", "51.68", "51.66", "51.79", "51.96", "51.92", "52.09", "52.03", "52.16", "52.09", "52.37", "52.33", "52.47", "52.40", "52.29", "52.17", "52.22", "52.12", "52.20", "52.34", "52.07", "52.09", "52.04", "52.02", "52.27", "52.32", "52.46", "52.62", "52.58", "52.50", "52.38", "52.40", "52.29", "52.35", "52.45", "52.45", "52.46", "52.56", "52.60", "52.58", "52.66", "52.87", "52.92", "52.72", "52.82", "52.60", "52.37", "52.33", "52.27", "52.20", "52.56", "52.47", "52.46", "52.40", "52.46", "52.66", "52.57", "52.90", "52.82", "52.73", "52.61", "52.55", "52.44", "52.37", "52.36", "52.40", "52.15", "52.48", "52.40", "52.26"));
        ExcelUtils.fillData3(sourceFilePath, 1, 1, 0, rowDataMap, 4,
                targetFilePath);
    }
}
