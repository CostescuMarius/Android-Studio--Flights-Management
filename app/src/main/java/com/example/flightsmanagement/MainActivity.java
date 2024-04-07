package com.example.flightsmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.flightsmanagement.database.ApiService;
import com.example.flightsmanagement.database.RetrofitClient;
import com.example.flightsmanagement.ui.BookTicketActivity;
import com.example.flightsmanagement.ui.ScanQRActivity;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CardView bookTicket = findViewById(R.id.cardViewBookTicket);
        bookTicket.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), BookTicketActivity.class);
            startActivity(intent);
        });

        CardView scanQR = findViewById(R.id.cardViewScanQRTicket);
        scanQR.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ScanQRActivity.class);
            startActivity(intent);
        });
    }
}