package com.lingxian.lingxian.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lingxian.lingxian.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WodeFragment extends Fragment {

    //private Button quit;
    //private Context context;

    public WodeFragment() {

    }

    //TODO:内部按钮和控件还没实现功能

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wode, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //quit = (Button) view.findViewById(R.id.id_btn_quit);
        initEvents();

    }

    //TODO:控件功能待定
    private void initEvents() {
    }
}
