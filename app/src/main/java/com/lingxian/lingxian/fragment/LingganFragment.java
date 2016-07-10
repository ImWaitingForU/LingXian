package com.lingxian.lingxian.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lingxian.lingxian.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LingganFragment extends Fragment {


    public LingganFragment () {
        // Required empty public constructor
    }


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_linggan, container, false);
    }

}
