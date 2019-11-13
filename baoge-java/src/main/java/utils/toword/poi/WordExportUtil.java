package utils.toword.poi;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import entity.poi.WordImg;
import entity.poi.WordTable;
import entity.poi.WordText;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import utils.CommonUtil;

import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author shaoxubao
 * @Date 2019/8/26 11:21
 */
public class WordExportUtil {
    public static void main(String[] args) throws Exception, IOException {
        testExportDoc();
    }

    //测试组合list，生成word时，会根据list添加各种类型内容的顺序生成到word中
    private static void testExportDoc() {
        String content ="";
        ParagraphAlignment align = ParagraphAlignment.CENTER;
        String fontFamily = "仿宋";
        int fontSize = 13;
        String wordId = DateUtil.format(new Date(),"yyyyMMddHHmmssSSS") ;
        String targetPath = CommonUtil.getRootPath() + "\\src\\main\\resources\\static\\poi_template\\" + wordId + ".docx";
        try {
            long startTime = System.currentTimeMillis();

            ArrayList<Object> list = new ArrayList<>();

            WordText textParam = new WordText(content , align , fontFamily , fontSize);
            list.add(textParam);

            WordTable tableParam  = new WordTable();
            tableParam.setRowHeight(400);
            String[] header = {"名称","内容"};
            tableParam.setHeader(header);
            List<String[]> values = new ArrayList<>();
            String[] v1 = {"企业基本信息",""};
            String[] v2 = {"公司名称：","正康以太（深圳）控股有限公司"};
            String[] v3 = {"品牌/产品运营团队介绍", ""};
            String[] v4 = {"团队主要合伙人及负责板块：","线下团队负责人刘怀岗先生，快消品行业工作18年营销经验，对中国矿泉水行业有着深刻了解，曾任巴马丽琅矿泉水市场部经理、曾任恒大冰泉传统渠道中心总经理。\n" +
                    "电商渠道现委托天地精华线上运营团队代运营。天地精华是互联网矿泉水领先品牌,主营瓶（桶）装矿泉水生产、销售，集全国化水源地生态开发保护、天然矿泉水系列产品开发与生产、个性定制、水设备销售、矿主礼品卡，包装桶/瓶生产、云水源生活馆连锁店等从水源地直达用户水杯全产业链运营的专业化企业。"};
            String[] v5 = {"建议引入品牌/产品基本信息", ""};
            String[] v6 = {"品牌名称：", "以太"};

            values.add(v1);
            values.add(v2);
            values.add(v3);
            values.add(v4);
            values.add(v5);
            values.add(v6);
            tableParam.setValues(values);
            list.add(tableParam);

            WordText textParam2 = new WordText(content , align , fontFamily , fontSize);
            list.add(textParam2);

            exportDoc(list, targetPath);

            System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查路径
     * @param path
     * @throws Exception
     */
    private static void checkPath(String path) {
        if (!(path.contains(".doc") || path.contains(".docx"))) {
            throw new RuntimeException("生成word目标路径需包含.doc、.docx");
        }
        //检查文件目录，不存在则创建
        String mkPath = path.substring(0, path.lastIndexOf("\\"));
        File file = new File(mkPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    /**
     * 导出 word （包含文本、图片、表格几种类型，需自己组合list）
     * 生成word时，会根据list添加各种类型内容的顺序生成到word中
     * @param list 内容list = [WordText , WordImg , WordTable]
     * @param targetPath 生成文档目标路径
     * @throws Exception
     */
    public static void exportDoc(ArrayList<Object> list , String targetPath) throws Exception {
        // 检测路径
        checkPath(targetPath);
        // 组装word
        XWPFDocument doc = generatorDocument(list);
        // 生成word
        outPutWord(doc , targetPath);
    }

    /**
     * 导出文本类型 word
     * @param textParam
     * @param targetPath
     */
    public static void exportTextDoc(WordText textParam , String targetPath) {
        // 检测路径
        checkPath(targetPath);
        // 组装word
        XWPFDocument doc = new XWPFDocument();
        appendWordText(doc , textParam);
        // 生成word
        outPutWord(doc , targetPath);
    }

    /**
     * 导出图片类型 word
     * 图片导出请使用 WordXWPFDocument
     * @param imgParam
     * @param targetPath
     */
    public static void exportImgDoc(WordImg imgParam , String targetPath) {
        // 检测路径
        checkPath(targetPath);
        // 组装word
        WordXWPFDocument doc = new WordXWPFDocument();
        appendWordImg(doc , imgParam);
        // 生成word
        outPutWord(doc , targetPath);
    }

    /**
     * 导出表格类型 word
     * @param tableParam
     * @param targetPath
     */
    public static void exportTableDoc(WordTable tableParam , String targetPath) {
        // 检测路径
        checkPath(targetPath);
        // 组装word
        XWPFDocument doc = new XWPFDocument();
        appendWordTable(doc , tableParam);
        // 生成word
        outPutWord(doc , targetPath);
    }

    /**
     * 组装word
     * @param list
     * @return
     */
    private static XWPFDocument generatorDocument(ArrayList<Object> list) {
        WordXWPFDocument doc = new WordXWPFDocument();
        list.forEach( item -> {
            if (item instanceof WordText) {
                appendWordText(doc , (WordText)item);
            } else if (item instanceof WordImg) {
                appendWordImg(doc , (WordImg)item);
            } else if (item instanceof WordTable) {
                appendWordTable(doc , (WordTable)item);
            }
        });
        return doc;
    }

    /**
     * 向word中追加文本内容
     * @param doc XWPFDocument
     * @param textParam 文本数据模型
     */
    private static void appendWordText(XWPFDocument doc , WordText textParam) {
        //添加文本
        if (textParam != null && !StrUtil.isEmpty(textParam.getContent())) {
            XWPFParagraph para = doc.createParagraph();
            para.setAlignment(textParam.getAlign());//设置左对齐
            XWPFRun run = para.createRun();
            run.setFontFamily(textParam.getFontFamily());
            run.setFontSize(textParam.getFontSize());
            run.setText(textParam.getContent());
            doc.createParagraph();
        }
    }

    /**
     * 向word中追加图标内容
     * @param doc XWPFDocument
     * @param imgParam 图片数据模型
     */
    private static void appendWordImg(WordXWPFDocument doc , WordImg imgParam) {
        //添加图片
        if(imgParam != null && imgParam.getImgs() != null && imgParam.getImgs().length > 0) {
            XWPFParagraph para;
            XWPFRun run;
            String[] imgs = imgParam.getImgs();
            try {
                for(int i=0;i<imgs.length;i++) {
                    para = doc.createParagraph();
                    para.setAlignment(imgParam.getAlign());//设置对齐方式
                    run = para.createRun();
                    InputStream input = getImgInputStream(imgs[i]);
                    //InputStream input = new FileInputStream(imgs[i]);
                    String filename = imgs[i].indexOf("\\") > 0 ? imgs[i].substring(imgs[i].lastIndexOf("\\") + 1) : imgs[i].substring(imgs[i].lastIndexOf("/") + 1);
                    doc.addPictureData(input, CommonUtil.getDocumentImgType(imgs[i]));
                    doc.createPicture(doc.getAllPictures().size() - 1 , imgParam.getWidth() , imgParam.getHeight() , para);
                    //run.addPicture(input, getDocumentImgType(imgs[i]), filename, Units.toEMU(imgParam.getWidth()), Units.toEMU(imgParam.getHeight()));
                    input.close();
                    //图片下面创建一行文字，表示图片的名称
                    para = doc.createParagraph();
                    para.setAlignment(ParagraphAlignment.CENTER);//设置对齐方式
                    run = para.createRun();
                    run.setFontFamily(imgParam.getFontFamily());
                    run.setFontSize(11);
                    run.setText(filename);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            doc.createParagraph();
        }
    }

    /**
     * 向word中追加表格内容
     * @param doc XWPFDocument
     * @param tableParam 表格数据模型
     */
    private static void appendWordTable(XWPFDocument doc, WordTable tableParam) {
        //添加表格
        if (tableParam != null && tableParam.getHeader() != null && tableParam.getValues() != null) {
            XWPFParagraph para;
            XWPFRun run;
            XWPFTable table  = doc.createTable(tableParam.getRows(),tableParam.getCols());
            table.setCellMargins(tableParam.getTop(), tableParam.getLeft(), tableParam.getBottom(), tableParam.getRight());
            //table.addNewCol();//添加新列
            //table.createRow();//添加新行
            XWPFTableRow row;
            XWPFTableCell cell;
            CTTcPr cellPr;
            //循环行
            for (int j = 0; j < tableParam.getRows(); j++) {
                row = table.getRow(j);
                row.setHeight(tableParam.getRowHeight());
                //循环列
                for (int i = 0; i < tableParam.getCols(); i++) {
                    cell = row.getCell(i);
                    cellPr = cell.getCTTc().addNewTcPr();
                    cellPr.addNewTcW().setW(BigInteger.valueOf(tableParam.getColWidth()));
                    para = cell.getParagraphs().get(0);
                    para.setAlignment(tableParam.getAlign());
                    run = para.createRun();
                    run.setFontFamily(tableParam.getFontFamily());
                    run.setFontSize(tableParam.getFontSize());
                    if (j == 0) {//第一行输出标题
                        run.setBold(true);
                        run.setText(tableParam.getHeader()[i]);
                    }
                    else {//输出表格内容
                        run.setText(String.valueOf(tableParam.getValues().get(j -1)[i]));
                    }
                }
            }
            doc.createParagraph();
        }
    }

    /**
     * 输出成word
     * @param doc XWPFDocument
     * @param targetPath 生成文件目标地址
     */
    private static void outPutWord(XWPFDocument doc , String targetPath) {
        // 生成word
        try {
            OutputStream os = new FileOutputStream(targetPath);
            doc.write(os);
            if(os != null) {
                try {
                    os.close();
                    System.out.println("已生成word文件，文件地址：" + targetPath);
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    /**
     * 获取图片数据流
     * @param imgPath
     * @return
     * @throws Exception
     */
    private static InputStream getImgInputStream(String imgPath) throws Exception{
        if (imgPath.startsWith("http://")) {
            //new一个URL对象
            URL url = new URL(imgPath);
            //打开链接
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            return inStream;
            /*
            //可以使用 OkHttp3 下载网络图片
            final Request request = new Request.Builder()
                    .url(imgPath)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().byteStream();*/
        }else{
            return new FileInputStream(imgPath);
        }
    }
}
