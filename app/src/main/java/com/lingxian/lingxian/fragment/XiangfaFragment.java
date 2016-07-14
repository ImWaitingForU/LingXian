package com.lingxian.lingxian.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.lingxian.lingxian.R;
import com.lingxian.lingxian.activity.AddTabActivity;
import com.lingxian.lingxian.adapter.XiangfaFragViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 想法界面: 包括头部分类
 */
public class XiangfaFragment extends Fragment {

    public static final int REQUESTCODE = 0;
    private TabLayout tabLayout;
    private ImageButton addTab;
    private List<Fragment> fragmentList;
    private XiangfaFragViewPagerAdapter adapter;
    private List<String> tabTitleList = new ArrayList<> ();

    public XiangfaFragment () {
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_xiangfa, container, false);
    }

    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        initViewPager (view);
    }

    private void initViewPager (View view) {

        tabTitleList.add ("热门");
        tabTitleList.add ("生活");
        tabTitleList.add ("搞怪");
        tabTitleList.add ("娱乐");
        tabTitleList.add ("专业");
        tabTitleList.add ("音乐");
        tabTitleList.add ("运动");

        ViewPager viewPager = (ViewPager) view.findViewById (R.id.xiangfaFrag_vp);
        tabLayout = (TabLayout) view.findViewById (R.id.tabLayout);

        fragmentList = new ArrayList<> ();

        XiangfaSonFragment sonFragment1 = new XiangfaSonFragment ();
        fragmentList.add (sonFragment1);
        XiangfaSonFragment sonFragment2 = new XiangfaSonFragment ();
        fragmentList.add (sonFragment2);
        XiangfaSonFragment sonFragment3 = new XiangfaSonFragment ();
        fragmentList.add (sonFragment3);
        XiangfaSonFragment sonFragment4 = new XiangfaSonFragment ();
        fragmentList.add (sonFragment4);
        XiangfaSonFragment sonFragment5 = new XiangfaSonFragment ();
        fragmentList.add (sonFragment5);
        XiangfaSonFragment sonFragment6 = new XiangfaSonFragment ();
        fragmentList.add (sonFragment6);
        XiangfaSonFragment sonFragment7 = new XiangfaSonFragment ();
        fragmentList.add (sonFragment7);

        adapter = new XiangfaFragViewPagerAdapter (getActivity ().getSupportFragmentManager (), fragmentList,
                                                   tabTitleList);
        viewPager.setAdapter (adapter);
        viewPager.setOffscreenPageLimit (3);

        tabLayout.setupWithViewPager (viewPager);

        // 点击+跳转到选择标签界面
        addTab = (ImageButton) view.findViewById (R.id.xiangfaFrag_addtab);
        addTab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent (getContext (), AddTabActivity.class);
                startActivityForResult (intent, REQUESTCODE);
            }
        });
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == AddTabActivity.RESULTCODE) {
            List<String> resultList = (List<String>) data.getSerializableExtra ("resultTags");
            for (String tag : resultList) {
                tabTitleList.add (tag);
                fragmentList.add (new XiangfaSonFragment ());
            }
            adapter.notifyDataSetChanged ();
        }
    }
}
