package com.oonusave.coupon.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
 
public class DatabaseHelper extends SQLiteOpenHelper {
 
  private static final String DBNAME = "mocansa_db";
  private DatabaseHelper myDBHelper;
  private SQLiteDatabase myDB;
 
  private final Context myContext;
 
  public DatabaseHelper(Context context) {
    super(context, DBNAME, null, 2);
    this.myContext = context;
  }
 
  @Override
  public void onCreate(SQLiteDatabase db) {
  }
 
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  }
 
  public DatabaseHelper open() throws SQLException {
     myDBHelper = new DatabaseHelper(myContext);
     myDB = myDBHelper.getWritableDatabase();
     return this;
  }
}