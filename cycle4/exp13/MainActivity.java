package com.example.exp13;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editTextId, editTextName, editTextEmail;
    Button btnAdd, btnView, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        addData();
        viewAll();
        updateData();
        deleteData();
    }

    public void addData() {
        btnAdd.setOnClickListener(v -> {
            boolean isInserted = myDb.insertData(
                    editTextName.getText().toString(),
                    editTextEmail.getText().toString()
            );
            if (isInserted)
                Toast.makeText(MainActivity.this, "Data Inserted Successfully!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Data Insertion Failed", Toast.LENGTH_SHORT).show();
        });
    }

    public void viewAll() {
        btnView.setOnClickListener(v -> {
            Cursor res = myDb.getAllData();
            if (res.getCount() == 0) {
                showMessage("Error", "Nothing found");
                return;
            }

            StringBuilder buffer = new StringBuilder();
            while (res.moveToNext()) {
                buffer.append("ID: ").append(res.getString(0)).append("\n");
                buffer.append("Name: ").append(res.getString(1)).append("\n");
                buffer.append("Email: ").append(res.getString(2)).append("\n\n");
            }

            showMessage("Data", buffer.toString());
        });
    }

    public void updateData() {
        btnUpdate.setOnClickListener(v -> {
            boolean isUpdated = myDb.updateData(
                    editTextId.getText().toString(),
                    editTextName.getText().toString(),
                    editTextEmail.getText().toString()
            );
            if (isUpdated)
                Toast.makeText(MainActivity.this, "Data Updated Successfully!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Data Update Failed", Toast.LENGTH_SHORT).show();
        });
    }

    public void deleteData() {
        btnDelete.setOnClickListener(v -> {
            Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
            if (deletedRows > 0)
                Toast.makeText(MainActivity.this, "Data Deleted Successfully!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Data Deletion Failed", Toast.LENGTH_SHORT).show();
        });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
