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

    private void initializeViews() {
        editTextEmail = findViewById(R.id.edittext_email);
        editTextPassword = findViewById(R.id.edittext_password);
        editTextFirstName = findViewById(R.id.edittext_firstname);
        editTextLastName = findViewById(R.id.edittext_lastname);
        editTextRepeatedPassword = findViewById(R.id.edittext_repeated_password);

        findViewById(R.id.btn_SignUp).setOnClickListener(view -> {
            SignUp();
        });
    }

    public void goBack(View view){
        finish();
    }

    private void SignUp() {
        AsyncTask.execute(() -> {
            service.getData(getRegistrationBody()).enqueue(new Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                    if (response.isSuccessful()) {
                        if (editTextPassword.getText().toString() == editTextRepeatedPassword.getText().toString()) {
                            Toast.makeText(getApplicationContext(), "Регистрация успешна!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainWindow.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Не крут", Toast.LENGTH_LONG).show();
                    }

                }
                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                }
            });
        });
    }

    private RegistrationBody getRegistrationBody() {
        return new RegistrationBody(editTextEmail.getText().toString(),
                        editTextPassword.getText().toString(),
                        editTextFirstName.getText().toString(),
                        editTextLastName.getText().toString());
    }
}