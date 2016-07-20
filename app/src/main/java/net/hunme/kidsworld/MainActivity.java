package net.hunme.kidsworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.hunme.discovery.DiscoveryFragement;
import net.hunme.message.fragment.MessageFragement;
import net.hunme.school.SchoolFragement;
import net.hunme.status.StatusFragement;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import main.jpushlibrary.JPush.JPushBaseActivity;

/**
 * 作者： wh
 * 时间： 2016/7/19
 * 名称：app--首页
 * 版本说明：
 * 附加注释：继承了极光推送页面，初始化极光的设置,其中ShwoMessageReceiver为通讯未读消息个数接收广播，当未读消息不为0时，显示小圆点，否则不显示
 * 主要接口：
 */
public class MainActivity extends JPushBaseActivity {
    private static final String SHOWDOS = "net.hunme.message.showdos";
    /**
     * 通讯圆点
     */
    @Bind(R.id.tv_dos_message)
    TextView tvMeaasgeDos;
    /**
     * 动态圆点
     */
    @Bind(R.id.tv_dos_status)
    TextView tvStatusDos;
    private FragmentManager fragmentManager;
    /**
     * 动态
     */
    private StatusFragement statusFragement;
    @Bind(R.id.tv_status)
    TextView tvStatus;
    @Bind(R.id.iv_status)
    ImageView ivStatus;
    /**
     * 学校
     */
    private SchoolFragement schoolFragement;

    @Bind(R.id.tv_school)
    TextView tvSchool;
    @Bind(R.id.iv_school)
    ImageView ivSchool;
    /**
     * 乐园
     */
    private DiscoveryFragement discoveryFragement;
    @Bind(R.id.tv_discovery)
    TextView tvDiscovery;
    @Bind(R.id.iv_discovery)
    ImageView ivDiscovery;
    /**
     * 通讯
     */
    private MessageFragement messageFragement;
    @Bind(R.id.tv_message)
    TextView tvMessage;
    @Bind(R.id.iv_message)
    ImageView ivMessage;
    /**
     * 通讯未读消息个数接收广播
     */
    private ShwoMessageReceiver mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        registerBoradcastReceiver();
        initJPushConfiguration();
    }

    /**
     * 初始化数据
     */
    private void init() {
        statusFragement = new StatusFragement();
        schoolFragement = new SchoolFragement();
        discoveryFragement = new DiscoveryFragement();
        messageFragement = new MessageFragement();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, statusFragement);
        transaction.commit();
        mBroadcastReceiver = new ShwoMessageReceiver();
    }

    /**
     * 通过点击事件改变tab
     */
    @OnClick({R.id.ll_status, R.id.ll_school, R.id.ll_discovery, R.id.ll_message})
    public void onClick(View view) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.ll_status:
                fragment = statusFragement;
                tvDiscovery.setTextColor(getResources().getColor(R.color.default_grey));
                tvMessage.setTextColor(getResources().getColor(R.color.default_grey));
                tvSchool.setTextColor(getResources().getColor(R.color.default_grey));
                tvStatus.setTextColor(getResources().getColor(R.color.main_green));
                ivSchool.setImageResource(R.mipmap.school);
                ivDiscovery.setImageResource(R.mipmap.discovery);
                ivMessage.setImageResource(R.mipmap.message);
                ivStatus.setImageResource(R.mipmap.status_p);
                break;
            case R.id.ll_school:
                fragment = schoolFragement;
                tvDiscovery.setTextColor(getResources().getColor(R.color.default_grey));
                tvMessage.setTextColor(getResources().getColor(R.color.default_grey));
                tvSchool.setTextColor(getResources().getColor(R.color.main_green));
                tvStatus.setTextColor(getResources().getColor(R.color.default_grey));
                ivSchool.setImageResource(R.mipmap.school_p);
                ivDiscovery.setImageResource(R.mipmap.discovery);
                ivMessage.setImageResource(R.mipmap.message);
                ivStatus.setImageResource(R.mipmap.status);
                break;
            case R.id.ll_discovery:
                fragment = discoveryFragement;
                tvDiscovery.setTextColor(getResources().getColor(R.color.main_green));
                tvMessage.setTextColor(getResources().getColor(R.color.default_grey));
                tvSchool.setTextColor(getResources().getColor(R.color.default_grey));
                tvStatus.setTextColor(getResources().getColor(R.color.default_grey));
                ivSchool.setImageResource(R.mipmap.school);
                ivDiscovery.setImageResource(R.mipmap.discovery_p);
                ivMessage.setImageResource(R.mipmap.message);
                ivStatus.setImageResource(R.mipmap.status);
                break;
            case R.id.ll_message:
                fragment = messageFragement;
                tvDiscovery.setTextColor(getResources().getColor(R.color.default_grey));
                tvMessage.setTextColor(getResources().getColor(R.color.main_green));
                tvSchool.setTextColor(getResources().getColor(R.color.default_grey));
                tvStatus.setTextColor(getResources().getColor(R.color.default_grey));
                ivSchool.setImageResource(R.mipmap.school);
                ivDiscovery.setImageResource(R.mipmap.discovery);
                ivMessage.setImageResource(R.mipmap.message_p);
                ivStatus.setImageResource(R.mipmap.status);
                break;
        }
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    /**
     * 注册广播
     */
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(SHOWDOS);
        registerReceiver(mBroadcastReceiver, myIntentFilter);
    }

    /**
     * 对消息的圆点处理广播
     */
    private class ShwoMessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(SHOWDOS)) {
                int count = intent.getIntExtra("count", 0);
                if (count > 0) {
                    tvMeaasgeDos.setVisibility(View.VISIBLE);
                } else {
                    tvMeaasgeDos.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBroadcastReceiver != null) {
            unregisterReceiver(mBroadcastReceiver);
        }
    }

}



