package com.example.test8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {

    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        //构造数据库Library,版本为1
        myDatabaseHelper = new MyDatabaseHelper(this,"Library.db",null, 2);
        //打开数据库
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        //实例化容器，用于将数据包装存入数据库
        ContentValues values = new ContentValues();
        EditText author = (EditText) findViewById(R.id.text2);
        EditText price = (EditText) findViewById(R.id.text3);
        EditText pages = (EditText) findViewById(R.id.text4);
        EditText name = (EditText) findViewById(R.id.text5);
        EditText category_id = (EditText) findViewById(R.id.text6);
        Button button = (Button) findViewById(R.id.insert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将文本框中的数据添加到数据库
                values.put("author",author.getText().toString());
                values.put("price",Float.parseFloat(price.getText().toString().trim()));
                values.put("pages",Integer.parseInt(pages.getText().toString().trim()));
                values.put("name",name.getText().toString());
                values.put("category_id",Integer.parseInt(category_id.getText().toString().trim()));
                if(db.insert("Book",null,values) > 0){
                    Toast.makeText(AddBookActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    //清空文本框
                    author.setText("");
                    price.setText("");
                    pages.setText("");
                    name.setText("");
                    category_id.setText("");
                    //清空容器
                    values.clear();
                }
            }
        });
    }
}