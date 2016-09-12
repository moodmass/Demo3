package com.hy.mdms.demo3;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/6.
 */
public class CrimeListFragment extends ListFragment {
    private static final String TAG = "CrimeListFragment";
    private ArrayList<Crime> crimes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crime_title);
        crimes = CrimeLab.get(getActivity()).getCrimes();

        CrimeAdapter crimeAdapter = new CrimeAdapter(crimes);
        setListAdapter(crimeAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Crime crime = (Crime) (getListAdapter()).getItem(position);
        Intent intent = new Intent(getActivity(), CrimePagerActivity.class);
        intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
        startActivity(intent);
//        Log.d(TAG, "onListItemClick: "+crime.getTitle());
//        Toast.makeText(getActivity(), crime.getTitle(), Toast.LENGTH_SHORT).show();
    }

    private  class CrimeAdapter extends ArrayAdapter<Crime> {

        public CrimeAdapter(ArrayList<Crime> crimes){
            super(getActivity(),0,crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_crime,null);
            }
            Crime crime = getItem(position);
            TextView titleTextView = (TextView) convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(crime.getTitle());
            TextView dateTextView = (TextView) convertView.findViewById(R.id.crime_list_item_dateTextView);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateTextView.setText(sdf.format(crime.getDate()));
            CheckBox solvedCheckBox = (CheckBox) convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(crime.isSolved());
            return convertView;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }
}
