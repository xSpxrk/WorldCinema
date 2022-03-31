package com.example.watch;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.watch.databinding.ActivityMainBinding;
import com.example.watch.network.ApiHandler;
import com.example.watch.network.auth.models.LoginBody;
import com.example.watch.network.auth.models.LoginResponse;
import com.example.watch.network.auth.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Auth extends AppCompatActivity {

    EditText ed_email, ed_password;
    LoginService service = ApiHandler.getInstance().getLogin();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    ed_email = findViewById(R.id.edit_email);
    ed_password = findViewById(R.id.edit_pass);

    findViewById(R.id.btn_sign_in).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SingIn();
        }
    });
    }

    private void SingIn(){
        AsyncTask.execute(() ->
        {
            service.getData(getLoginBody()).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "onResponse: " + response.body().getToken());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.d(TAG, t.getLocalizedMessage().toString());
                }
            });
        });
    }

    private LoginBody getLoginBody() {
        return new LoginBody(ed_email.getText().toString(), ed_password.getText().toString());
    }
}