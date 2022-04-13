package com.example.test8;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase db;
    private List<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //构造数据库Library,版本为1
        myDatabaseHelper = new MyDatabaseHelper(this,"Library.db",null, 2);
        //创建打开数据库和表
        db = myDatabaseHelper.getWritableDatabase();
        //初始化图书条目信息
        initBook();
        BookAdapter bookAdapter = new BookAdapter(MainActivity.this,R.layout.book_item,books);
        ListView listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(bookAdapter);
        //添加按钮，点击按钮进入添加书籍界面
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.close();
                myDatabaseHelper.close();
                Intent intent = new Intent(MainActivity.this,AddBookActivity.class);
                startActivity(intent);
            }
        });
    }

    //设置图书条目信息初始化方法
    @SuppressLint("Range")
    public void initBook(){
        //需要查询的列
        String[] columns = new String[]{"name","category_name","price"};
        //设置查询语句
        Cursor cursor = db.rawQuery("select name,category_name,price from Book," +
                "                       CateGory where category_id = category_code",null);
        String name;
        String style;
        String price;
        if (cursor.moveToFirst()){//遍历对象
            do {
                //添加数据
                name = cursor.getString(cursor.getColumnIndex("name"));
                style = cursor.getString(cursor.getColumnIndex("category_name"));
                price = cursor.getString(cursor.getColumnIndex("price"));
                Log.d(TAG, name + "--" +style + "--" + price);
                Book book = new Book(name,style,price);
                books.add(book);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}