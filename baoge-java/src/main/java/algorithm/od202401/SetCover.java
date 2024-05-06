package algorithm.od202401;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 集合覆盖问题
 * 假设你办了个广播节目，要让全美50个州的听众都能收听到。为此，你需要决定在哪些广播台播出这个节目。在每个广播台播出都需要支付费用，
 * 所以要在尽可能少的广播台播出。现有广播台名单（部分）如下所示：
 * 广播名称        覆盖的州
 * KONG           ID、NV、UT
 * KTWO           WA、ID、MT
 * KTHREE         OR、NV、CA
 * KFOUR          NV、UT
 * KFIVE          CA、ZA
 * 请问如何找出覆盖全美50个州的最小广播台集合呢？
 *
 */
public class SetCover {
    public static void main(String[] args) {
        // 初始化allStates，存放所有需要覆盖的州
        String[]allStates = {"mt", "wa","or", "id", "nv", "ut","ca","az"};
        HashSet<String> allStatesSet = new HashSet<>(Arrays.asList(allStates));
        // 创建一个HashMap broadCasts，存放广播台和每个广播台可覆盖的州
        LinkedHashMap<String,HashSet<String>> broadCasts = new LinkedHashMap<String,HashSet<String>>();
        // 初始化broadCasts
        broadCasts.put("kone", new HashSet<> (Arrays .asList ("id", "nv", "ut")));
        broadCasts.put("ktwo", new HashSet<> (Arrays.asList ("wa", "id", "mt")));
        broadCasts.put("kthree", new HashSet<> (Arrays .asList ("or", "nv", "ca")));
        broadCasts.put("kfour", new HashSet<>(Arrays.asList ("nv","ut")));
        broadCasts.put("kfive", new HashSet<>(Arrays.asList ("ca","az")));
        System.out.println(getBestBroadCasts(allStatesSet, broadCasts));
    }

    public static HashSet<String> getBestBroadCasts(HashSet<String> allStatesSet,
                                                    LinkedHashMap<String, HashSet<String>> broadCasts) {
        HashSet<String> bestBroadCasts = new HashSet<>();
        String bestBroadCast = "";
        // 外层循环控制覆盖所有州
        while (allStatesSet.size() > 0) {
            // 内层循环遍历每一个广播台，得到其覆盖的州
            // 计算这些州与未覆盖州的交集
            // 选出其中可覆盖最多未覆盖州的广播台，将其放到bestBroadCasts集合里
            HashSet<String> maxCovered = new HashSet<>();
            for (Map.Entry<String, HashSet<String>> entry : broadCasts.entrySet()) {
                HashSet<String> set = entry.getValue(); // 得到该广播台可覆盖州的集合
                // 计算该广播台可覆盖的州与未覆盖州的交集
                HashSet<String> covered = new HashSet<>();
                covered.clear();
                covered.addAll(set);
                covered.retainAll(allStatesSet);
                // for循环结束后，maxCovered中保存可覆盖的最多未覆盖的州
                // bestBroadCasts保存对应广播台的名字
                if (covered.size() > maxCovered.size()) {
                    maxCovered = covered;
                    bestBroadCast = entry.getKey();
                    System.out.println("maxCovered=" + maxCovered);
                    System.out.println("bestBroadCast=" + bestBroadCast);
                }
            }
            bestBroadCasts.add(bestBroadCast);
            allStatesSet.removeAll(maxCovered);
        }
        return bestBroadCasts;
    }
}
