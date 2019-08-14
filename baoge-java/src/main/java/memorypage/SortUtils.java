package memorypage;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: java排序
 */
public class SortUtils {

    private final static Logger logger = LoggerFactory.getLogger(SortUtils.class);

    /**
     * 排序字段
     */
    private static String field = "";

    /**
     * 升序或降序
     */
    private static String order = "";

    /**
     * 对List<Map>进行排序
     *
     * @param data 数据集合
     * @param field 排序字段
     * @param order 排序方式
     */
    public static void mapSort(List<Map<String, String>> data, String field, String order) {
        if (field == null || field.isEmpty()) {
            logger.info("排序字段为空");
            return;
        }
        if (data.size() == 0) {
            logger.info("排序列表为空");
        }
        SortUtils.field = field;
        SortUtils.order = order;
        // 调用Collections的sort方法实现排序，
        Collections.sort(data, new Comparator<Map>() {
            /**
             *  根据生命Map类型，重写compare方法
             *  其中 return v1.compareTo(v2)为升序
             *      return v2.compareTo(v1)为降序。
             */
            @Override
            public int compare(Map o1, Map o2) {
                String v1 = o1.get(SortUtils.field).toString().toLowerCase();
                String v2 = o2.get(SortUtils.field).toString().toLowerCase();
                if (SortUtils.order.equals("desc")) {
                    return v2.compareTo(v1);
                } else {
                    return v1.compareTo(v2);
                }
            }
        });
    }

}


