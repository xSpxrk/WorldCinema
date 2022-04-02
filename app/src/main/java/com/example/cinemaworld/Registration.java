package com.example.cinemaworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cinemaworld.network.ApiHandler;
import com.example.cinemaworld.network.registration.models.RegistrationBody;
import com.example.cinemaworld.network.registration.models.RegistrationResponse;
import com.example.cinemaworld.network.registration.service.RegistrationService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {
    private static final String TAG = "Registration";
    EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextRepeatedPassword;

    RegistrationService service = ApiHandler.getInstance().getRegistration();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initializeViews();

    }
    // Инизиацлизация объектов экрана
    private void initializeViews() {
        editTextEmail = findViewById(R.id.edittext_email);
        editTextPassword = findViewById(R.id.edittext_password);
        editTextFirstName = findViewById(R.id.edittext_firstname);
        editTextLastName = findViewById(R.id.edittext_lastname);
        editTextRepeatedPassword = findViewById(R.id.edittext_repeated_password);

        findViewById(R.id.btn_SignUp).setOnClickListener(view -> {
            // проверка на правильность пароля
            if (editTextPassword.getText().toString() == editTextRepeatedPassword.getText().toString()) {
                SignUp();
            } else {
                Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_LONG).show();
            }

        });
    }
    // Переход на предыдущее окно
    public void goBack(View view){
        finish();
    }

    // Регистрация пользователя
    private void SignUp() {
        AsyncTask.execute(() -> {
            service.getData(getRegistrationBody()).enqueue(new Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Регистрация успешна!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainWindow.class));
                        finish();
                    } else if (response.code() == 400) {
                        Toast.makeText(getApplicationContext(), "Неправильный запрос", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getApplicationContext(), "Неизвестная ошибка", Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                }
            });
        });
    }
    // Преобразование текстовых полей в класс RegistrationBody.java
    private RegistrationBody getRegistrationBody() {
        return new RegistrationBody(editTextEmail.getText().toString(),
                        editTextPassword.getText().toString(),
                        editTextFirstName.getText().toString(),
                        editTextLastName.getText().toString());
    }
}