package algorithm.od202401;

import java.util.*;

/**
 * @Author: xubao_shao@163.com
 * @Description:
 * @Date 2024/5/3 19:06
 *
 * 报文重排序
 * 对报文进行重传和重排序是常用的可靠机制，重传缓存区内有一定数量的子报文，每个子报文在原始报文中的顺序已知，现在需要恢复出原始报文。
 * 输入描述
 * 输入第一行为N，表示子报文的个数，0<N<1000。
 * 输入第二行为N个子报文，以空格分开，子报文格式为字符串报文内容+后缀顺序索引，子报文内容由[a-z,A-Z]组成，后缀为整型值，表示顺序，顺序唯一。
 * 输出描述
 * 输出恢复出的原始报文。按照每个子报文的顺序的升序排序恢复出原始报文，顺序后缀需要从恢复出的报文中删除掉
 * 输入：
 * 4
 * roling3 stone4 like1 a2
 * 输出：
 * like a rolling stone
 * 说明
 * 4个子报文的内容分别为"rolling”，"stone”，"like”，"a"，顺序值分别为3，4，1，2，按照顺序值升序并删除顺序后缀，
 * 得到恢复的原始报文:"like a rolling stone“
 * 输入
 * 8
 * gifts6  and7  Exchanging1  all2  precious5  thins8  kinds3  of4
 * 输出
 * Exchanging  all  kinds  of  precious  gifts  and  things
 *
 */
public class MessageReSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = Integer.parseInt(scanner.nextLine());
            String[] aArr = new String[n];
            for (int i = 0; i < n; i++) {
                aArr[i] = scanner.next();
            }
            Map<Integer, String> map = new HashMap<>();
            for (int i = 0; i < aArr.length; i++) {
                String str = aArr[i];
                String strC = str.replaceAll("[0-9]", "");
                String strN = str.replaceAll("[A-Za-z]", "");
                int num = Integer.parseInt(strN);
                map.put(num, strC);
            }
            Set<Integer> integerSet = map.keySet();
            ArrayList<Integer> list = new ArrayList<>(integerSet);
            Collections.sort(list);
            for (int i = 0; i < list.size(); i++) {
                System.out.print(map.get(list.get(i)) + " ");
            }
        }
    }
}
