package utils.toexcel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;

import java.io.FileOutputStream;

/**
 * @Author shaoxubao
 * @Date 2019/8/26 16:22
 */
public class ExcelReport2 {
    /**
     * 注意：同一行上的只能创建同一个行对象row  否则赋值的内容会被覆盖。
     */
    public static void main(String[] args) {
        //1.创建一个workbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //2.在建立的工作簿中添加一个sheet，对应Excel文件中的工作簿，并设置工作簿名称
        HSSFSheet sheet = wb.createSheet("我是工作簿左下角的名字（第一个）");

        //3.第一行2,3,4列合并（注意：从0开始开始，参数分别表示起始第2行，结束第4行，起始第2列，结束第4列，大家可以
        //在脑海中想象画出对应的矩形框）
        HSSFRow row1 = sheet.createRow(0);
        //第一个合并单元区域：
        //3.1为合并单元格赋值，实际就是为坐标（1,1）这个单元格赋值（工作簿中坐标是（2，2)）
        //经本人测试，只有合并区域左上角那个单元格赋值能成功，左下角、右下角、右上角都不行。
        //注意：这儿colum必须是 1 ：因为这是代码，是从0开始数的，Excel中对应的是2，以下同理
        row1.createCell(0).setCellValue("企业基本信息");

        HSSFRow row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("公司名称：");
        row2.createCell(1).setCellValue("正康以太（深圳）控股有限公司");

        HSSFRow row3 = sheet.createRow(2);
        row3.createCell(0).setCellValue("品牌/产品运营团队介绍");

        HSSFRow row4 = sheet.createRow(3);
        row4.createCell(0).setCellValue("团队主要合伙人及负责板块：");
        row4.createCell(1).setCellValue("线下团队负责人刘怀岗先生，快消品行业工作18年营销经验，对中国矿泉水行业有着深刻了解，曾任巴马丽琅矿泉水市场部经理、曾任恒大冰泉传统渠道中心总经理。" +
                        "电商渠道现委托天地精华线上运营团队代运营。天地精华是互联网矿泉水领先品牌,主营瓶（桶）装矿泉水生产、销售，集全国化水源地生态开发保护、天然矿泉水系列产品开发与生产、个性定制、水设备销售、矿主礼品卡，包装桶/瓶生产、云水源生活馆连锁店等从水源地直达用户水杯全产业链运营的专业化企业。");

        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream("d:/tmp/workbook1.xls");
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("写出成功！");
    }
}
