package com.shweta.roomdatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    EditText name,email;
    Button update;
    UserDatabase userDatabase;
    UserDao userDao;
    PojoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name = findViewById(R.id.etName);
        email = findViewById(R.id.etEmail);
        update = findViewById(R.id.btnUpdate);

        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.getDao();

        PojoModel pojo =(PojoModel) getIntent().getSerializableExtra("model");

        name.setText(pojo.getName());
        email.setText(pojo.getEmail());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PojoModel user = new PojoModel(pojo.getId(),name.getText().toString(),email.getText().toString());
                userDao.Update(user);
                finish();
            }
        });
    }
}