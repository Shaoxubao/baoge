package com.baoge.calable;

import com.google.common.collect.Lists;
import guaua.Person;
import org.springframework.stereotype.Service;
import utils.CallableUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author shaoxubao
 * @Date 2019/10/17 17:54
 */

@Service("callableService")
public class CallableServiceImpl implements CallableService {

    public void process() {
        // 计算定长线程池大小
        int threadSize = (int) Math.ceil((double) 10 / 3);
        // 数据按分批处理
        List<List<Person>> personList = Lists.partition(getPersonList(), 3);

        // 初始化任务列表
        List<CallableUtil.InnerClass> taskList = new ArrayList<>(threadSize);
        for (int i = 0; i < threadSize; i++) {
            taskList.add(new CallableUtil.InnerClass(personList.get(i), this.getClass(), "doSomeThing"));
        }
        // 创建多线程任务
        CallableUtil.createCallableTask(taskList, threadSize);
    }

    public void doSomeThing(Object object) {
        System.out.println("==============");

        if (object instanceof List) {
            List list = (List) object;

            List<Person> personList = (List<Person>) object;

            for (Person person : personList) {
                System.out.println(person.getName() + "-----------------------------");
            }
        }
    }

    private List<Person> getPersonList() {
        List<Person> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person("who" + i, i * 10, "男", "中国");
            result.add(person);
        }

        return result;
    }

}
