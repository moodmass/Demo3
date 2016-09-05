package com.hy.mdms.demo3;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/9/5.
 */
public class CrimeFragment extends Fragment {
    @BindView(R.id.crime_title)
    EditText crimeTitle;
    @BindView(R.id.crime_date)
    Button crimeDate;
    @BindView(R.id.crime_solved)
    CheckBox crimeSolved;
    private Crime crime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crime = new Crime();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        ButterKnife.bind(this, v);
        crimeDate.setText(crime.getDate().toString());
        crimeDate.setEnabled(false);

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
}
