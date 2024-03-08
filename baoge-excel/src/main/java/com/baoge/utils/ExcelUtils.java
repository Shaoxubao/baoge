package com.baoge.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtils {

    /**
     * 指定行列填充数据
     * @param sourceFilePath 文件路径
     * @param rowNum  行列分别从0开始
     * @param cellNum 列号
     * @param sheetIndex 工作区索引
     * @param data 填充的数据
     * @param targetFilePath 保存目标文件路径
     */
    public static void fillData(String sourceFilePath, int rowNum, int cellNum, int sheetIndex,
                                String data, String targetFilePath) throws IOException, InvalidFormatException {
        // 获取Excel模板文件
        File file = new File(sourceFilePath);
        // 读取Excel模板
        XSSFWorkbook wb = new XSSFWorkbook(file);
        // 读取了模板内sheet的内容
        XSSFSheet sheet = wb.getSheetAt(sheetIndex);
        // 在相应的单元格进行（读取）赋值 行列分别从0开始
        XSSFCell cell = sheet.getRow(rowNum).getCell(cellNum);
        cell.setCellValue(data);
        // 修改模板内容导出新模板
        FileOutputStream out = new FileOutputStream(targetFilePath);
        // 关闭相应的流
        wb.write(out);
        out.close();
        wb.close();
    }

    /**
     * 指定行列填充数据(从上往下填充，列固定)
     * @param sourceFilePath 文件路径
     * @param rowNum  行列分别从0开始
     * @param cellNum 列号
     * @param sheetIndex 工作区索引
     * @param rowDataArr 填充的数据
     * @param targetFilePath 保存目标文件路径
     */
    public static void fillData2(String sourceFilePath, int rowNum, int cellNum, int sheetIndex,
                                 String[] rowDataArr, String targetFilePath) throws IOException, InvalidFormatException {
        // 获取Excel模板文件
        File file = new File(sourceFilePath);
        // 读取Excel模板
        XSSFWorkbook wb = new XSSFWorkbook(file);
        // 读取了模板内sheet的内容
        XSSFSheet sheet = wb.getSheetAt(sheetIndex);
        // 在相应的单元格进行（读取）赋值 行列分别从0开始
        int rowSize = rowDataArr.length;
        int index = 0;
        for (int i = rowNum; i < rowSize; i++) {
            XSSFCell cell = sheet.getRow(i).getCell(cellNum);
            cell.setCellValue(rowDataArr[index++]);
            CellStyle cellStyle = wb.createCellStyle();
            Font font = wb.createFont();
            font.setFontName("宋体");
            font.setFontHeightInPoints((short) 12); // 字体大小
            font.setColor(IndexedColors.RED.index);
            cellStyle.setFont(font);
            cell.setCellStyle(cellStyle);
        }
        // 修改模板内容导出新模板
        FileOutputStream out = new FileOutputStream(targetFilePath);
        // 关闭相应的流
        wb.write(out);
        out.close();
        wb.close();
    }

}
