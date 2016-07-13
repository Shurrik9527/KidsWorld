package net.hunme.baselibrary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.hunme.baselibrary.R;
import net.hunme.baselibrary.util.ToolBarHelper;


/**
 * ================================================
 * 作    者：ZLL
 * 时    间：2016/7/13
 * 描    述：
 * 版    本：
 * 修订历史：
 * 主要接口：
 * ================================================
 */
public abstract class BaseActivity extends AppCompatActivity {
    private ToolBarHelper mToolBarHelper ;
    public Toolbar toolbar ;
    private ImageView iv_lift;
    private TextView tv_title;
    private ImageView iv_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {

        mToolBarHelper = new ToolBarHelper(this,layoutResID) ;
        toolbar = mToolBarHelper.getToolBar() ;
        setContentView(mToolBarHelper.getContentView());
        /*把 toolbar 设置到Activity 中*/
        setSupportActionBar(toolbar);
        /*自定义的一些操作*/
        onCreateCustomToolBar(toolbar) ;
        getToolbarViews(toolbar);
    }

    public void onCreateCustomToolBar(Toolbar toolbar){
        toolbar.setContentInsetsRelative(0,0);
    }

    private void getToolbarViews(Toolbar toolbar){
        iv_lift= (ImageView) toolbar.findViewById(R.id.iv_left);
        iv_right= (ImageView) toolbar.findViewById(R.id.iv_right);
        tv_title= (TextView) toolbar.findViewById(R.id.tv_title);
    }

    public void setLiftImage(int imageResource){
        iv_lift.setImageResource(imageResource);
    }

    public void setRightImage(int imageResource){
        iv_right.setImageResource(imageResource);
        iv_right.setVisibility(View.VISIBLE);
    }

    public void setCententTitle(String title){
        tv_title.setText(title);
    }

    public void setLiftOnClickListener(View.OnClickListener listener){
        iv_lift.setOnClickListener(listener);
    }

    public void setRightOnClickListener(View.OnClickListener listener){
        iv_right.setOnClickListener(listener);
    }

}