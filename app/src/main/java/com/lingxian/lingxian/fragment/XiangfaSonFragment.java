package com.lingxian.lingxian.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lingxian.lingxian.R;
import com.lingxian.lingxian.adapter.XiangfaRvAdapter;
import com.lingxian.lingxian.bean.XiangfaBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 想法的子界面
 */
public class XiangfaSonFragment extends Fragment {
	private RecyclerView rv;
	private List<XiangfaBean> beanList;

	// TODO : 获取数据
	private void initData() {
		beanList = new ArrayList<>();

		for (int i = 0; i < 20; i++) {
			XiangfaBean bean = new XiangfaBean(i + "xxx的想法", "xxxxxx", null,
					null, "2016-7-10");

			beanList.add(bean);
		}
	}

	public XiangfaSonFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		initData();
		return inflater
				.inflate(R.layout.fragment_xiangfa_son, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		rv = (RecyclerView) view.findViewById(R.id.xiangfaFrag_rv);
		XiangfaRvAdapter adapter = new XiangfaRvAdapter(getContext(), beanList);
		rv.setLayoutManager(new LinearLayoutManager(getContext()));
		rv.setAdapter(adapter);
		rv.addItemDecoration(new RecyclerView.ItemDecoration() {
			@Override
			public void getItemOffsets(Rect outRect, View view,
					RecyclerView parent, RecyclerView.State state) {
				if (parent.getChildLayoutPosition(view) != 0) {
					outRect.top = 5;
				}
			}
		});
	}
}
