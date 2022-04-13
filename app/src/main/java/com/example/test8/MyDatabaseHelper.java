package com.example.test8;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String CREATE_BOOK =   "create table Book ("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer,"
            + "name text,"
            + "category_id integer)";

    private static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement,"
            + "category_name text,"
            + "category_code integer)";

    private static final String Drop_Book = "drop table if exists Book";

    private static final String Drop_Category = "drop table if exists Category";

    private Context myContext;

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建表
        sqLiteDatabase.execSQL(CREATE_BOOK);
        sqLiteDatabase.execSQL(CREATE_CATEGORY);

        Toast.makeText(myContext,"创建表成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //更新版本后将原数据库删除，以便于创建新的数据库
        sqLiteDatabase.execSQL(Drop_Book);
        sqLiteDatabase.execSQL(Drop_Category);
        onCreate(sqLiteDatabase);
    }
}
