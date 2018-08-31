package com.example.no0ne.sendobjects.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.no0ne.sendobjects.R;
import com.example.no0ne.sendobjects.api.model.User;
import com.example.no0ne.sendobjects.api.service.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nameEditText = (EditText) findViewById(R.id.edit_text_name);
        final EditText emailEditText = (EditText) findViewById(R.id.edit_text_email);
        final EditText ageEditText = (EditText) findViewById(R.id.edit_text_age);
        final EditText topicsEditText = (EditText) findViewById(R.id.edit_text_topics);

        Button createAccountButton = (Button) findViewById(R.id.button_create_account);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(
                        nameEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        Integer.parseInt(ageEditText.getText().toString()),
                        topicsEditText.getText().toString().split(",")
                );

                sendNetworkRequest(user);
            }
        });
    }

    private void sendNetworkRequest(User user) {
        // Creating retrofit instance
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://10.0.2.2:3000/api/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        // Get client & call object for the request
        UserClient client = retrofit.create(UserClient.class);
        Call<User> call = client.createAccount(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this, "Worked! \n User ID: " + response.body().getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", t.toString());
            }
        });
    }
}
