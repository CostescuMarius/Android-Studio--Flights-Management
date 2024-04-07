package com.example.flightsmanagement.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.flightsmanagement.MainActivity;
import com.example.flightsmanagement.R;
import com.example.flightsmanagement.data.Ticket;
import com.example.flightsmanagement.util.TicketInfo;
import com.example.flightsmanagement.util.TimeFormat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Date;

public class TicketQRActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ticket_qr_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.buy_ticket), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent currentIntent = getIntent();
        if(currentIntent != null && currentIntent.hasExtra("ticket")) {
            Ticket ticket = (Ticket) currentIntent.getSerializableExtra("ticket");

            assert ticket != null;
            generateQR(ticket);
        }

        ImageView backButton = findViewById(R.id.arrow_back);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }

    private void generateQR(Ticket ticket) {
        String textInQR = TicketInfo.generateTicketInfo(ticket);

        int width = 500;
        int height = 500;

        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(textInQR, BarcodeFormat.QR_CODE, width, height);

            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            ImageView ticketInfoTextView = findViewById(R.id.history_ticket_qr);
            ticketInfoTextView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
