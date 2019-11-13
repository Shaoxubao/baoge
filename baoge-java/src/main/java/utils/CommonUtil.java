package utils;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.ResourceUtils;

import java.io.File;

/**
 * 公用util
 */
public class CommonUtil {

    /**
     * 获取指定目录
     * @return
     */
    public static String getPathByCondition(String where) {
        String rootPath = getRootPath();
        String modulesPath = rootPath.substring(0 ,rootPath.indexOf(where)) + "\\" + where;
        return modulesPath;
    }

    /**
     * 获取应用根目录
     * @return
     */
    public static String getRootPath() {
        String rootPath = "";
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) path = new File("");
            rootPath = path.getAbsolutePath();
            //System.out.println("rootPath:" + rootPath);
            if (rootPath.contains("\\target\\classes")) {
                rootPath = rootPath.replace("\\target\\classes","");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rootPath;
    }

    /**
     * 获取图片对应的文档类型
     * @param picName
     * @return
     */
    public static int getDocumentImgType(String picName) {
        //bmp|jpg|jpeg|png|gif|psd|dwg
        int format = XWPFDocument.PICTURE_TYPE_PNG;
        if (picName.endsWith(".emf")) format = XWPFDocument.PICTURE_TYPE_EMF;
        else if (picName.endsWith(".wmf")) format = XWPFDocument.PICTURE_TYPE_WMF;
        else if (picName.endsWith(".pict")) format = XWPFDocument.PICTURE_TYPE_PICT;
        else if (picName.endsWith(".jpeg") || picName.endsWith(".jpg")) format = XWPFDocument.PICTURE_TYPE_JPEG;
        else if (picName.endsWith(".png")) format = XWPFDocument.PICTURE_TYPE_PNG;
        else if (picName.endsWith(".dib")) format = XWPFDocument.PICTURE_TYPE_DIB;
        else if (picName.endsWith(".gif")) format = XWPFDocument.PICTURE_TYPE_GIF;
        else if (picName.endsWith(".tiff")) format = XWPFDocument.PICTURE_TYPE_TIFF;
        else if (picName.endsWith(".eps")) format = XWPFDocument.PICTURE_TYPE_EPS;
        else if (picName.endsWith(".bmp")) format = XWPFDocument.PICTURE_TYPE_BMP;
        else if (picName.endsWith(".wpg")) format = XWPFDocument.PICTURE_TYPE_WPG;
        return format;
    }
}
