package com.yltx.modulebase.util;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * 功能描述:
 * Created by ixzus on 2017/4/18.
 */

public class TimeCount extends CountDownTimer {

    private Button button;

    //参数依次为总时长,和计时的时间间隔
    public TimeCount(Button button, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.button = button;
    }

    //计时过程显示
    @Override
    public void onTick(long millisUntilFinished) {
        String time = millisUntilFinished / 1000 + "秒后重新获取";
        setButtonInfo(time, false);
    }

    //计时完毕时触发
    @Override
    public void onFinish() {
        setButtonInfo("重新获取", true);
    }

    /**
     * 验证按钮在点击前后相关设置
     *
     * @param content 要显示的内容
     * @param isClick 是否可点击
     */
    private void setButtonInfo(String content, boolean isClick) {
        button.setText(content);
        button.setEnabled(isClick);
    }
}
