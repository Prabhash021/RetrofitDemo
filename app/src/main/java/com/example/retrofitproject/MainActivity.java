package com.example.retrofitproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {
    TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult =findViewById(R.id.result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONapi jsonApi = retrofit.create(JSONapi.class);

        Call<List<PostModel>> call = jsonApi.getPosts();

        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Result: "+ response.code());
                    return;
                }

                List<PostModel> posts = response.body();
                for(PostModel post: posts){
                    String content = "";
                    content += "ID: "+ post.getId() + "\n";
                    content += "UserId: "+post.getUserId()+"\n";
                    content += "Title: "+post.getTitle()+"\n";
                    content += "Text: "+post.getBody();

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {

            }
        });
    }
}