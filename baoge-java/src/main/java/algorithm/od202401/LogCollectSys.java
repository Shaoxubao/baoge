package algorithm.od202401;

import java.util.Scanner;

/**
 题目
 日志采集是运维系统的的核心组件。日志是按行生成，每行记做一条，由采集系统分批上报。如果上报太频繁，
 会对服务端造成压力；如果上报太晚，会降低用户的体验；如果一次上报的条数太多，会导致超时失败。
 为此，项目组设计了如下的上报策略：
 1、每成功上报一条日志，奖励1分
 2、每条日志每延迟上报1秒，扣1分
 3、积累日志达到100条，必须立即上报给出日志系统，
 根据该规则，计算首次上报能获得的最多积分数。
 输入
 按时序产生的日志条数 T1,T2...Tn, 其中 1 ≤ n ≤ 1000, 0 ≤ Ti ≤ 100
 输出描述
 首次上报最多能获得的积分数
 示例1
 输入：1 98 1
 输出：98
 说明：采集系统第2个时刻上报，可获得最大积分(98 + 1) - 1 = 98

 示例2
 输入：50 60 1
 输出：50
 说明：如果第一个时刻上报，获得积分是50，如果第2个时刻上报，最多上报100条，
 前50条延迟上报1秒，每条扣1分，共获得积分100 - 50 = 50

 */
public class LogCollectSys {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            // 记录按时序产生的日志条数
            String[] logByTimeArr = scanner.nextLine().split(" ");
            int score = 0;    // 每个时刻上报的总积分
            int maxScore = 0; // 最大积分
            int count = 0;    // 日志条数
            for (int i = 0; i < logByTimeArr.length; i++) {
                score = 0;
                count += Integer.parseInt(logByTimeArr[i]);
                if (count >= 100) { // 必须上报
                    score = 100 - (count - Integer.parseInt(logByTimeArr[i]));
                    maxScore = Math.max(maxScore, score);
                    break;
                } else {
                    if (i == 0) {
                        score += count;
                    } else {
                        // 第二个时序后上报且总条数小于100时，需要减去延迟上报的扣减积分
                        score += count - (count - Integer.parseInt(logByTimeArr[i]));
                    }
                    maxScore = Math.max(maxScore, score);
                }
            }
            System.out.println(maxScore);
        }
    }
}
