package com.example.practice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class index_fragment extends Fragment {
    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view = LayoutInflater.from(getActivity()).inflate(R.layout.index_fragment, null);
         return view;
     }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView()!=null){
            this.getView().setVisibility(menuVisible ? View.VISIBLE:View.GONE);
        }
    }
}
