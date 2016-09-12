package com.hy.mdms.demo3;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2016/9/12.
 */
public class DatePickerFragment extends DialogFragment {
    public static final String EXTRA_DATE = "com.hy.mdms.demo3.date";
    public static final String EXTRA_TIME = "com.hy.mdms.demo3.time";
    private Date date;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        date = (Date) getArguments().getSerializable(EXTRA_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
        TimePicker datePicker = (TimePicker) v.findViewById(R.id.dialog_date_timePicker);
        datePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                date = calendar.getTime();
                //防止设备旋转数据丢失
                getArguments().putSerializable(EXTRA_DATE, date);
            }
        });
//        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                date = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
//                //防止设备旋转数据丢失
//                getArguments().putSerializable(EXTRA_DATE, date);
//            }
//        });
        return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.date_picker_title).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendResult(Activity.RESULT_OK);
            }
        }).create();
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
