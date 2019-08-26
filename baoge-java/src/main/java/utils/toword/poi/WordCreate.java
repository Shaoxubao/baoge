package utils.toword.poi;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * @Author shaoxubao
 * @Date 2019/8/26 14:59
 */
public class WordCreate {

    public static void createWord() {
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph p1 = doc.createParagraph();

        XWPFTable table = doc.createTable(5, 2);
        // CTTblBorders borders=table.getCTTbl().getTblPr().addNewTblBorders();
        CTTblPr tblPr = table.getCTTbl().getTblPr();
        tblPr.getTblW().setType(STTblWidth.DXA);
        tblPr.getTblW().setW(new BigInteger("9000"));

        // 设置上下左右四个方向的距离，可以将表格撑大
        table.setCellMargins(20, 20, 20, 20);

        // 表格
        List<XWPFTableCell> tableCells = table.getRow(0).getTableCells();

        XWPFTableCell cell = tableCells.get(0);
        XWPFParagraph newPara = new XWPFParagraph(cell.getCTTc().addNewP(), cell);
        XWPFRun run = newPara.createRun();
        /** 内容居中显示 **/
        newPara.setAlignment(ParagraphAlignment.LEFT);
        // run.getCTR().addNewRPr().addNewColor().setVal("FF0000");/**FF0000红色*/
        // run.setUnderline(UnderlinePatterns.THICK);
        run.setText("企业基本信息");

        tableCells = table.getRow(1).getTableCells();
        tableCells.get(0).setText("公司名称：");
        tableCells.get(1).setText("正康以太（深圳）控股有限公司");

        tableCells = table.getRow(2).getTableCells();
        tableCells.get(0).setText("品牌/产品运营团队介绍");

        tableCells = table.getRow(3).getTableCells();
        tableCells.get(0).setText("团队主要合伙人及负责板块：");
        tableCells.get(1).setText("线下团队负责人刘怀岗先生，快消品行业工作18年营销经验，对中国矿泉水行业有着深刻了解，曾任巴马丽琅矿泉水市场部经理、曾任恒大冰泉传统渠道中心总经理。" +
                "电商渠道现委托天地精华线上运营团队代运营。天地精华是互联网矿泉水领先品牌,主营瓶（桶）装矿泉水生产、销售，集全国化水源地生态开发保护、天然矿泉水系列产品开发与生产、个性定制、水设备销售、矿主礼品卡，包装桶/瓶生产、云水源生活馆连锁店等从水源地直达用户水杯全产业链运营的专业化企业。");

        // 设置字体对齐方式
        p1.setAlignment(ParagraphAlignment.CENTER);
        p1.setVerticalAlignment(TextAlignment.TOP);

        // 第一页要使用p1所定义的属性
        XWPFRun r1 = p1.createRun();

        // 设置字体是否加粗
        r1.setBold(true);
        r1.setFontSize(20);

        // 设置使用何种字体
        r1.setFontFamily("Courier");

        // 设置上下两行之间的间距
        r1.setTextPosition(20);
        r1.setText("标题");

        FileOutputStream out;
        try {
            out = new FileOutputStream("d:/tmp/word2007.docx");
            // 以下代码可进行文件下载
            // response.reset();
            // response.setContentType("application/x-msdownloadoctet-stream;charset=utf-8");
            // response.setHeader("Content-Disposition",
            // "attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8"));
            // OutputStream out = response.getOutputStream();
            // this.doc.write(out);
            // out.flush();

            doc.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("success");
    }

    public static void main(String[] args) {
        createWord();
    }

}
