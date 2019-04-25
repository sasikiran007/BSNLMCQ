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

import java.util.List;

/**
 * Created by root on 10/1/19.
 */

public class CategoryArrayAdapter extends ArrayAdapter<String> {
    private List<String> categories;
    private Context mContext;
    public CategoryArrayAdapter(@NonNull Context context,  @NonNull List<String> objects) {
        super(context, 0, objects);
        mContext = context;
        categories = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.test_category_list_item,parent,false);
        }
        String categoryName = categories.get(position);
        TextView categoryTextView = (TextView) view.findViewById(R.id.categoryTextView);
        categoryTextView.setText(categoryName);
        return  view;
    }
}
