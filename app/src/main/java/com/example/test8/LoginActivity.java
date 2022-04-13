package com.example.test8;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //这是一个静态方法,它接收一个Context参数,并自动使用当前应用程序的包名作为前缀来命名SharedPreferences文件.
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.editTextTextPersonName);
        passwordEdit = (EditText) findViewById(R.id.editTextTextPassword);
        rememberPass = (CheckBox) findViewById(R.id.checkBox);
        login = (Button) findViewById(R.id.button1);
        //设置复选框的初始值为false，获取它的选择结果进行判断是否已选择记住密码
        boolean isRemember = sharedPreferences.getBoolean("checkBox",false);
        //判断是否记住密码
        if(isRemember){
            //如果记住了密码，将账号和密码都设置到文本框中
            //获取当前保存的相应键的值，默认为空
            String account = sharedPreferences.getString("editTextTextPersonName","");
            String password = sharedPreferences.getString("editTextTextPassword","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            //自动登录
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = accountEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString().trim();
                //如果账号是vollf，密码是123456则登陆成功，否则显示登陆失败，请重新输入正确的账号密码
                if(account.equals("vollf") && password.equals("123456")){
                    editor = sharedPreferences.edit();
                    //如果复选框被选中，则将复选框的值改为true，账号密码保存到SharedPreferences中
                    if(rememberPass.isChecked()){
                        editor.putBoolean("checkBox",true);
                        editor.putString("editTextTextPersonName",account);
                        editor.putString("editTextTextPassword",password);
                    }else {
                        //如果没有选中则将已保存的内容删除
                        editor.clear();
                    }
                    //将editor中的内容提交
                    editor.apply();
                    //打开主页面
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    //销毁当前活动
                    finish();
                }else {
                    //Toast.LENGTH_LONG（3.5秒）和Toast.LENGTH_SHORT（2秒）
                    Toast.makeText(LoginActivity.this,"登陆失败，请重新输入正确的账号密码",
                    Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}