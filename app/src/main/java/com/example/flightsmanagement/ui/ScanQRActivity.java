package com.example.flightsmanagement.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.flightsmanagement.MainActivity;
import com.example.flightsmanagement.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.scan_qr_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView backButton = findViewById(R.id.arrow_back);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        Button scanButton = findViewById(R.id.scan_button);
        scanButton.setOnClickListener(v -> {
            scanQR();
        });
    }

    private void scanQR() {
        new IntentIntegrator(ScanQRActivity.this)
                .setOrientationLocked(true)
                .setPrompt("Scan a QR code")
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                .initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            String scannedData = result.getContents();
            if (scannedData != null) {
                //Toast.makeText(this, "Scanned: " + scannedData, Toast.LENGTH_SHORT).show();
                TextView resultTextView = findViewById(R.id.resultView);
                resultTextView.setText(scannedData);
                resultTextView.setTextSize(20);
                resultTextView.setTypeface(null, Typeface.BOLD);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
