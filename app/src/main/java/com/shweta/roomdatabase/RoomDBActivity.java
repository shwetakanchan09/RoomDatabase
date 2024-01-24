package com.shweta.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;

public class RoomDBActivity extends AppCompatActivity implements AdapterListener{

    EditText userName, email;
    Button insert;
    RecyclerView rv;
    UserDatabase userDatabase;
    UserDao userDao;
    PojoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_db);
        userName = findViewById(R.id.userName);
        email = findViewById(R.id.emailId);
        insert = findViewById(R.id.btnInsert);
        rv = findViewById(R.id.rvDB);

        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.getDao();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = userName.getText().toString();
                String emailID = email.getText().toString();

                if (!name.equals("") && !emailID.equals("")) {
                    PojoModel pojoModel = new PojoModel(Math.random(), name, emailID);
                    adapter.addUser(pojoModel);
                    userDao.Insert(pojoModel);
                    userName.setText("");
                    email.setText("");
                    Toast.makeText(RoomDBActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RoomDBActivity.this, "Please fill the data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        adapter = new PojoAdapter(this, this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fetchData() {
        adapter.clearData();
        List<PojoModel> userList = userDao.getAllUsers();
        for (int i = 0; i < userList.size(); i++) {
            PojoModel pojo = userList.get(i);
            adapter.addUser(pojo);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    @Override
    public void onUpdate(PojoModel pojoModel) {
        Intent i = new Intent(this, UpdateActivity.class);
        i.putExtra("model", pojoModel);
        startActivity(i);
    }

    @Override
    public void onDelete(double id, int pos) {
        userDao.delete(id);
        adapter.removeUser(pos);

    }
}