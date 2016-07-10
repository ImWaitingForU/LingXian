package com.lingxian.lingxian.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;

import com.lingxian.lingxian.R;

/**
 * Created by Chan on 2016/7/10.
 *
 * 底部导航的中间按钮
 */
public class IwfuCenterButton extends ImageButton
		implements
			View.OnClickListener {

	public static boolean isOpen = false; // 判断中间按钮是否打开
	private CenterButtonListener listener;

	public IwfuCenterButton(Context context) {
		super(context);
	}

	public IwfuCenterButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initButton();
	}

	private void initButton() {
		this.setImageResource(R.drawable.ic_tab_button);
		this.setOnClickListener(this);
		this.setBackgroundColor(Color.TRANSPARENT);
	}



	/*
	 * 打开中间按钮的动画
	 */
	private void goCenterButton(final View view) {
		if (isOpen) {
			// 打开—>关闭
			RotateAnimation openAnimation = new RotateAnimation(135, 0,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			openAnimation.setDuration(400);
			openAnimation.setFillAfter(true);
			openAnimation
					.setAnimationListener(new Animation.AnimationListener() {
						@Override
						public void onAnimationStart(Animation animation) {
							view.setClickable(false);
						}

						@Override
						public void onAnimationEnd(Animation animation) {
							isOpen = false;
							view.setClickable(true);
						}

						@Override
						public void onAnimationRepeat(Animation animation) {

						}
					});
			view.startAnimation(openAnimation);
			listener.closeCenterButton ();
		} else {
			// 关闭->打开

			// 按钮旋转
			RotateAnimation openAnimation = new RotateAnimation(0, 135,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			openAnimation.setDuration(400);
			openAnimation.setFillAfter(true);
			openAnimation
					.setAnimationListener(new Animation.AnimationListener() {
						@Override
						public void onAnimationStart(Animation animation) {
							view.setClickable(false);
						}

						@Override
						public void onAnimationEnd(Animation animation) {
							isOpen = true;
							view.setClickable(true);
						}

						@Override
						public void onAnimationRepeat(Animation animation) {

						}
					});
			view.startAnimation(openAnimation);
			listener.openCenterButton ();
			// float son1X = sonLinearLayout1.getX();
			// float son1Y = sonLinearLayout1.getY();
			// sonLinearLayout1.setVisibility (VISIBLE);
			// ObjectAnimator transXAnimator = ObjectAnimator.ofFloat(
			// sonLinearLayout1, "translationX", son1X, son1X - 100);
			// ObjectAnimator transYAnimator = ObjectAnimator.ofFloat(
			// sonLinearLayout1, "translationY", son1Y, son1Y - 100);
			// transXAnimator.setDuration(300);
			// transYAnimator.setDuration(300);
			// transXAnimator.setInterpolator(new BounceInterpolator ());
			// transYAnimator.setInterpolator(new BounceInterpolator());
			// AnimatorSet set = new AnimatorSet();
			// set.playTogether(transXAnimator, transYAnimator);
			// set.start ();

		}
	}
	@Override
	public void onClick(View v) {
		goCenterButton(v);
	}

	public void setCenterButtonListener(CenterButtonListener listener) {
		this.listener = listener;
	}

	public interface CenterButtonListener {
		void openCenterButton();
		void closeCenterButton();
	}
}
