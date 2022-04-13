package com.example.test8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    //获取内容布局资源id
    private int resourceId;

    public BookAdapter(@NonNull Context context, int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    //设置内容视图
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        //获取当前项的实例
        Book book = (Book) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView textView1 = (TextView) view.findViewById(R.id.textView13);
        TextView textView2 = (TextView) view.findViewById(R.id.textView14);
        TextView textView3 = (TextView) view.findViewById(R.id.textView15);
        textView1.setText(book.getName());
        textView2.setText(book.getStyle());
        textView3.setText(book.getPrice());
        return view;
    }
}