package net.hunme.status.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import net.hunme.baselibrary.base.BaseActivity;
import net.hunme.status.R;

/**
 * 作者： wh
 * 时间： 2016/7/19
 * 名称：选择可见范围
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ChoosePermitActivity extends BaseActivity implements View.OnClickListener {
    public  static  final  int CHOOSE_PERMIT = 2;
    /**
     * 班级空间和校园空间
     */
    private RadioButton rb_allroom;
    /**
     * 班级空间
     */
    private RadioButton rb_classroom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_permit);
        initView();
    }

    @Override
    protected void setToolBar() {
        setLiftImage(R.mipmap.ic_arrow_lift);
        setCententTitle("选择可见范围");
        setLiftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void  initView(){
        rb_allroom = $(R.id.rb_allroom);
        rb_classroom = $(R.id.rb_classroom);
        rb_classroom.setOnClickListener(this);
        rb_allroom.setOnClickListener(this);
    }

    private void choose(String permit){
        Intent intent = new Intent();
        intent.setClass(this,PublishStatusActivity.class);
        intent.putExtra("permit",permit);
        setResult(CHOOSE_PERMIT,intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        int viewId =view.getId();
        if (viewId==R.id.rb_allroom){
         //   Log.i("TAGG",rb_allroom.getText().toString());
            choose(rb_allroom.getText().toString());
        }else if (viewId==R.id.rb_classroom) {
           // Log.i("TAGG",rb_classroom.getText().toString());
            choose(rb_classroom.getText().toString());
        }
    }
}