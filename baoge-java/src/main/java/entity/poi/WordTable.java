package entity.poi;

import lombok.Data;

import java.util.List;

/**
 * word 表格内容参数
 */
@Data
public class WordTable extends WordBase {

    /**
     * 表格行高
     */
    private int rowHeight = 350;

    /**
     * 列宽
     */
    private int colWidth = 5000;

    /**
     * 表格内边距 - 顶部距离
     */
    private int top = 3;
    /**
     * 表格内边距 - 左边距离
     */
    private int left = 5;
    /**
     * 表格内边距 - 下边距离
     */
    private int bottom = 3;
    /**
     * 表格内边距 - 右边距离
     */
    private int right = 5;
    /**
     * 表头内容
     * 如：{"境内河流","境外河流","合计"}
     */
    private String[] header;
    /**
     * 表格内容
     * [{"境内河流1","境外河流1","合计1"},{"境内河流2","境外河流2","合计2"}]
     */
    private List<String[]> values;

    /**
     * 表格行数 = 表格内容 + 表头(1行)
     * @return
     */
    public int getRows(){
        return values.size() + 1;
    }

    /**
     * 表格列数
     * @return
     */
    public int getCols(){
        return header.length;
    }
}
