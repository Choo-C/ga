package vip.wexiang.job.txt.test;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;
@Slf4j
public class TestFuture {
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new CallableTest());
//        Future<Map<String,List<String>>>
        log.info("直接拿到了future对象，但是此刻Callable线程还没有返回结果");
        log.info("主线程开始异步计算");
        Thread.sleep(5*1000);
        log.info("主线程计算结束，但是Callable，还没计算完成，得到");
        long start = System.currentTimeMillis();
        String result = future.get();
        long end = System.currentTimeMillis();
        log.info("主线程为了Callable线程，等了怎么久："+((end-start)/1000)+"秒");
        log.info("result:"+result);

        future = executorService.submit(new CallableTest());
        log.info("--------------------------------------------");
        log.info("直接拿到了future对象，但是此刻Callable线程还没有返回结果");
        log.info("主线程开始异步计算");
        Thread.sleep(15*1000);
        log.info("Callable线程先于主线程执行成功");
        start = System.currentTimeMillis();
        result = future.get();
        end = System.currentTimeMillis();
        log.info("主线程没有等Callable线程，等了怎么久："+((end-start)/1000)+"秒");
        log.info("result"+result);

        executorService.shutdown();
    }
}
@Slf4j
class CallableTest implements Callable<String>{
    @Override
    public String call() throws Exception {
        try{
            log.error("CallableTest>>>>>>>>>>开始执行");
            log.error("延时，模拟计算");
            Thread.sleep(10*1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "result";
    }
}
class FutureOne implements Future{
    @Override
    public Object get() throws InterruptedException, ExecutionException {
        return null;
    }

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
    public Object get(long timeout, @NotNull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
