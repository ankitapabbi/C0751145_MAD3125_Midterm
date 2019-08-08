package com.ankitapabbi.c0751145_mad3125_midterm.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase {

    public static final String DB_Name="MidTerm.db";
    public static final int DB_Ver=2;
    public static final String DB_Table="User_Data";
    public static final String ID = "User_id";
    public static final String Email="User_email";
    public static final String Password="User_password";


    //Query to create table

    public static final String Q_Create=
            "CREATE TABLE "+DB_Table+"("+ID+" INTEGER PRIMARY KEY  AUTOINCREMENT,"+Email+" TEXT, "+Password+" TEXT)";

    Context c;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    //all database operation coded here
    public MyDatabase(Context context){
        c = context;

    }

    public MyDatabase open() {

        dbHelper=new DBHelper(c);

        database=dbHelper.getWritableDatabase();

        return this;
    }

    public void save(String id, String email,String password) {

        ContentValues cv= new ContentValues();
        cv.put(ID,id);
        cv.put(Email,email);
        cv.put(Password,password);


        database.insert(DB_Table,null,cv);
    }

    public void close() {
        database.close();
    }

    public List<CartData> getCartData()
    {
        List<CartData> data= new ArrayList<>();
        String[] columns={KEY_ID,Name,Image,Price,Qty,Size,Color,Material,Pro_Var_ID,Pro_ID};
        Cursor cursor=database.query(DB_Table,columns,null,null,null,null,null);

        int iName=cursor.getColumnIndex(Name);
        int iImage=cursor.getColumnIndex(Image);
        int iPrice=cursor.getColumnIndex(Price);
        int iQty=cursor.getColumnIndex(Qty);
        int iSize=cursor.getColumnIndex(Size);
        int iColor=cursor.getColumnIndex(Color);
        int iMaterial=cursor.getColumnIndex(Material);
        int iId=cursor.getColumnIndex(KEY_ID);
        int iProdVarID=cursor.getColumnIndex(Pro_Var_ID);
        int iProID=cursor.getColumnIndex(Pro_ID);

        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            CartData cart = new CartData();
            cart.setName(cursor.getString(iName));
            cart.setImage(cursor.getString(iImage));
            cart.setPrice(cursor.getString(iPrice));
            cart.setProQty(cursor.getInt(iQty));
            cart.setSize(cursor.getString(iSize));
            cart.setColor(cursor.getString(iColor));
            cart.setMaterial(cursor.getString(iMaterial));
            cart.setProdId(cursor.getString(iId));
            cart.setProVarId(cursor.getInt(iProdVarID));
            cart.setProId(cursor.getInt(iProID));

            data.add(cart);
        }
        return data;
    }
    public void delete(String id) {
        database.delete(DB_Table, KEY_ID+"="+id, null);
    }

    public void deleteWithoutId() {
        database.delete(DB_Table, null, null);
    }

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + DB_Table;

        Cursor cursor = database.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_Name, null, DB_Ver);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL(Q_Create);////////////////////

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}