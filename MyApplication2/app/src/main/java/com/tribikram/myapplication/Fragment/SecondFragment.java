package com.tribikram.myapplication.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tribikram.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private TextView tv_value;
    private String data;

    public SecondFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance(String arg1){
        SecondFragment frag = new SecondFragment();

        Bundle b = new Bundle();
        b.putString("param1", arg1);

        frag.setArguments(b);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        data = b.getString("param1");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_second, container, false);
        tv_value = layout.findViewById(R.id.tv_value);

        return layout;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_value.setText(data);
    }

    public void setValue(String result) {
        tv_value.setText(result);
    }

}
