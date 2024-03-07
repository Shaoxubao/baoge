package algorithm.od202401;

import java.util.*;

/**
 一个XX产品行销总公司，只有一个boss，其有若干一级分销，一级分销又有若干二级分销，每个分销只有唯一的上级分销.
 规定，每个月，下级分销需要将自己的总收入 (自己的+下级上交的) 每满100元上交15元给自己的上级.
 现给出一组分销的关系，和每个分销的收入，请找出boss并计算出这个boss的收入。
 比如:
 收入100元上交15元;
 收入199元(99元不够100)上交15元;
 收入200元，上交30元。
 输入:
 分销关系和收入: [[分销id 上级分销的ld 收入，[分销id 上级分销的id 收入]，[分销id 级分销的id 收入]]
 分销ID范围 0…65535
 收入范围: 0…65535,单位元
 提示: 输入的数据只存在1个boss，不存在环路
 输出: [boss的ID，总收入]
 输入描述:
 第1行输入关系的总数量N
 第2行开始，输入关系信息，格式: 分销ID 上级分销ID 收入
 比如:
 5
 1 0 100
 2 0 199
 3 0 200
 4 0 200
 5 0 200
 输出: boss的ID 总收入
 比如:
 0 120
 说明：bossId为0，只有一层分销节点，那么boss提成为 1->15 2->15 3->30 4->30 5->30 和为120
 备注：
 给定的输入数据都是合法的，不存在环路，重复的
 题解：
 采用的是Map结构来进行实现:
 {
  "上级分销ID":[
    {"下级分销id":"收入"},
    {"下级分销id":"收入"}],
  "上级分销ID1":[
    {"下级分销id":"收入"},
    {"下级分销id":"收入"}],
 }
 把所有的有子节点上级分销ID的作为Map最外层的键，值该分销商ID对应的子分销商列表，列表中以键值对的形式存储了每个分销商的id和收入。
 例如输入：
 5
 1 0 199
 2 0 200
 3 1 300
 4 2 400
 5 2 300
 转换为对应的Map对象为：
 {
  0=[{1=199}, {2=200}],
  1=[{3=300}],
  2=[{4=400}, {5=300}]
 }
 1.查找boss节点：所有的子节点中不包含该分销商id的话，则为boss节点
 2.计算每个节点收入：整体以迭代的方式进行计算，方法参数为当前id和当前id的父id。
 3.推演：5:300 4:400 3:300 2:200+60+45=305 1:199+45=244 0:45+30=75
 */
public class BossIncome {
    // {"上级分销":[{"下级分销id":"收入"}，{"下级分销id":"收入"}]}
    static Map<Integer, List<Map<Integer, Integer>>> mapList = new HashMap<>();
    // 记录所有的父经销商Id
    static List<Integer> parentIdList = new ArrayList<>();
    // boss
    static int bossId;

    // boss最终提成
    static Integer bossSum = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < num; i++) {
            // id 上级id 收入
            String[] str = scanner.nextLine().split(" ");
            int curId = Integer.parseInt(str[0]);
            int parentId = Integer.parseInt(str[1]);
            int income = Integer.parseInt(str[2]);
            Map<Integer, Integer> map = new HashMap<>();
            map.put(curId, income);
            if (mapList.containsKey(parentId)) {
                mapList.get(parentId).add(map);
            } else {
                List<Map<Integer, Integer>> maps = new ArrayList<>();
                maps.add(map);
                mapList.put(parentId, maps);
                parentIdList.add(parentId);
            }
        }
        // 找出bossId
        bossId = findBossId();
//      System.out.println(mapList);
//		System.out.println(bossId);
        calc(bossId, bossId);
        System.out.println(bossId + " " + bossSum);
    }

    public static Integer findBossId() {
        Integer bossId = null;
        boolean flag = true;
        for (Integer pId : parentIdList) {
            for (Map.Entry<Integer, List<Map<Integer, Integer>>> entry : mapList.entrySet()) {
                List<Map<Integer, Integer>> child = entry.getValue();
                for (Map<Integer, Integer> map : child) {
                    if (map.containsKey(pId)) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                bossId = pId;
                break;
            }
        }
        return bossId;
    }

    public static Integer calc(int id, int parentId) {
        if (id != bossId) { // 计算下级(总收入=本金收入+提成)
            // 个人本金收入
            int selfIncome = 0;
            if (mapList.containsKey(parentId)) { // parentId=0, 0=[{1=199}, {2=200}]
                for (Map<Integer, Integer> item : mapList.get(parentId)) {
                    if (item.keySet().iterator().next() == id) {
                        selfIncome = item.get(id);
                        // System.out.println("本金->" + m.get(id));
                        break;
                    }
                }
            }
            // 个人提成
            if (mapList.containsKey(id)) { // 1=[{3=300}]
                for (Map<Integer, Integer> item : mapList.get(id)) {
                    selfIncome += calc(item.keySet().iterator().next(), id);
                }
            }
            // System.out.println(id + "->" + (selfIncome / 100) * 15);
            return (selfIncome / 100) * 15;
        } else { // 计算boss 0=[{1=199}, {2=200}]
            for (Map<Integer, Integer> item : mapList.get(bossId)) {
                int curId = item.keySet().iterator().next();
                // System.out.println("第一层：" + curId);
                bossSum += calc(curId, bossId);
            }
        }
        return 0;
    }
}
