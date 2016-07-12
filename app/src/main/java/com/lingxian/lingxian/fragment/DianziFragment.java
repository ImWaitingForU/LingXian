package com.lingxian.lingxian.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lingxian.lingxian.R;
import com.lingxian.lingxian.adapter.SimpleAdapter;
import com.lingxian.lingxian.bean.DianziBean;
import com.lingxian.lingxian.divider.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DianziFragment extends Fragment {


    public DianziFragment() {
        // Required empty public constructor
    }


    private RecyclerView mRecyclerView;
    private List<DianziBean> mDatas;
    private SimpleAdapter mAdapter;
    //private LinearLayout linOfItem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dianzi, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initDatas();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
        mAdapter = new SimpleAdapter(getContext(), mDatas);
        mRecyclerView.setAdapter(mAdapter);
        //linOfItem= (LinearLayout) view.findViewById(R.id.lin_of_dianziitem);

        // 设置recyclerview的布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        // 设置recyclerview的Item分割线,通过抽象类来控制
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));


        mAdapter.setOnItemClickListener(new SimpleAdapter.OnItemClickListener() {

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getContext(), "longclick:" + position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "click:" + position, Toast.LENGTH_SHORT).show();

            }
        });
    }


    //TODO：从网络或者后台获取数据，待后台搭建

    /**
     * 初始化数据
     */
    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            DianziBean bean = new DianziBean(" "+i);
            mDatas.add(bean);
        }

    }

}
