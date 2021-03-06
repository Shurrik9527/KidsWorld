package net.hunme.baselibrary.base;


import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;

import com.umeng.analytics.MobclickAgent;

/**
 * 作者： wh
 * 时间： 2016/7/14
 * 名称：所有Fragment父类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class BaseFragement extends Fragment {
    public <T extends View> T $(View layoutView, @IdRes int resId){
        return (T)layoutView.findViewById(resId);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
    }
}
