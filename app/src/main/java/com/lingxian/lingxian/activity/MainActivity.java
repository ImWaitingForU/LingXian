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
				startActivity (new Intent (MainActivity.this,EditXiangfaActivity.class));
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

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.search) {
			startActivity(new Intent(MainActivity.this, SearchActivity.class));
		} else if (v.getId() == R.id.message) {
			startActivity(new Intent(MainActivity.this, MessageActivity.class));
		}
	}
}