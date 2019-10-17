package utils;

import com.google.common.collect.Lists;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description: 线程池工具类
 */
public class CallableUtil {

    /**
     * 创建线程池任务方法
     * @param taskList      任务列表
     * @param threadSize    线程数
     * @return
     */
    public static List<Future<DataResponse>> createCallableTask(List<InnerClass> taskList, Integer threadSize) {

        // 初始化定长线程池
        ExecutorService executorService = new ThreadPoolExecutor(threadSize, threadSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        try {
            // 批量提交任务,获取结果集
            List<Future<DataResponse>> resultList = executorService.invokeAll(taskList);
           return resultList;
        } catch (Exception e) {
            List<Future<DataResponse>> resultList = Lists.newArrayList();
            resultList.add(new Future<DataResponse>() {
                @Override
                public boolean cancel(boolean mayInterruptIfRunning) {
                    return false;
                }

                @Override
                public boolean isCancelled() {
                    return false;
                }

                @Override
                public boolean isDone() {
                    return false;
                }

                @Override
                public DataResponse get() throws InterruptedException, ExecutionException {
                    return DataResponse.builderFailed();
                }

                @Override
                public DataResponse get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                    return DataResponse.builderFailed();
                }
            });
            return  resultList;
        } finally {
            //关闭线程池
            executorService.shutdown();
        }
    }

    /**
     * 通用内部类
     */
    public static class InnerClass implements Callable<DataResponse> {

        /**
         * 对象
         */
        private final Object paramObj;

        /**
         * 调用类
         */
        private Class<?> clazz;

        /**
         * 调用方法
         */
        private final String methodName;

        /**
         * 构造器
         * @param paramObj 线程调用入参
         * @param clazz  线程调用类
         * @param methodName 线程调用方法
         */
        public InnerClass(Object paramObj, Class<?> clazz, String methodName) {
            this.paramObj = paramObj;
            this.clazz =clazz;
            this.methodName = methodName;
        }

        /**
         * 线程调用
         * @return
         * @throws Exception
         */
        @Override
        public DataResponse call() throws Exception{
            Object obj = null;
            Method method = null;
            try {
                // 获取bean
                obj = SpringContextUtil.getBean(clazz);
                // 获取方法
                method = obj.getClass().getDeclaredMethod(methodName, Object.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return (DataResponse) method.invoke(obj, paramObj);
        }
    }
}
