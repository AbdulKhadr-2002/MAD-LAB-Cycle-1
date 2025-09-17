package com.example.arrayadapter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    @Override

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView=findViewById(R.id.listView);
        String[] fruits={"Apple", "Banana", "Cherry", "Date", "Grape", "Kiwi", "Lemon", "Mango",
                "Orange", "Peach"};

        ArrayAdapter<String>adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,fruits);
        listView.setAdapter(adapter);
    }
}
