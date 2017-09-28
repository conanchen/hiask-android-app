package org.ditto.lib.usecases;




import android.content.Intent;
import android.os.IBinder;

import com.xdandroid.hellodaemon.AbsWorkService;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

public class AppServiceKeepliveTraceImpl extends AbsWorkService {

    //是否 任务完成, 不再需要服务运行?
    public static boolean sShouldStopService;
    public static Disposable sDisposable;

    public static void stopService() {
        //我们现在不再需要服务运行了, 将标志位置为 true
        sShouldStopService = true;
        //取消对任务的订阅
        if (sDisposable != null) sDisposable.dispose();
        //取消 Job / Alarm / Subscription
        cancelJobAlarmSub();
    }

    /**
     * 是否 任务完成, 不再需要服务运行?
     *
     * @return 应当停止服务, true; 应当启动服务, false; 无法判断, 什么也不做, null.
     */
    @Override
    public Boolean shouldStopService(Intent intent, int flags, int startId) {
        if (intent != null && Intent.ACTION_RUN.equals(intent.getAction())) {
            sShouldStopService = false;
        }

        return sShouldStopService;
    }

    @Override
    public void startWork(Intent intent, int flags, int startId) {
        int seconds = 30;
        Timber.d("检查磁盘中是否有上次销毁时保存的数据");
        sDisposable = Flowable
                .interval(seconds, TimeUnit.SECONDS)
                //取消任务时取消定时唤醒
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Timber.d("定时任务:保存数据到磁盘。");
                        cancelJobAlarmSub();
                    }
                }).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long count) throws Exception {
                        Timber.d("定时任务:每 %d 秒采集一次数据... count = %d", seconds, count);
                        if (count > 0 && count % 18 == 0)
                            Timber.d("定时任务:保存数据到磁盘。 saveCount = " + (count / 18 - 1));
                    }
                });

    }

    @Override
    public void stopWork(Intent intent, int flags, int startId) {
        stopService();
    }

    /**
     * 任务是否正在运行?
     *
     * @return 任务正在运行, true; 任务当前不在运行, false; 无法判断, 什么也不做, null.
     */
    @Override
    public Boolean isWorkRunning(Intent intent, int flags, int startId) {
        //若还没有取消订阅, 就说明任务仍在运行.
        return sDisposable != null && !sDisposable.isDisposed();
    }

    @Override
    public IBinder onBind(Intent intent, Void v) {
        return null;
    }

    @Override
    public void onServiceKilled(Intent rootIntent) {
        Timber.d("onServiceKilled...保存数据到磁盘。");
    }

    @Override
    protected int onStart(Intent intent, int flags, int startId) {
        Timber.d("onStart flags=%d startId=%d", flags, startId);
        return super.onStart(intent, flags, startId);
    }

}