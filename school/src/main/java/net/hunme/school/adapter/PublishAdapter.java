package net.hunme.school.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.hunme.school.R;
import net.hunme.school.bean.PublishVo;

import java.util.List;

/**
 * ================================================
 * 作    者：ZLL
 * 时    间：2016/8/1
 * 描    述：
 * 版    本：
 * 修订历史：
 * 主要接口：
 * ================================================
 */
public class PublishAdapter extends BaseAdapter {
    private Context context;
    private List<PublishVo> publishList;

    public PublishAdapter(Context context, List<PublishVo> publishList) {
        this.context = context;
        this.publishList = publishList;
    }

    @Override
    public int getCount() {
        return publishList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHold viewHold;
        if(null==view){
            view= LayoutInflater.from(context).inflate(R.layout.item_publish,null);
            new ViewHold(view);
        }
        PublishVo vo=publishList.get(i);
        viewHold= (ViewHold) view.getTag();
//        viewHold.lv_holad.setImageResource(vo.getImgUrl());
        viewHold.tv_title.setText(vo.getTsName());
        viewHold.tv_content.setText(vo.getMessage());
        viewHold.tv_date.setText(vo.getDateTime());
        viewHold.setTextColor(vo.isRead());
        return view;
    }

     class ViewHold{
         ImageView lv_holad;
         TextView tv_title;
         TextView tv_date;
         TextView tv_content;

        public ViewHold(View view) {
            lv_holad = (ImageView) view.findViewById(R.id.lv_holad);
            tv_title= (TextView) view.findViewById(R.id.tv_title);
            tv_date= (TextView) view.findViewById(R.id.tv_date);
            tv_content= (TextView) view.findViewById(R.id.tv_content);
            view.setTag(this);
        }

         public void setTextColor(boolean isRead){
             if(!isRead){
                 tv_title.setTextColor(Color.BLACK);
                 tv_content.setTextColor(Color.BLACK);
                 tv_date.setTextColor(Color.BLACK);
             }else{
                 tv_title.setTextColor(Color.parseColor("#a9a9a9"));
                 tv_content.setTextColor(Color.parseColor("#a9a9a9"));
                 tv_date.setTextColor(Color.parseColor("#a9a9a9"));
             }
         }
    }
}
