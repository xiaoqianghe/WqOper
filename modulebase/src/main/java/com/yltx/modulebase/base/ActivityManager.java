package com.yltx.modulebase.base;

import android.app.Activity;
import android.os.Build;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ixzus on 2017/8/6.
 */

public class ActivityManager {
    public static final ActivityManager INSTANCE = new ActivityManager();
    private WeakReference<Activity> mCurrentActivity;
    private static List<Activity> activityStack = new ArrayList<>();

    public ActivityManager() {
    }

    public static ActivityManager getInstance() {
        return INSTANCE;
    }

    public void addActivity(Activity aty) {
        activityStack.add(aty);
    }

    public void removeActivity(Activity aty) {
        activityStack.remove(aty);
    }

    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (mCurrentActivity != null) {
            currentActivity = mCurrentActivity.get();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (currentActivity == null || currentActivity.isDestroyed()) {
                    currentActivity = null;
                }
            }
        }
        return currentActivity;
    }

    public void setCurrentActivity(Activity activity) {
        this.mCurrentActivity = new WeakReference<Activity>(activity);
    }

}
