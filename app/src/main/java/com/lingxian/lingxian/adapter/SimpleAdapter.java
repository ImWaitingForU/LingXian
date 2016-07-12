package com.lingxian.lingxian.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lingxian.lingxian.R;
import com.lingxian.lingxian.activity.DianziDetailActivity;
import com.lingxian.lingxian.bean.DianziBean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/11 0011.
 * <p/>
 * 点子界面适配器
 */
public class SimpleAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    protected List<DianziBean> mDatas;


    // 设置一个接口回调，给Item绑定一个点击事件
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    //simpleadapter构造方法，传入上下文和数据
    public SimpleAdapter(Context context, List<DianziBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        View view = mInflater.inflate(R.layout.item_of_dianzi, arg0, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position).getTempdata());
        holder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,
                        DianziDetailActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tv;
    LinearLayout lin;

    public MyViewHolder(View arg0) {
        super(arg0);
        tv = (TextView) arg0.findViewById(R.id.id_tv);
        lin = (LinearLayout) arg0.findViewById(R.id.lin_of_dianziitem);
    }
}