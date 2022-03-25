package com.example.cinemaworld;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cinemaworld.network.ApiHandler;
import com.example.cinemaworld.network.auth.models.LoginBody;
import com.example.cinemaworld.network.auth.models.LoginResponse;
import com.example.cinemaworld.network.auth.service.LoginService;
import com.example.cinemaworld.network.profile.models.GetProfileResponse;
import com.example.cinemaworld.network.profile.service.GetProfileService;

import java.nio.channels.AsynchronousServerSocketChannel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Authorization extends AppCompatActivity {
    EditText editPassword, editEmail;

    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private String token;

    LoginService service = ApiHandler.getInstance().getLogin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        editor = getSharedPreferences("token", MODE_PRIVATE).edit();
        preferences = getSharedPreferences("token", MODE_PRIVATE);
        token = preferences.getString("token", "");
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        findViewById(R.id.btnLogin).setOnClickListener(view -> {
            SignIn();
        });
        if (!token.equals(""))
            startHomePage();


    }

    public void SignUp(View view) {
        startActivity(new Intent(this, Registration.class));
    }

    public void SignIn() {
        AsyncTask.execute(() -> {
            service.getData(getLoginData()).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        editor.putString("token", response.body().getToken()).apply();
                        startHomePage();
                        finish();
                    } else if (response.code() == 400) {
                        Toast.makeText(getApplicationContext(), "Не крут", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Не крут", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), call.toString(), Toast.LENGTH_LONG).show();
                }
            });
        });


    }


    private LoginBody getLoginData() {
        return new LoginBody(editEmail.getText().toString(), editPassword.getText().toString());
    }

    private void startHomePage() {
        startActivity(new Intent(getApplicationContext(), MainWindow.class));
        finish();
    }
}