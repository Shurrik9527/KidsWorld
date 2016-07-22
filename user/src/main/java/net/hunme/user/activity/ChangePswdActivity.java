package net.hunme.user.activity;

import android.os.Bundle;
import android.view.View;

import net.hunme.baselibrary.base.BaseActivity;
import net.hunme.user.R;

public class ChangePswdActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pswd);
    }

    @Override
    protected void setToolBar() {
        setLiftImage(R.mipmap.ic_arrow_lift);
        setLiftOnClickListener(this);
        setCententTitle("修改密码");
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId==R.id.iv_left){
            finish();
        }
    }
}