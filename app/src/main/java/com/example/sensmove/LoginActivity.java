package com.example.sensmove;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sensmove.dao.ApiService;
import com.example.sensmove.database.UserDatabase;
import com.example.sensmove.entities.User;
import com.example.sensmove.instance.ApiInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public Context context;
    public Button LoginButton;

    @Override
    protected void onStart() {
        super.onStart();
        LoginAsyncTask loginAsyncTasks = new LoginAsyncTask();
        loginAsyncTasks.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = getApplicationContext();

        LoginButton = findViewById(R.id.login);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginButton.setEnabled(false);
                LoginButton.setText("Chargement...");
                EditText emailEditText = (EditText) findViewById(R.id.email);
                EditText passwordEditText = (EditText) findViewById(R.id.password);

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                ApiService apiService = ApiInstance.getRetrofitInstance().create(ApiService.class);
                apiService.performLogin(email, password).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        if(response.isSuccessful() && response.body() != null) {
                            User user = response.body();
                            UserDatabase.getDb(context).userDao().add(user);
                            Context context = getApplicationContext();
                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Identifiants incorrects",Toast.LENGTH_SHORT).show();
                            LoginButton.setEnabled(true);
                            LoginButton.setText("Connexion");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(), "Identifiants incorrects",Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                        LoginButton.setEnabled(true);
                        LoginButton.setText("Connexion");
                    }
                });

            }
        });

    }

    public void onRegisterClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://jean.leopold-jacquet.com/register"));
        startActivity(browserIntent);
    }

    public class LoginAsyncTask extends AsyncTask<Nullable, Nullable, List<User>> {
        @Override
        protected List<User> doInBackground(Nullable... nullables) {
            return UserDatabase.getDb(context).userDao().list();
        }
        @Override
        protected void onPostExecute(List<User> users) {
            if(users.size() != 0){
                Context context = getApplicationContext();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        }
    }
}