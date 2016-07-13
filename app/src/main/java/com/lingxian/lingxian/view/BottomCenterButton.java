package com.lingxian.lingxian.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lingxian.lingxian.R;

/**
 * Created by Chan on 2016/7/12.
 *
 */
public class BottomCenterButton extends RelativeLayout
		implements
			View.OnClickListener {

	private Context context;
	private int centerIconSrc;
	private int firstSonIconSrc;
	private int secondSonIconSrc;
	private int thirdSonIconSrc;
	private String firstSonIconText;
	private String secondSonIconText;
	private String thirdSonIconText;
	public static boolean isOpen = false; // 判断中间按钮是否打开
	public static final String CENTERBUTTON = "CenterButton";
	public static final String FIRSTSONBUTTON = "FirstSonButton";
	public static final String SECONDSONBUTTON = "SecondSonButton";
	public static final String THIRDSONBUTTON = "ThirdSonButton";
	public static final String BACKVIEW = "BackView";
	private View backView; // 背景图片
	private ImageButton centerButton;
	private LinearLayout sonButton1;
	private LinearLayout sonButton2;
	private LinearLayout sonButton3;
	private SonButtonClickListener listener;
	private boolean isCloseAfterClicked = true;

	public BottomCenterButton(Context context) {
		super(context);
		this.context = context;
	}

	public BottomCenterButton(Context context, AttributeSet attrs)
			throws MissingPropertiesException {
		super(context, attrs);
		this.context = context;

		// 从xml读取
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
		                                                       R.styleable.BottomCenterButton);
		centerIconSrc = typedArray.getResourceId(
				R.styleable.BottomCenterButton_centerIconSrc, 0);
		firstSonIconSrc = typedArray.getResourceId(
				R.styleable.BottomCenterButton_firstSonIconSrc, 0);
		secondSonIconSrc = typedArray.getResourceId(
				R.styleable.BottomCenterButton_secondSonIconSrc, 0);
		thirdSonIconSrc = typedArray.getResourceId(
				R.styleable.BottomCenterButton_thirdSonIconSrc, 0);
		firstSonIconText = typedArray
				.getString(R.styleable.BottomCenterButton_firstSonIconText);
		secondSonIconText = typedArray
				.getString(R.styleable.BottomCenterButton_secondSonIconText);
		thirdSonIconText = typedArray
				.getString(R.styleable.BottomCenterButton_thirdSonIconText);

		if (centerIconSrc == 0 | firstSonIconSrc == 0
				| firstSonIconText == null | secondSonIconSrc == 0
				| secondSonIconText == null | thirdSonIconSrc == 0
				| thirdSonIconText == null) {
			throw new MissingPropertiesException("请确保初始的每个按钮的图标和所有子按钮的文字都设置好~~");
		} else {
			initCenterButton();
		}

		typedArray.recycle();
	}

	/**
	 * 设置是否点击子按钮后回收子按钮
	 * 
	 * @param isCloseAfterClicked
	 *            是否在点击后收回子按钮，默认是收回
	 */
	public void setIsCloseAfterClick(boolean isCloseAfterClicked) {
		this.isCloseAfterClicked = isCloseAfterClicked;
	}

	@Override
	public void onClick(View v) {
		String tag = (String) v.getTag();
		switch (tag) {
			case CENTERBUTTON :
				rotateCenterButton(v);
				break;
			case FIRSTSONBUTTON :
				listener.onSonButton1Clicked(sonButton1);
				if (isCloseAfterClicked) {
					rotateCenterButton(centerButton);
				}
				break;
			case SECONDSONBUTTON :
				listener.onSonButton2Clicked(sonButton2);
				if (isCloseAfterClicked) {
					rotateCenterButton(centerButton);
				}
				break;
			case THIRDSONBUTTON :
				listener.onSonButton3Clicked(sonButton3);
				if (isCloseAfterClicked) {
					rotateCenterButton(centerButton);
				}
				break;
			case BACKVIEW :
				rotateCenterButton(centerButton);
				break;
			default :
				break;
		}
	}
	private void initCenterButton() {
		ViewGroup.LayoutParams mainLp = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		this.setLayoutParams(mainLp);

		// 添加背景
		backView = new View(context);
		backView.setBackgroundColor(Color.BLACK);
		backView.setAlpha(0.7f);
		backView.setVisibility(GONE);
		backView.setTag(BACKVIEW);
		backView.setOnClickListener(this);
		LayoutParams backViewLp = new LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		this.addView(backView, backViewLp);

		// 添加CenterButton
		centerButton = new ImageButton(context);
		centerButton.setImageResource(centerIconSrc);
		centerButton.setOnClickListener(this);
		centerButton.setBackgroundColor(Color.TRANSPARENT);
		centerButton.setTag(CENTERBUTTON);
		LayoutParams centerLp = new LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		centerLp.addRule(ALIGN_PARENT_BOTTOM);
		centerLp.addRule(CENTER_HORIZONTAL);
		this.addView(centerButton, centerLp);

		// 添加子按钮
		sonButton1 = addSonButton(context, firstSonIconText, firstSonIconSrc);
		sonButton2 = addSonButton(context, secondSonIconText, secondSonIconSrc);
		sonButton3 = addSonButton(context, thirdSonIconText, thirdSonIconSrc);
		sonButton1.setTag(FIRSTSONBUTTON);
		sonButton2.setTag(SECONDSONBUTTON);
		sonButton3.setTag(THIRDSONBUTTON);
		sonButton1.setOnClickListener(this);
		sonButton2.setOnClickListener(this);
		sonButton3.setOnClickListener(this);
	}

	/**
	 * 回收子按钮
	 */
	private void closeSonButtons() {
		// 关闭背景
		backView.setVisibility(GONE);
		// 回收子按钮
		toggleSonButtons(sonButton1, sonButton1.getX(), sonButton1.getY(),
				-180f, -180f);
		toggleSonButtons(sonButton2, sonButton2.getX(), sonButton2.getY(), 0f,
				-250f);
		toggleSonButtons(sonButton3, sonButton3.getX(), sonButton3.getY(),
				180f, -180f);
	}

	/**
	 * 打开子按钮
	 *
	 */
	private void openSonButtons() {
		// 打开背景
		backView.setVisibility(VISIBLE);
		// 打开子按钮
		// 当按钮打开,弹出三个子按钮
		float startX = centerButton.getX() + 30;
		toggleSonButtons(sonButton1, startX, centerButton.getY(), 180f, 180f);
		toggleSonButtons(sonButton2, startX, centerButton.getY(), 0f, 250f);
		toggleSonButtons(sonButton3, startX, centerButton.getY(), -180f, 180f);
	}

	/**
	 * 打开和关闭旋转中间按钮的动画,在点击事件触发
	 *
	 * @param view
	 *            传入centerButton保证动画进行时不会重复开启动画
	 *
	 * @return 旋转中间按钮动画
	 */
	private void rotateCenterButton(final View view) {
		RotateAnimation rotateAnimation;
		if (isOpen) {
			// 打开—>关闭
			closeSonButtons();
			rotateAnimation = new RotateAnimation(135, 0,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
		} else {
			// 关闭->打开
			openSonButtons();
			rotateAnimation = new RotateAnimation(0, 135,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
		}
		rotateAnimation.setDuration(400);
		rotateAnimation.setFillAfter(true);
		rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				view.setClickable(false);
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				view.setClickable(true);
				if (isOpen) {
					isOpen = false;
				} else {
					isOpen = true;
				}
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		view.startAnimation(rotateAnimation);
	}

	/**
	 * 切换子按钮的动画
	 *
	 * @param target
	 *            子按钮
	 * @param startX
	 *            开始X
	 * @param startY
	 *            开始Y
	 * @param deltaX
	 *            X变化
	 * @param deltaY
	 *            Y变化
	 */
	private void toggleSonButtons(final View target, float startX,
			float startY, float deltaX, float deltaY) {
		target.setVisibility(View.VISIBLE);
		AnimatorSet set = new AnimatorSet();

		ObjectAnimator transXAnimator = ObjectAnimator.ofFloat(target,
				"translationX", startX, startX - deltaX);
		ObjectAnimator transYAnimator = ObjectAnimator.ofFloat(target,
				"translationY", startY, startY - deltaY);
		// 新增放大缩小动画，简单的通过传入的值判断是关闭还是打开
		ObjectAnimator scaleXAnimator;
		ObjectAnimator scaleYAnimator;
		if (deltaY > 0f) {
			// 弹出，进行放大
			scaleXAnimator = ObjectAnimator.ofFloat(target, "scaleX", 0.0f,
					1.0f);
			scaleYAnimator = ObjectAnimator.ofFloat(target, "scaleY", 0.0f,
					1.0f);
		} else {
			// 回收，进行缩小
			scaleXAnimator = ObjectAnimator.ofFloat(target, "scaleX", 1.0f,
					0.0f);
			scaleYAnimator = ObjectAnimator.ofFloat(target, "scaleY", 1.0f,
					0.0f);
		}

		set.playTogether(transXAnimator, transYAnimator, scaleXAnimator,
				scaleYAnimator);
		set.setDuration(400);
		set.setInterpolator(new BounceInterpolator());
		set.setTarget(target);
		set.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				if (isOpen) {
					target.setVisibility(View.GONE);
				}
			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}
		});
		set.start();
	}

	/**
	 * 添加子按钮,每个子按钮看起来是ImageButton，其实包括下面的文字 ，组成一个LinearLayout
	 *
	 * @param context
	 *            上下文
	 * @param title
	 *            子按钮下的文本提示
	 * @param resId
	 *            子按钮图标
	 * @return 新建的子按钮
	 */
	private LinearLayout addSonButton(Context context, String title, int resId) {
		LinearLayout sonLinearLayout = new LinearLayout(context);
		sonLinearLayout.setOrientation(LinearLayout.VERTICAL);
		sonLinearLayout.setBackgroundColor(Color.TRANSPARENT);

		ImageButton sonButton = new ImageButton(context);
		sonButton.setImageResource(resId);
		sonButton.setBackgroundColor(Color.TRANSPARENT);
		sonButton.setTag(title); // tag标识触发点击事件的条件
		sonButton.setClickable(false);
		// sonButton.setOnClickListener(this);

		TextView sonTv = new TextView(context);
		sonTv.setText(title);
		sonTv.setTextSize(14);
		sonTv.setTextColor(Color.WHITE);
		sonTv.setGravity(Gravity.CENTER);

		sonLinearLayout.addView(sonButton);
		sonLinearLayout.addView(sonTv);
		sonLinearLayout.setVisibility(View.GONE);
		this.addView(sonLinearLayout);

		return sonLinearLayout;
	}

	public void setOnSonButtonClickListener(SonButtonClickListener listener) {
		this.listener = listener;
	}

	public interface SonButtonClickListener {
		void onSonButton1Clicked (View sonButton1);
		void onSonButton2Clicked (View sonButton2);
		void onSonButton3Clicked (View sonButton3);
	}

	public int getCenterIconSrc() {
		return centerIconSrc;
	}

	public void setCenterIconSrc(int centerIconSrc) {
		this.centerIconSrc = centerIconSrc;
	}

	public int getFirstSonIconSrc() {
		return firstSonIconSrc;
	}

	public void setFirstSonIconSrc(int firstSonIconSrc) {
		this.firstSonIconSrc = firstSonIconSrc;
	}

	public int getSecondSonIconSrc() {
		return secondSonIconSrc;
	}

	public void setSecondSonIconSrc(int secondSonIconSrc) {
		this.secondSonIconSrc = secondSonIconSrc;
	}

	public int getThirdSonIconSrc() {
		return thirdSonIconSrc;
	}

	public void setThirdSonIconSrc(int thirdSonIconSrc) {
		this.thirdSonIconSrc = thirdSonIconSrc;
	}

	public String getFirstSonIconText() {
		return firstSonIconText;
	}

	public void setFirstSonIconText(String firstSonIconText) {
		this.firstSonIconText = firstSonIconText;
	}

	public String getSecondSonIconText() {
		return secondSonIconText;
	}

	public void setSecondSonIconText(String secondSonIconText) {
		this.secondSonIconText = secondSonIconText;
	}

	public String getThirdSonIconText() {
		return thirdSonIconText;
	}

	public void setThirdSonIconText(String thirdSonIconText) {
		this.thirdSonIconText = thirdSonIconText;
	}
}
