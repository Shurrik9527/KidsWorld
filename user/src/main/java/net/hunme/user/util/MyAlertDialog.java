package net.hunme.user.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import net.hunme.user.R;

/**
 * ====
 * 作    者：Restring
 * 时    间： 2016/7/24
 * 描    述：自定义一个弹框
 * 版    本：
 * 修订历史：
 * 主要接口：.
 */
public class MyAlertDialog {
    public static AlertDialog getDialog(View view, Activity activity, int flag) {
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams params =
                alertDialog.getWindow().getAttributes();
        params.width = (int) (display.getWidth() * 0.8);
        if (flag==1){
            params.height=display.getHeight()/4;
        }else {
            params.height=WindowManager.LayoutParams.WRAP_CONTENT;
        }
        params.flags = WindowManager. LayoutParams.FLAG_DIM_BEHIND;
        alertDialog.getWindow().setAttributes(params);
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.fillet_pop);
        alertDialog.getWindow().setContentView(view);
        return  alertDialog;
    }
}
