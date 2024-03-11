package com.example.flightsmanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BuyTicket extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.buy_ticket_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.buy_ticket), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent currentIntent = getIntent();
        if(currentIntent != null && currentIntent.hasExtra("ticket")) {
            Ticket ticket = (Ticket) currentIntent.getSerializableExtra("ticket");

            TextView ticketInfoTextView = findViewById(R.id.ticketInfoTextView);
            assert ticket != null;
            ticketInfoTextView.setText("From: " + ticket.getDepartureAirport() + "         " +
                    "To: " + ticket.getArrivalAirport() + "\n" +
                    "Interval: " + ticket.getDepartureTime() + " <-> " + ticket.getArrivalTime() + "\n" +
                    "Date: " + ticket.getDate() + "\n" +
                    "Price: " + ticket.getPrice());
        }

        ImageView backButton = findViewById(R.id.arrow_back);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SearchTickets.class);
            startActivity(intent);
        });
    }
}
