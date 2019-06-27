package com.example.ckbur.solutionsqlite_final;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText,ageEditText,genderEditText;
    private Button addButton,displayAllDataButtonId;

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabaseHelper=new MyDatabaseHelper(this);

        SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getWritableDatabase();

        nameEditText=(EditText)findViewById(R.id.NameEditTextId);
        ageEditText=(EditText)findViewById(R.id.AgeEditTextId);
        genderEditText=(EditText)findViewById(R.id.GenderEditTextId);
        addButton=(Button)findViewById(R.id.addButtonId);
        displayAllDataButtonId=(Button)findViewById(R.id.displayAllDataButtonId);

        addButton.setOnClickListener(this);
        displayAllDataButtonId.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String name=nameEditText.getText().toString();
        String age=ageEditText.getText().toString();
        String gender=genderEditText.getText().toString();

        if (v.getId()==R.id.addButtonId){

          long rowId=  myDatabaseHelper.insertData(name,age,gender);

          if (rowId==-1){
              Toast.makeText(this, "Row "+rowId+" not successfully added", Toast.LENGTH_SHORT).show();

          }else {

              Toast.makeText(this, "Row "+rowId+" successfully added", Toast.LENGTH_SHORT).show();


          }

        }if (v.getId()==R.id.displayAllDataButtonId){

            Cursor cursor=myDatabaseHelper.displayalldata();

            if (cursor.getCount()==0){

                Toast.makeText(this, "there is no data", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer stringBuffer=new StringBuffer();
            while (cursor.moveToNext()){

                stringBuffer.append("ID : "+cursor.getString(0)+"\n");
                stringBuffer.append("Name : "+cursor.getString(1)+"\n");
                stringBuffer.append("Age : "+cursor.getString(2)+"\n");
                stringBuffer.append("Gender : "+cursor.getString(3)+"\n");
            }

            showdata("ReseltSet",stringBuffer.toString());

        }

    }


    public void showdata(String title,String data){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setTitle(title);
        builder.setMessage(data);
        builder.setCancelable(true);
        builder.show();
    }
}
