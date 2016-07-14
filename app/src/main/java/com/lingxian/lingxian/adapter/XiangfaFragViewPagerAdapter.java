package com.lingxian.lingxian.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Chan on 2016/5/27.
 *
 * 想法界面的ViewPager适配器
 */
public class XiangfaFragViewPagerAdapter extends FragmentStatePagerAdapter {

	private List<Fragment> fragmentList;
	private String[] tabTitleArray;
	private Context context;

	public XiangfaFragViewPagerAdapter(FragmentManager fm, Context context,
			List<Fragment> fragmentList, String[] tabTitleArray) {
		super(fm);
		this.context = context;
		this.fragmentList = fragmentList;
		this.tabTitleArray = tabTitleArray;
	}
	@Override
	public Fragment getItem(int position) {
		return fragmentList.get(position);
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}

	/* 重写与TabLayout配合 */

	@Override
	public CharSequence getPageTitle(int position) {
		return tabTitleArray[position % tabTitleArray.length];
	}
}
