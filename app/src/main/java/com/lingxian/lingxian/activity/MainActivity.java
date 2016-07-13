package com.lingxian.lingxian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.lingxian.lingxian.R;
import com.lingxian.lingxian.fragment.DianziFragment;
import com.lingxian.lingxian.fragment.LingganFragment;
import com.lingxian.lingxian.fragment.WodeFragment;
import com.lingxian.lingxian.fragment.XiangfaFragment;
import com.lingxian.lingxian.view.BottomCenterButton;
import com.lingxian.lingxian.view.IwfuBottomBar;

public class MainActivity extends FragmentActivity
		implements
			View.OnClickListener {

	private FrameLayout contentMain;
	private RelativeLayout main_rl;
	private Fragment curFragment; // 代表当前显示的Fragment
	private XiangfaFragment tab1Fragment;
	private DianziFragment tab2Fragment;
	private LingganFragment tab3Fragment;
	private WodeFragment tab4Fragment;
	private BottomCenterButton centerButton;
//	private LinearLayout sonLinearLayout1;
//	private LinearLayout sonLinearLayout2;
//	private LinearLayout sonLinearLayout3;
	private ImageButton ib_search;
	private ImageButton ib_message;

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
		contentMain = (FrameLayout) findViewById(R.id.content_main);
		main_rl = (RelativeLayout) findViewById(R.id.main_rl);
		IwfuBottomBar ibb = (IwfuBottomBar) findViewById(R.id.ibb);
		// 初始化toolbar
		final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("想法");

		ibb.bindFragmentSwitcherListener(new IwfuBottomBar.FragmentSwichterListener() {
			@Override
			public void onTab1Selected() {
				toolbar.setTitle("想法");
				if (tab1Fragment == null) {
					tab1Fragment = new XiangfaFragment();
				}
				switchFragment(curFragment, tab1Fragment);
			}

			@Override
			public void onTab2Selected() {
				toolbar.setTitle("点子");
				if (tab2Fragment == null) {
					tab2Fragment = new DianziFragment();
				}
				switchFragment(curFragment, tab2Fragment);
			}

			@Override
			public void onTab3Selected() {
				toolbar.setTitle("灵感");
				if (tab3Fragment == null) {
					tab3Fragment = new LingganFragment();
				}
				switchFragment(curFragment, tab3Fragment);

			}

			@Override
			public void onTab4Selected() {
				toolbar.setTitle("我的");
				if (tab4Fragment == null) {
					tab4Fragment = new WodeFragment();
				}
				switchFragment(curFragment, tab4Fragment);
			}
		});

		centerButton = (BottomCenterButton) findViewById(R.id.centerButton);
		centerButton.setOnSonButtonClickListener (new BottomCenterButton.SonButtonClickListener () {
			@Override
			public void onSonButton1Clicked (View sonButton1) {
				Log.d ("tag", "sonButton1 clicked~~~");
			}

			@Override
			public void onSonButton2Clicked (View sonButton2) {
				Log.d ("tag","sonButton2 clicked~~~");

			}

			@Override
			public void onSonButton3Clicked (View sonButton3) {
						Log.d("tag", "sonButton3 clicked~~~");

			}
		});


		ib_search = (ImageButton) findViewById(R.id.search);
		ib_message = (ImageButton) findViewById(R.id.message);

		ib_search.setOnClickListener(this);
		ib_message.setOnClickListener(this);
	}

	// /**
	// * 切换子按钮的动画
	// *
	// * @param target
	// * 子按钮
	// * @param startX
	// * 开始X
	// * @param startY
	// * 开始Y
	// * @param deltaX
	// * X变化
	// * @param deltaY
	// * Y变化
	// */
	// private void toggleSonButtons(final View target, float startX,
	// float startY, float deltaX, float deltaY) {
	// target.setVisibility(View.VISIBLE);
	// AnimatorSet set = new AnimatorSet();
	//
	// ObjectAnimator transXAnimator = ObjectAnimator.ofFloat(target,
	// "translationX", startX, startX - deltaX);
	// ObjectAnimator transYAnimator = ObjectAnimator.ofFloat(target,
	// "translationY", startY, startY - deltaY);
	// //新增放大缩小动画，简单的通过传入的值判断是关闭还是打开
	// ObjectAnimator scaleXAnimator = null;
	// ObjectAnimator scaleYAnimator = null;
	// if (deltaY>0f){
	// //弹出，进行放大
	// scaleXAnimator = ObjectAnimator.ofFloat (target,"scaleX",0.0f,1.0f);
	// scaleYAnimator = ObjectAnimator.ofFloat (target,"scaleY",0.0f,1.0f);
	// }else{
	// //回收，进行缩小
	// scaleXAnimator = ObjectAnimator.ofFloat (target,"scaleX",1.0f,0.0f);
	// scaleYAnimator = ObjectAnimator.ofFloat (target,"scaleY",1.0f,0.0f);
	// }
	//
	// set.playTogether(transXAnimator,
	// transYAnimator,scaleXAnimator,scaleYAnimator);
	// set.setDuration(400);
	// set.setInterpolator(new BounceInterpolator());
	// set.setTarget(target);
	// set.addListener(new Animator.AnimatorListener() {
	// @Override
	// public void onAnimationStart(Animator animation) {
	//
	// }
	//
	// @Override
	// public void onAnimationEnd(Animator animation) {
	// if (IwfuCenterButton.isOpen) {
	// target.setVisibility(View.GONE);
	// }
	// }
	//
	// @Override
	// public void onAnimationCancel(Animator animation) {
	//
	// }
	//
	// @Override
	// public void onAnimationRepeat(Animator animation) {
	//
	// }
	// });
	// set.start();
	// }
	//
	// /**
	// * 添加子按钮
	// *
	// * @param context
	// * 上下文
	// * @param title
	// * 子按钮下的文本提示
	// * @param resId
	// * 子按钮图标
	// * @return 新建的子按钮
	// */
	// private LinearLayout addSonLinearLayout(Context context, String title,
	// int resId) {
	// LinearLayout sonLinearLayout = new LinearLayout(context);
	// sonLinearLayout.setOrientation(LinearLayout.VERTICAL);
	// sonLinearLayout.setBackgroundColor(Color.TRANSPARENT);
	//
	// ImageButton sonButton = new ImageButton(context);
	// sonButton.setImageResource(resId);
	// sonButton.setBackgroundColor(Color.TRANSPARENT);
	// sonButton.setTag(title);
	// sonButton.setOnClickListener(this);
	//
	// TextView sonTv = new TextView(context);
	// sonTv.setText(title);
	// sonTv.setTextSize(12);
	// sonTv.setTextColor(Color.BLACK);
	// sonTv.setGravity(Gravity.CENTER);
	// sonTv.setPadding(5, 5, 5, 5);
	// sonTv.setBackgroundColor(Color.GRAY);
	//
	// sonLinearLayout.addView(sonButton);
	// sonLinearLayout.addView(sonTv);
	// sonLinearLayout.setVisibility(View.GONE);
	// main_rl.addView(sonLinearLayout);
	//
	// return sonLinearLayout;
	// }

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.search) {
			startActivity(new Intent(MainActivity.this, SearchActivity.class));
		} else if (v.getId() == R.id.message) {
			startActivity(new Intent(MainActivity.this, MessageActivity.class));
		}
	}
}