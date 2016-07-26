package com.lingxian.lingxian.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;

import com.lingxian.lingxian.R;
import com.lingxian.lingxian.view.DetailButtomLayout;

public class XiangfaDetailActivity extends AppCompatActivity {

	// TODO:从用户收藏中查询，若已经收藏点击收藏按钮则取消收藏，否则加入收藏表
	public boolean isLike = false;
	private DetailButtomLayout dbl;
	private AnimatorSet set;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xiangfa_detail);

		initView();
	}

	private void initView() {
		dbl = (DetailButtomLayout) findViewById(R.id.dbl);
		dbl.getIb2().setImageResource(R.drawable.ic_like_gray);
		dbl.getIb2().setVisibility(View.VISIBLE);
		dbl.getIb3().setImageResource(R.drawable.ic_message);

		dbl.setOnIbClickListener(new DetailButtomLayout.IbClickListener() {
			@Override
			public void onIb1Clicked(View ib1) {
				finish();
			}

			@Override
			public void onIb2Clicked(View ib2) {
				like();
				Log.d("tag", "likea~~~~");
			}

			@Override
			public void onIb3Clicked(View ib3) {
				Intent intent = new Intent(XiangfaDetailActivity.this,
						ChatActivity.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * 收藏
	 *
	 */
	private void like() {
		ImageButton button = dbl.getIb2();

		ObjectAnimator scaleX = ObjectAnimator.ofFloat(button, "scaleX", 1.0f,
				1.5f, 1.0f);
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(button, "scaleY", 1.0f,
				1.5f, 1.0f);

		set = new AnimatorSet();
		set.playTogether(scaleX, scaleY);

		set.setDuration(800);
		set.setInterpolator(new OvershootInterpolator());

		if (set.isRunning()) {
			return;
		}

		set.start();

		if (isLike) {
			button.setImageResource(R.drawable.ic_like_gray);
			isLike = false;
			// TODO:取消收藏
		} else {
			button.setImageResource(R.drawable.ic_like);
			isLike = true;

			// TODO:添加收藏
		}

	}
}
