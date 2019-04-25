package com.apps.kanchan.mcq.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apps.kanchan.mcq.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by root on 10/1/19.
 */

public class TestListViewAdapter extends ArrayAdapter<String> {
    ArrayList<String> mTestNames;
    Context mContext;
    public TestListViewAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
        mTestNames = (ArrayList<String>) objects;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.tests_listview,parent,false);
        }
        String testName = mTestNames.get(position);
        TextView testNameTextView = (TextView) view.findViewById(R.id.testNameTextView);
        testNameTextView.setText(testName);

        return view;
    }
}
