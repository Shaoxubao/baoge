package entity.poi;

import lombok.Data;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

/**
 * word 基础参数
 */
@Data
public class WordBase {

    /**
     * 对齐方式
     */
    private ParagraphAlignment align = ParagraphAlignment.LEFT;
    /**
     * 字体样式
     */
    private String fontFamily = "仿宋";
    /**
     * 字体大小
     */
    int fontSize = 11;
}
