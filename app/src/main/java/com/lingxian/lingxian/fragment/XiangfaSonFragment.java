package com.lingxian.lingxian.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lingxian.lingxian.R;
import com.lingxian.lingxian.adapter.XiangfaRvAdapter;
import com.lingxian.lingxian.bean.XiangfaBean;
import com.lingxian.lingxian.divider.DividerItemDecoration;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 想法的子界面
 */
public class XiangfaSonFragment extends Fragment {

	public static int REFRESH_OK = 0x123;
	private RecyclerView rv;
	private List<XiangfaBean> beanList;
	private SwipeRefreshLayout srl;
	private XiangfaRvAdapter adapter;
	private MyHandler handler = new MyHandler(this);
	public XiangfaSonFragment() {
		// Required empty public constructor
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		handler.removeCallbacksAndMessages(null);
	}

	// TODO : 获取数据
	private void initData() {
		beanList = new ArrayList<>();

		for (int i = 0; i < 20; i++) {
			XiangfaBean bean = new XiangfaBean(i + "xxx的想法", "xxxxxx", null,
					null, "2016-7-10");

			beanList.add(bean);
		}
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
		adapter = new XiangfaRvAdapter(getContext(), beanList);
		rv.setLayoutManager(new LinearLayoutManager(getContext()));
		rv.setAdapter(adapter);
		rv.addItemDecoration(new DividerItemDecoration(getContext(),
				DividerItemDecoration.VERTICAL_LIST));

		srl = (SwipeRefreshLayout) view.findViewById(R.id.srl);
		srl.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
		srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				// TODO:刷新数据,完了发送消息修改界面
				Message message = handler.obtainMessage();
				message.what = REFRESH_OK;
				handler.sendMessage(message);
			}
		});
	}

	private static class MyHandler extends Handler {
		private WeakReference<Fragment> reference;

		public MyHandler(Fragment fragment) {
			reference = new WeakReference<>(fragment);
		}

		@Override
		public void handleMessage(Message msg) {
			XiangfaSonFragment frag = (XiangfaSonFragment) reference.get();
			if (frag != null) {
				if (msg.what == REFRESH_OK) {
					frag.adapter.notifyDataSetChanged();
					frag.srl.setRefreshing(false);
				}

			}
		}
	}
}
