package com.tuan.serviceexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editTextInput;
    Button btn_start1;
    Button btn_start2;
    Button btn_stop1;
    Button btn_stop2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextInput = findViewById(R.id.editTextTextPersonName);

         btn_start1 = findViewById(R.id.button_start_1);
        btn_start2 = findViewById(R.id.button_start_2);
        btn_stop1 = findViewById(R.id.button_stop_1);
         btn_stop2 = findViewById(R.id.button_stop_2);

        btn_start1.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        startService1();
                    }
                });
        btn_start2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                startService2();
            }
        });

        btn_stop1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                stopService1();
            }
        });

        btn_stop2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                stopService2();
            }
        });
    }


    public void startService1() {
        String input = editTextInput.getText().toString();
        Intent serviceIntent = new Intent(this, ExampleService.class);
        serviceIntent.putExtra("inputExtra", input);
        serviceIntent.putExtra("id", 1);
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void startService2() {
        String input = editTextInput.getText().toString();
        Intent serviceIntent = new Intent(this, ExampleService.class);
        serviceIntent.putExtra("inputExtra", input);
        serviceIntent.putExtra("id", 2);
        ContextCompat.startForegroundService(this, serviceIntent);


    }

    public void stopService1() {
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.cancel(1);


        //Intent serviceIntent = new Intent(this, ExampleService.class);
        //stopService(serviceIntent);
    }
    public void stopService2() {
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.cancel(2);
        //Intent serviceIntent = new Intent(this, ExampleService.class);
        //stopService(serviceIntent);
    }
}