package entity.poi;

import lombok.Data;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

/**
 * word 图片内容参数
 */
@Data
public class WordImg extends WordBase {

    /**
     * 图片路径数组
     * String[] imgs = {"D:\\bar.png","D:\\pie.png"};
     */
    private String[] imgs;
    /**
     * 图片宽，默认350
     */
    private int width = 350;
    /**
     * 图片高，默认180
     */
    private int height = 180;

    public WordImg() {}

    public WordImg(String[] imgs , ParagraphAlignment align , String fontFamily , int fontSize) {
        this.imgs = imgs;
        super.setAlign(align);
        super.setFontFamily(fontFamily);
        super.setFontSize(fontSize);
    }
}
