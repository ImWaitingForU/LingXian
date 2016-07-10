package com.lingxian.lingxian.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lingxian.lingxian.R;

/**
 * Created by Chan on 2016/7/10.
 *
 * 主界面底部导航
 */
public class IwfuBottomBar extends LinearLayout implements View.OnClickListener {

	private FragmentSwichterListener fsl;
	public ImageView tabIv1;
	private ImageView tabIv2;
	private ImageView tabIv3;
	private ImageView tabIv4;

	public IwfuBottomBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOrientation(HORIZONTAL);
		this.setBackgroundColor(Color.WHITE);
		this.setPadding(5, 10, 5, 10);
		LayoutParams bottomBarParams = new LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		bottomBarParams.gravity = Gravity.CENTER;

		tabIv1 = addTab(context, "想法", R.drawable.ic_xiangfa);
		tabIv2 = addTab(context, "点子", R.drawable.ic_dianzi);
		addTab(context, "", 0);
		tabIv3 = addTab(context, "灵感", R.drawable.ic_linggan);
		tabIv4 = addTab(context, "我的", R.drawable.ic_wode);

		// 默认选中tab1
		tabIv1.setImageResource(R.drawable.ic_xiangfa_selected);
		this.setLayoutParams(bottomBarParams);
	}

	public IwfuBottomBar(Context context) {
		super(context);
	}

	/**
	 * 添加tab
	 * 
	 * @param context 上下文
	 * @param tabTitle tab标题
	 * @param iconId tab图标
	 * @return tab的ImageView
	 */
	private ImageView addTab(Context context, String tabTitle, int iconId) {
		LinearLayout tab = new LinearLayout(context);
		tab.setOrientation(VERTICAL);
		tab.setGravity(Gravity.CENTER);
		LayoutParams tabParams = new LayoutParams(0,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		tabParams.gravity = Gravity.CENTER;
		tabParams.weight = 1;

		TextView tabTv = new TextView(context);
		tabTv.setText(tabTitle);
		tabTv.setTextColor(Color.BLACK);
		tabTv.setTextSize (10);
		tabTv.setGravity(Gravity.CENTER);
		LayoutParams tabTvParams = new LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);

		ImageView tabIv = new ImageView(context);
		tabIv.setImageResource(iconId);
		LayoutParams tabIvParams = new LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		tabIvParams.setMargins (0,10,0,5);

		tab.addView(tabIv, tabIvParams);
		tab.addView(tabTv, tabTvParams);
		// 用tab名字标记tab以便区分
		tab.setTag(tabTitle);
		tab.setOnClickListener(this);

		this.addView(tab, tabParams);

		return tabIv;
	}

	public void bindFragmentSwitcherListener(FragmentSwichterListener fsl) {
		this.fsl = fsl;
	}

	@Override
	public void onClick(View v) {
		switch ((String) v.getTag()) {
			case "想法" :
				tabIv1.setImageResource(R.drawable.ic_xiangfa_selected);
				tabIv2.setImageResource(R.drawable.ic_dianzi);
				tabIv3.setImageResource(R.drawable.ic_linggan);
				tabIv4.setImageResource(R.drawable.ic_wode);

				fsl.onTab1Selected();
				break;
			case "点子" :
				tabIv1.setImageResource(R.drawable.ic_xiangfa);
				tabIv2.setImageResource(R.drawable.ic_dianzi_selected);
				tabIv3.setImageResource(R.drawable.ic_linggan);
				tabIv4.setImageResource(R.drawable.ic_wode);

				fsl.onTab2Selected();
				break;
			case "灵感" :
				tabIv1.setImageResource(R.drawable.ic_xiangfa);
				tabIv2.setImageResource(R.drawable.ic_dianzi);
				tabIv3.setImageResource(R.drawable.ic_linggan_selected);
				tabIv4.setImageResource(R.drawable.ic_wode);

				fsl.onTab3Selected();
				break;
			case "我的" :
				tabIv1.setImageResource(R.drawable.ic_xiangfa);
				tabIv2.setImageResource(R.drawable.ic_dianzi);
				tabIv3.setImageResource(R.drawable.ic_linggan);
				tabIv4.setImageResource(R.drawable.ic_wode_selected);

				fsl.onTab4Selected();
				break;
			default :
				break;
		}
	}

	/**
	 * 切换Fragment时回调
	 */
	public interface FragmentSwichterListener {
		void onTab1Selected();
		void onTab2Selected();
		void onTab3Selected();
		void onTab4Selected();
	}

}
