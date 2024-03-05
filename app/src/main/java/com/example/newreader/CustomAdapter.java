package com.example.newreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<DocBao> {
    public CustomAdapter(Context context, int resource, List<DocBao> items) {
        super(context, resource, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.dong_layout_listview, null);
        }
        DocBao p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txtTitle = (TextView) view.findViewById(R.id.textViewTitle);
            txtTitle.setText(p.getTitle());
            ImageView imgHinhAnh = (ImageView) view.findViewById(R.id.imageViewHinhAnh);
            Picasso.with(getContext()).load(p.getImage()).into(imgHinhAnh);
        }
        return view;

    }
}
