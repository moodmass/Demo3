package com.hy.mdms.demo3;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/9/5.
 */
public class CrimeFragment extends Fragment {
    public static final String EXTRA_CRIME_ID = "com.hy.mdms.demo3.CrimeListFragment.EXTRA_CRIME_ID";
    private static final String DIALOG_DATE="date";
    private static final int REQUEST_DATE=0;

    @BindView(R.id.crime_title)
    EditText crimeTitle;
    @BindView(R.id.crime_date)
    Button crimeDate;
    @BindView(R.id.crime_solved)
    CheckBox crimeSolved;
    private Crime crime;

    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args=new Bundle();
        args.putSerializable(EXTRA_CRIME_ID,crimeId);
        CrimeFragment crimeFragment = new CrimeFragment();
        crimeFragment.setArguments(args);
        return crimeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID uuid = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);
        crime = CrimeLab.get(getActivity()).getCrime(uuid);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        ButterKnife.bind(this, v);
        crimeTitle.setText(crime.getTitle());
       updateDate();
//        crimeDate.setEnabled(false);
        crimeDate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(crime.getDate());
                dialog.setTargetFragment(CrimeFragment.this,REQUEST_DATE);
                dialog.show(fm,DIALOG_DATE);
            }
        });
        crimeSolved.setChecked(crime.isSolved());

        crimeSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                crime.setSolved(isChecked);
            }
        });
        return v;
    }

    @OnClick({R.id.crime_title, R.id.crime_date})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.crime_title:
                break;
            case R.id.crime_date:
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode!= Activity.RESULT_OK)return ;
        if(requestCode==REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            crime.setDate(date);
            updateDate();
        }
    }

    public void updateDate(){
        crimeDate.setText(crime.getFormatDate());
    }
}
