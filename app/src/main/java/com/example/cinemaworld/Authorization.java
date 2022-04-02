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
import com.example.cinemaworld.network.profile.models.ProfileResponse;
import com.example.cinemaworld.network.profile.service.GetProfileService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Authorization extends AppCompatActivity {
    EditText editPassword, editEmail;

    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private String token;

    LoginService service = ApiHandler.getInstance().getLogin();
    GetProfileService getProfileService = ApiHandler.getInstance().getProfileService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        // Создание переменных для хранения токена
        editor = getSharedPreferences("token", MODE_PRIVATE).edit();
        preferences = getSharedPreferences("token", MODE_PRIVATE);
        // Получение токена
        token = preferences.getString("token", "");
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        findViewById(R.id.btnLogin).setOnClickListener(view -> {
            SignIn();
        });
        // Если токен не пустой, запускаем главное окно
        if (!token.equals("")) {
            startHomePage();
            finish();
        }

    }

    public void SignUp(View view) {
        startActivity(new Intent(this, Registration.class));
    }

    // Получение токена, если токен пришел, значит авторизация прошла успешно
    public void SignIn() {
        // Создание ассинхронного потока
        AsyncTask.execute(() -> {
            service.getData(getLoginData()).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        // Сохранение токена в sharedPreferences в переменную 'token'
                        editor.putString("token", response.body().getToken()).apply();
                        // Получение и сохранение имени профиля
                        getProfile(response.body().getToken());
                        //Запуск
                        startHomePage();
                        finish();
                    } else if (response.code() == 401) {
                        Toast.makeText(getApplicationContext(), "Такого профиля не существует" + response.code(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Неизвестная ошибка", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), call.toString(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    // Получение профиля
    private void getProfile(String _token) {
        AsyncTask.execute(() -> {
            getProfileService.getData(_token).enqueue(new Callback<List<ProfileResponse>>() {
                @Override
                public void onResponse(Call<List<ProfileResponse>> call, Response<List<ProfileResponse>> response) {
                    // Получение имени профиля и сохранения
                    String profileName = response.body().get(0).getProfileName();
                    editor.putString("ProfileName", profileName).apply();
                }

                @Override
                public void onFailure(Call<List<ProfileResponse>> call, Throwable t) {

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