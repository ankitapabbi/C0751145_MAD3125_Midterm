package com.ankitapabbi.c0751145_mad3125_midterm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ankitapabbi.c0751145_mad3125_midterm.Model.User;
import com.ankitapabbi.c0751145_mad3125_midterm.Utilities.MyDataBase;

import java.util.ArrayList;

public class LoginScreen extends AppCompatActivity {

    EditText userEmail,userPassword;
    Button login;
    MyDataBase mdb;
    CheckBox rememberMe;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        userEmail= (EditText)findViewById(R.id.userEmail);
        userPassword = (EditText)findViewById(R.id.userPassword);
        login = (Button)findViewById(R.id.loginBtn);
        rememberMe = (CheckBox)findViewById(R.id.rememberMe);
        mdb = new MyDataBase(getApplicationContext());
        mdb.open();
        mdb.save("ankita@gmail.com","ankita123");
        mdb.save("vishal@gmail.com","vishal123");
        mdb.save("diksha@gmail.com","diksha123");
        mdb.close();

        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        if(sharedPreferences.contains("userEmail")){
            userEmail.setText(sharedPreferences.getString("userEmail",""));
            userPassword.setText(sharedPreferences.getString("userPassword",""));
        }

      //  Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_LONG).show();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();

                mdb.open();
//                // listData.addAll(mdb.getCartData());
//                for (int ii = 0; ii<mdb.getUserData().size();ii++){
//                    String id = mdb.getUserData().get(ii).getUserId();
//                    String uemail =mdb.getUserData().get(ii).getUserEmail();
//                    String upassword =mdb.getUserData().get(ii).getUserPassword();
//                   // arrayList.add(new User(id,uemail,upassword));
//                }
//
               // Toast.makeText(LoginScreen.this,email,Toast.LENGTH_LONG).show();
            int count =  mdb.checkUserExist(email,password);

            if(count != 0 ){

                if(rememberMe.isChecked()){
                editor.putString("userEmail",email);
                editor.putString("userPassword",password);
                }else {
                    editor.remove("userEmail");
                    editor.remove("userPassword");
                }
                editor.apply();
                Intent intent = new Intent(LoginScreen.this,HomeScreen.class);
                intent.putExtra("email",email);
                startActivity(intent);
                finish();
            }
            else if (count == 0){
                Toast.makeText(LoginScreen.this,"Invalid Email Or Password",Toast.LENGTH_LONG).show();
            }

               Toast.makeText(LoginScreen.this,""+count,Toast.LENGTH_LONG).show();


                mdb.close();


            }
        });


    }
}
