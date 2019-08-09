package com.ankitapabbi.c0751145_mad3125_midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ankitapabbi.c0751145_mad3125_midterm.Model.User;
import com.ankitapabbi.c0751145_mad3125_midterm.Utilities.MyDataBase;

import java.util.ArrayList;

public class LoginScreen extends AppCompatActivity {

    EditText userEmail,userPassword;
    Button login;
    MyDataBase mdb;
    ArrayList<User> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        userEmail= (EditText)findViewById(R.id.userEmail);
        userPassword = (EditText)findViewById(R.id.userPassword);
        login = (Button)findViewById(R.id.loginBtn);
        mdb = new MyDataBase(getApplicationContext());
        mdb.open();
        mdb.save("ankita@gmail.com","ankita123");
        mdb.save("vishal@gmail.com","vishal123");
        mdb.save("diksha@gmail.com","diksha123");
        mdb.close();

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

               Toast.makeText(LoginScreen.this,""+count,Toast.LENGTH_LONG).show();


                mdb.close();


            }
        });


    }
}
