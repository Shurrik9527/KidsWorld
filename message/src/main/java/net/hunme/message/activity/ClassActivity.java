package net.hunme.message.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import net.hunme.baselibrary.base.BaseActivity;
import net.hunme.baselibrary.mode.Result;
import net.hunme.baselibrary.network.Apiurl;
import net.hunme.baselibrary.network.OkHttpListener;
import net.hunme.baselibrary.network.OkHttps;
import net.hunme.baselibrary.util.UserMessage;
import net.hunme.message.R;
import net.hunme.message.adapter.ClassAdapter;
import net.hunme.message.bean.GroupJson;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;

/**
 * 作者： wh
 * 时间： 2016/7/15
 * 名称：通讯首页--班级
 * 版本说明：
 * 附加注释：通过服务端传递过来的班级列表，进行群聊
 * 主要接口：
 */
public class ClassActivity extends BaseActivity implements OkHttpListener{
    /**
     * 班级列表
     */
    private ListView lv_class;
    /**
     * 适配器
     */
    private ClassAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        getClassinfor();
    }

    @Override
    protected void setToolBar() {
        setCententTitle("班级");
         setLiftOnClickClose();
        setLiftImage(R.mipmap.ic_arrow_lift);
    }
    private void setlistview(final List<GroupJson> groupJsons){
        lv_class = $(R.id.lv_class);
        adapter = new ClassAdapter(this,groupJsons);
        lv_class.setAdapter(adapter);
        lv_class.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (RongIM.getInstance()!=null){
                    RongIM.getInstance().startGroupChat(ClassActivity.this,groupJsons.get(i).getClassId(),groupJsons.get(i).getGroupName());
                }
            }
        });
    }
    /**
     * 获取所有班级信息
     */
    private  void getClassinfor(){
        Map<String,Object> params = new HashMap<>();
        params.put("tsId", UserMessage.getInstance(this).getTsId());
        Log.i("TAFFFF",UserMessage.getInstance(this).getTsId());
        //1=群，2=老师，3=家长
        params.put("type",1);
        Type type =new TypeToken<Result<List<GroupJson>>>(){}.getType();
        OkHttps.sendPost(type,Apiurl.MESSAGE_GETGTOUP,params,this);

    }

    @Override
    public void onSuccess(String uri, Object date) {
        Log.i("TAG",date+"");
        Result<List<GroupJson>> data = (Result<List<GroupJson>>) date;
        List<GroupJson> groupJsonList =data.getData();
        if (groupJsonList!=null||groupJsonList.size()!=0){
            setlistview(groupJsonList);
        }

    }

    @Override
    public void onError(String uri, String error) {
        Log.i("TAG",error);
        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
    }
}
