package entity.poi;

import lombok.Data;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

/**
 * word 文本内容参数
 */
@Data
public class WordText extends WordBase {

    /**
     * 内容
     */
    private String content;

    public WordText() {}

    public WordText(String content , ParagraphAlignment align , String fontFamily , int fontSize) {
        this.content = content;
        super.setAlign(align);
        super.setFontFamily(fontFamily);
        super.setFontSize(fontSize);
    }
}
