package com.example.aswinvenkat.miniprj;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by aswin venkat on 4/20/2015.
 */
public class AssiFragmentA extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_assi, container, false);

        return rootView;
    }
}
