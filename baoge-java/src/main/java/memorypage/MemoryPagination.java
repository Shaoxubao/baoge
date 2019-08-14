package memorypage;

import java.util.List;

/**
 * @Description: 对列表进行内存分页
 */
public class MemoryPagination {

    /**
     * 当前页号
     */
    private Integer pageNum = 1;

    /**
     * 页面大小
     */
    private Integer pageSize = 10;

    /**
     * 总记录数
     */
    private Integer totalCount;

    /**
     * 当前页面记录数
     */
    private Integer sizes;

    /**
     * 页数
     */
    private Integer pages;

    /**
     * 截取的数据开始位置
     */
    private int startPos;

    /**
     * 截取数据结束位置
     */
    private int endPos;

    /**
     * 数据列表
     */
    private List<?> data;

    /**
     * 构造器
     *
     * @param pageNum 页号
     * @param pageSize 页面大小
     * @param dataList 需要分页的数据列表
     */
    public MemoryPagination(Integer pageNum, Integer pageSize, List<?> dataList) {
        super();
        this.data = dataList;
        this.totalCount = dataList.size();
        if (pageSize != null) {
            // 默认页面大小为10
            this.pageSize = pageSize > 0 ? pageSize : 10;
        }
        this.pages = (int) Math.ceil((double) totalCount / this.pageSize);
        if (pageNum != null) {
            // 默认第一页
            this.pageNum = pageNum > 0 ? ((pageNum > pages && pages > 0) ? pages : pageNum) : 1;
        }
        int remainingCount = this.totalCount - ((this.pageNum - 1) * this.pageSize);
        this.sizes = remainingCount > this.pageSize ? this.pageSize : remainingCount;
        // 将分页后得到的数据赋予data
        setData();
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public Integer getSizes() {
        return sizes;
    }

    public Integer getPages() {
        return pages;
    }

    public void setStartPos() {
        this.startPos = (pageNum - 1) * pageSize;
    }

    public void setEndPos() {
        this.endPos = (pageNum * pageSize) > totalCount ? totalCount : (pageNum * pageSize);
    }
    // 设置为私有防止外部直接调用
    private void setData() {
        setStartPos();
        setEndPos();
        this.data = data.subList(startPos, endPos);
    }

    public List<?> getData() {
        return data;
    }

}


