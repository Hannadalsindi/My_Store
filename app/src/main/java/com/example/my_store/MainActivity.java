package com.example.my_store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    EditText Username = null;
    EditText Password = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView PostTitleTV = findViewById(R.id.post_title_tv);

        Username = findViewById(R.id.UserName);
        Password = findViewById(R.id.Password);
        Button Create = findViewById(R.id.Create);



    }



    public void create_user(View view) {



       String username = Username.getText().toString();
       String password= Password.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);


         Call<Post> call = apiInterface.getPost(username,password);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
//                Username.setText(response.body().getUsername());
                Log.i("onResponse-POST REQUEST",response.message());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
//                PostTitleTV.setText(t.getMessage());
                Log.i("onFailure-POST REQUEST",t.getMessage());



            }
        });


    }
}

