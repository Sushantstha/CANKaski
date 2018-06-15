package com.tribikram.myapplication.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tribikram.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    private EditText et_name;
    private Button btn_display;
    private OnFragmentInteraction OnFragmentInteraction;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        et_name = view.findViewById(R.id.et_name);
        btn_display = view.findViewById(R.id.btn_display);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = et_name.getText().toString();
                /*Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT).show();*/
                OnFragmentInteraction.onClick(value);
            }
        });
    }

    //Interface
    public interface OnFragmentInteraction {
        void onClick(String data);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            OnFragmentInteraction = (OnFragmentInteraction) context;
        }catch(ClassCastException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        OnFragmentInteraction = null;
    }
}