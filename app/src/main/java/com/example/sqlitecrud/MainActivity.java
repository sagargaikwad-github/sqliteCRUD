package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id,name,contact,bdate;
    Button insert,read,update,delete;
    String Name;
    int Id,Contact,Birthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqliteData DB=new sqliteData(MainActivity.this);

        id=findViewById(R.id.id);
        name=findViewById(R.id.name);
        contact=findViewById(R.id.contact);
        bdate=findViewById(R.id.bdate);
        insert=findViewById(R.id.insert);
        read=findViewById(R.id.read);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Id=Integer.parseInt(id.getText().toString());
                 Birthdate=Integer.parseInt(bdate.getText().toString());
                 Contact=Integer.parseInt(contact.getText().toString());
                 Name=name.getText().toString();

                Boolean check=DB.insert(Id,Name,Contact,Birthdate);
                if(check==true)
                {
                    Toast.makeText(MainActivity.this, "Data Inserted Sucessfully", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    id.setText("");
                    contact.setText("");
                    bdate.setText("");
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Id=Integer.parseInt(id.getText().toString());
                Name=name.getText().toString();
                Birthdate=Integer.parseInt(bdate.getText().toString());
                Contact=Integer.parseInt(contact.getText().toString());

                Boolean check=DB.update(Id,Name,Contact,Birthdate);
                if(check==true)
                {
                    Toast.makeText(MainActivity.this, "Data Update Sucessfully", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    id.setText("");
                    contact.setText("");
                    bdate.setText("");
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Failed to Update", Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name=name.getText().toString();
                Boolean check=DB.delete(Name);
                if(check==true)
                {
                    Toast.makeText(MainActivity.this, "Data Deleted Sucessfully", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    id.setText("");
                    contact.setText("");
                    bdate.setText("");
                }
                else
                {
                    Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                }

            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Id=Integer.parseInt(id.getText().toString());

                Cursor res=DB.viewdata(Id);
                if(res.moveToFirst())
                {
                    do{
                        Name=res.getString(1); //column position
                        Contact=res.getInt(2);
                        Birthdate=res.getInt(3);

                        name.setText(Name);
                        contact.setText(String.valueOf(Contact));
                        bdate.setText(String.valueOf(Birthdate));

                    }while(res.moveToNext());
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                    id.setText(String.valueOf(Id));
                    name.setText("");
                    contact.setText("");
                    bdate.setText("");
                }


            }
        });

    }

}