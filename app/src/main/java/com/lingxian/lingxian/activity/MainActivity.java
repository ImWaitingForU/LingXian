package com.lingxian.lingxian.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lingxian.lingxian.R;
import com.lingxian.lingxian.fragment.DianziFragment;
import com.lingxian.lingxian.fragment.LingganFragment;
import com.lingxian.lingxian.fragment.WodeFragment;
import com.lingxian.lingxian.fragment.XiangfaFragment;
import com.lingxian.lingxian.view.IwfuBottomBar;
import com.lingxian.lingxian.view.IwfuCenterButton;

public class MainActivity extends FragmentActivity
		implements
			View.OnClickListener {

	private RelativeLayout main_rl;
	private Fragment curFragment; // 代表当前显示的Fragment
	private XiangfaFragment tab1Fragment;
	private DianziFragment tab2Fragment;
	private LingganFragment tab3Fragment;
	private WodeFragment tab4Fragment;
	private IwfuCenterButton centerButton;
	private LinearLayout sonLinearLayout1;
	private LinearLayout sonLinearLayout2;
	private LinearLayout sonLinearLayout3;

	/**
	 * 为页面加载初始状态的Fragment
	 */
	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		if (curFragment == null) {
			tab1Fragment = new XiangfaFragment();
		}
		curFragment = tab1Fragment;
		ft.replace(R.id.content_main, tab1Fragment).commit();
	}

	/**
	 * 切换fragment,隐藏之前的Fragment,且可以保存fragment数据，保证切换fragment数据不会清空
	 */
	private void switchFragment(Fragment from, Fragment to) {
		if (curFragment != to) {
			curFragment = to;
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			if (!to.isAdded()) {
				// 如果to没有添加，则先隐藏之前的再add
				ft.hide(from).add(R.id.content_main, to).commit();
			} else {
				ft.hide(from).show(to).commit();
			}
		}
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initFragment();
		init();
	}

	private void init() {
		main_rl = (RelativeLayout) findViewById(R.id.main_rl);
		IwfuBottomBar ibb = (IwfuBottomBar) findViewById(R.id.ibb);
		ibb.bindFragmentSwitcherListener(new IwfuBottomBar.FragmentSwichterListener() {
			@Override
			public void onTab1Selected() {
				if (tab1Fragment == null) {
					tab1Fragment = new XiangfaFragment();
				}
				switchFragment(curFragment, tab1Fragment);
			}

			@Override
			public void onTab2Selected() {
				if (tab2Fragment == null) {
					tab2Fragment = new DianziFragment();
				}
				switchFragment(curFragment, tab2Fragment);
			}

			@Override
			public void onTab3Selected() {
				if (tab3Fragment == null) {
					tab3Fragment = new LingganFragment();
				}
				switchFragment(curFragment, tab3Fragment);

			}

			@Override
			public void onTab4Selected() {
				if (tab4Fragment == null) {
					tab4Fragment = new WodeFragment();
				}
				switchFragment(curFragment, tab4Fragment);
			}
		});

		// 初始化按钮
		sonLinearLayout1 = addSonLinearLayout(this, "编辑想法",
				R.drawable.ic_sonbutton1);
		sonLinearLayout2 = addSonLinearLayout(this, "编辑点子",
				R.drawable.ic_sonbutton2);
		sonLinearLayout3 = addSonLinearLayout(this, "编辑灵感",
				R.drawable.ic_sonbutton3);

		centerButton = (IwfuCenterButton) findViewById(R.id.centerButton);
		centerButton
				.setCenterButtonListener(new IwfuCenterButton.CenterButtonListener() {
					@Override
					public void openCenterButton() {
						// 当按钮打开,弹出三个子按钮
						float startX = centerButton.getX() + 10;
						toggleSonButtons(sonLinearLayout1, startX,
								centerButton.getY(), 180f, 180f);
						toggleSonButtons(sonLinearLayout2, startX,
								centerButton.getY(), 0f, 250f);
						toggleSonButtons(sonLinearLayout3, startX,
								centerButton.getY(), -180f, 180f);
					}

					@Override
					public void closeCenterButton() {
						// 当按钮关闭,回收三个子按钮
						toggleSonButtons(sonLinearLayout1,
								sonLinearLayout1.getX(),
								sonLinearLayout1.getY(), -180f, -180f);
						toggleSonButtons(sonLinearLayout2,
								sonLinearLayout2.getX(),
								sonLinearLayout2.getY(), 0f, -250f);
						toggleSonButtons(sonLinearLayout3,
								sonLinearLayout3.getX(),
								sonLinearLayout3.getY(), 180f, -180f);
					}
				});
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

		set.playTogether(transXAnimator, transYAnimator);
		set.setDuration(400);
		set.setInterpolator(new BounceInterpolator());
		set.setTarget(target);
		set.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				if (IwfuCenterButton.isOpen) {
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
	 * 添加子按钮
	 *
	 * @param context
	 *            上下文
	 * @param title
	 *            子按钮下的文本提示
	 * @param resId
	 *            子按钮图标
	 * @return 新建的子按钮
	 */
	private LinearLayout addSonLinearLayout(Context context, String title,
			int resId) {
		LinearLayout sonLinearLayout = new LinearLayout(context);
		sonLinearLayout.setOrientation(LinearLayout.VERTICAL);
		sonLinearLayout.setBackgroundColor(Color.TRANSPARENT);

		ImageButton sonButton = new ImageButton(context);
		sonButton.setImageResource(resId);
		sonButton.setBackgroundColor(Color.TRANSPARENT);
		sonButton.setTag(title);
		sonButton.setOnClickListener(this);

		TextView sonTv = new TextView(context);
		sonTv.setText(title);
		sonTv.setTextSize(12);
		sonTv.setTextColor(Color.BLACK);
		sonTv.setGravity(Gravity.CENTER);
		sonTv.setPadding(5, 5, 5, 5);
		sonTv.setBackgroundColor(Color.GRAY);

		sonLinearLayout.addView(sonButton);
		sonLinearLayout.addView(sonTv);
		sonLinearLayout.setVisibility(View.GONE);
		main_rl.addView(sonLinearLayout);

		return sonLinearLayout;
	}

	@Override
	public void onClick(View v) {
		if (v.getTag().equals("编辑想法")) {
			Log.d("tag", "sonLL1 pressed");
		} else if (v.getTag().equals("编辑点子")) {
			Log.d("tag", "sonLL2 pressed");

		} else if (v.getTag().equals("编辑灵感")) {
			Log.d("tag", "sonLL3 pressed");

		}
	}
}