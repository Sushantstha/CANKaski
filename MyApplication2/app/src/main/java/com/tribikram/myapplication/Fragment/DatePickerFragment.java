package com.tribikram.myapplication.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Tribikram on 5/3/2018.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{


    private DateSetlistener dateSetlistener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this, year,month,day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        month++;

        dateSetlistener.onDatePicked(year, month, day);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dateSetlistener =(DateSetlistener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dateSetlistener =null;
    }

    public interface DateSetlistener{
        void onDatePicked(int year, int month,int day);
    }
}
