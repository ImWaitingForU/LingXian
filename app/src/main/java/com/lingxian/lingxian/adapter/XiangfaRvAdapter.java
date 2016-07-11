package com.lingxian.lingxian.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lingxian.lingxian.R;
import com.lingxian.lingxian.activity.XiangfaDetailActivity;
import com.lingxian.lingxian.bean.XiangfaBean;

import java.util.List;

/**
 * Created by Chan on 2016/7/11.
 *
 * 想法界面的RecylerView的Adapter
 */
public class XiangfaRvAdapter extends RecyclerView.Adapter<XiangfaRvAdapter.VH> {

	private Context context;
	private LayoutInflater inflater;
	private List<XiangfaBean> beanList;

	public XiangfaRvAdapter(Context context, List<XiangfaBean> beanList) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.beanList = beanList;
	}

	@Override
	public VH onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.xiangfa_rv_itemlayout, parent,
				false);
		return new VH(view);
	}
	@Override
	public void onBindViewHolder(VH holder, int position) {
		holder.tv_title.setText(beanList.get(position).getTitle());
		holder.tv_time.setText(beanList.get(position).getTime());
		// Picasso就位，准备图片加载~
		// TODO :图片加载

		// 点击跳转到详情界面
		holder.rl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				context.startActivity(new Intent(context,
						XiangfaDetailActivity.class));
			}
		});
	}

	@Override
	public int getItemCount() {
		return beanList.size();
	}

	public static class VH extends RecyclerView.ViewHolder {

		private ImageView iv_thumbnail; // 缩略图
		private TextView tv_title; // 标题
		private ImageView iv_avatar; // 头像
		private TextView tv_time; // 发布时间
		private RelativeLayout rl; // 外面的根布局 用于添加点击事件

		public VH(View itemView) {
			super(itemView);

			rl = (RelativeLayout) itemView.findViewById(R.id.xiangfaFrag_rv_rl);

			iv_thumbnail = (ImageView) itemView
					.findViewById(R.id.xiangfaFrag_rv_iv_thumbNail);
			iv_avatar = (ImageView) itemView
					.findViewById(R.id.xiangfaFrag_rv_iv_avatar);
			tv_title = (TextView) itemView
					.findViewById(R.id.xiangfaFrag_rv_tv_title);
			tv_time = (TextView) itemView
					.findViewById(R.id.xiangfaFrag_rv_tv_time);

		}
	}
}
