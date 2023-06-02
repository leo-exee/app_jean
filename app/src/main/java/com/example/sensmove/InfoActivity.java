package com.example.sensmove;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sensmove.database.UserDatabase;
import com.example.sensmove.entities.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InfoActivity extends AppCompatActivity {

    public Context context;

    public TextView Email;
    public Button Detail;
    public Button Disconnect;
    public FloatingActionButton CloseButton;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        context = getApplicationContext();

        User user = UserDatabase.getDb(context).userDao().list().isEmpty() ? null : UserDatabase.getDb(context).userDao().list().get(0);

        if(user == null) {
            Context context = getApplicationContext();
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        } else {
            Detail = findViewById(R.id.detail);
            Disconnect = findViewById(R.id.disconnect);
            Email = findViewById(R.id.email);

            Email.setText(user.getEmail());

            CloseButton = findViewById(R.id.close);
            CloseButton.setImageDrawable(getResources().getDrawable(R.drawable.close));
            CloseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                }
            });

            Detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://jean.leopold-jacquet.com/"));
                    startActivity(browserIntent);
                }
            });

            Disconnect.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserDatabase.getDb(context).clearAllTables();
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                }
            }));
        }

    }
}
