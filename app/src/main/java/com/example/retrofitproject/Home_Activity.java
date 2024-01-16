package com.example.retrofitproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home_Activity extends AppCompatActivity {
    EditText name, role;
    Button bt, bt2;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.name);
        role = findViewById(R.id.role);
        bt = findViewById(R.id.request);
        result = findViewById(R.id.resultApi);
        bt2 = findViewById(R.id.fbt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty() && role.getText().toString().isEmpty()){
                    toast("Please enter all field");
                    return;
                }

                postData(name.getText().toString(), role.getText().toString());
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void postData(String rName, String rRole) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReteroRequestApi reteroRequestApi = retrofit.create(ReteroRequestApi.class);

        UserDataModel userDataModel = new UserDataModel(rName, rRole);

        Call<UserDataModel> call = reteroRequestApi.createPost(userDataModel);

        call.enqueue(new Callback<UserDataModel>() {
            @Override
            public void onResponse(@NonNull Call<UserDataModel> call, @NonNull Response<UserDataModel> response) {
                UserDataModel responseData = response.body();

                assert responseData != null;
                String responseResult = "Response code: "+ response.code() + "\nName: " + responseData.getName() + "\nRole: " + responseData.getJob();
                result.setText(responseResult);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(@NonNull Call<UserDataModel> call, @NonNull Throwable t) {
                result.setText("Error: " + t.getMessage() );
            }
        });
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}