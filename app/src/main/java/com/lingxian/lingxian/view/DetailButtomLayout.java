package com.lingxian.lingxian.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.lingxian.lingxian.R;

/**
 * Created by Chan on 2016/7/15.
 *
 * 详情界面的底部按钮组合,包括返回，喜欢，联系.等
 */
public class DetailButtomLayout extends RelativeLayout
		implements
			View.OnClickListener {

	// 从左到右
	private ImageButton ib1;
	private ImageButton ib2;
	private ImageButton ib3;
	private IbClickListener listener;

	public DetailButtomLayout(Context context) {
		super(context);
		init(context);
	}

	public DetailButtomLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		this.setLayoutParams(new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT));
		View view = LayoutInflater.from(context).inflate(
				R.layout.bottom_layout, this, true);
		ib1 = (ImageButton) view.findViewById(R.id.ib_back);
		ib2 = (ImageButton) view.findViewById(R.id.ib_like);
		ib3 = (ImageButton) view.findViewById(R.id.ib_ok);
		ib1.setOnClickListener(this);
		ib2.setOnClickListener(this);
		ib3.setOnClickListener(this);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}

	public void setOnIbClickListener(IbClickListener listener) {
		this.listener = listener;
	}

	@Override
	public void onClick(View v) {
		if (listener == null) {
			return;
		}
		switch (v.getId()) {
			case R.id.ib_back :
				listener.onIb1Clicked(v);
				break;
			case R.id.ib_like :
				listener.onIb2Clicked(v);
				break;
			case R.id.ib_ok :
				listener.onIb3Clicked(v);
				break;
			default :
				break;
		}
	}

	public ImageButton getIb1() {
		return ib1;
	}

	public void setIb1(ImageButton ib1) {
		this.ib1 = ib1;
	}

	public ImageButton getIb2() {
		return ib2;
	}

	public void setIb2(ImageButton ib2) {
		this.ib2 = ib2;
	}

	public ImageButton getIb3() {
		return ib3;
	}

	public void setIb3(ImageButton ib3) {
		this.ib3 = ib3;
	}

	public IbClickListener getListener() {
		return listener;
	}

	public void setListener(IbClickListener listener) {
		this.listener = listener;
	}

	public interface IbClickListener {
		void onIb1Clicked(View ib1);
		void onIb2Clicked(View ib2);
		void onIb3Clicked(View ib3);
	}
}
