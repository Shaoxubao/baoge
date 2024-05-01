package algorithm.od202401;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: xubao_shao@163.com
 * @Description:
 * @Date 2024/5/1 22:58
 *
   拔河比赛
   公司最近准备进行拔河比赛，需要在全部员工中进行挑选选拔的规则如下:
   1.按照身高优先、体重次优先的方式准备比赛阵容
   2.规定参赛的队伍派出10名选手
   请实现一个选拔队员的小程序。
   输入为一个数组，记录了部门人员的身高、体重信息，如[身高,体重的方式放置;部门全部成员数量为大于10的一个数组要求输出一个size为10的二维数组，
   输入描述
   输入为N行员工信息，表示部门报名参加选拔的候选人信息，每行有两个数字，使用空格分隔，表示员工的身高、体重信息
   如
   181 70
   182 70
   表示两位候选员工，第一人身高181厘米，体重70公斤;第二人身高182厘米，体重70公斤
   输出描述
   要求输出一个10行的已经排序的参赛员工信息数据，每行有两个数字，使用空格分隔，表示员工的身高、体重信息如
   182 70
   181 70
   备注输入数据范围
   成员身高、体重为int数据类型
   输入备选成员数量为N，10 < N 100
   示例1：
   输入
   181 70
   182 70
   183 70
   184 70
   185 70
   186 70
   180 71
   180 72
   180 73
   180 74
   180 75
   输出
   186 70
   185 70
   184 70
   183 70
   182 70
   181 70
   180 75
   180 74
   180 73
   180 72
   解析
   这个题看着蛮难的，但是只要数据结构定义好了，其实做起来也蛮简单的
   其实就是一个List 集合内装入一个实体类 这个实体类记录了选手的基本信息
   给集合进行排序即可
 */
public class TugOfWarCompetition {

    static class Person {
        private int height; // 身高

        private int weight; // 体重

        public Person(int h, int w) {
            this.height = h;
            this.weight = w;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> persons = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line  = scanner.nextLine();
            if (line == null || line.equals("")) {
                break;
            }
            Person person = new Person(Integer.parseInt(line.split(" ")[0]),
                    Integer.parseInt(line.split(" ")[1]));
            persons.add(person);
        }
        persons.sort((p1, p2) -> p1.height != p2.height ? p2.height - p1.height : p2.weight - p1.weight);
        for (int i = 0; i < 10; i++) {
            Person p = persons.get(i);
            System.out.println(p.height + " " + p.weight);
        }
    }
}
