package com.example.flightsmanagement.ui;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightsmanagement.MainActivity;
import com.example.flightsmanagement.R;
import com.example.flightsmanagement.data.BuyTicketDto;
import com.example.flightsmanagement.data.CheckTicketDto;
import com.example.flightsmanagement.data.Ticket;
import com.example.flightsmanagement.data.service.ApiService;
import com.example.flightsmanagement.data.service.RetrofitClient;
import com.example.flightsmanagement.util.TicketInfo;
import com.example.flightsmanagement.util.TimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyTicketActivity extends AppCompatActivity {
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

            ticketInfoTextView.setText(TicketInfo.generateTicketInfo(ticket));

            Button confirmButton = findViewById(R.id.buttonConfirmPayment);
            confirmButton.setOnClickListener(v -> {
                EditText customerNameEditText = findViewById(R.id.editTextCustomerName);
                BuyTicketDto buyTicketDto = new BuyTicketDto("maryuscostescu558@yahoo.com",
                        ticket.getTicketId(), customerNameEditText.getText().toString());
                buyTicket(buyTicketDto);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //intent.putExtra("ticket", ticket);
                startActivity(intent);

                sendNotification(ticket);
            });
        }

        ImageView backButton = findViewById(R.id.arrow_back);
        backButton.setOnClickListener(v -> {
            //Intent intent = new Intent(getApplicationContext(), SearchTicketsActivity.class);
            //startActivity(intent);
            finish();
        });
    }

    private void buyTicket(BuyTicketDto buyTicketDto) {
        ApiService apiService = RetrofitClient.getClient("http://10.0.2.2:8080").create(ApiService.class);

        Call<List<Ticket>> ticketsCall = apiService.buyTicket(buyTicketDto);
        ticketsCall.enqueue(new Callback<List<Ticket>>() {
            @Override
            public void onResponse(@NonNull Call<List<Ticket>> call, @NonNull Response<List<Ticket>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && !response.body().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Ticket has been added.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No tickets added.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to add ticket.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Ticket>> call, @NonNull Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Failed to connect to server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendNotification(Ticket ticket) {
        NotificationManager notificationManager;

        String channelId = "channel_id";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId);

        Intent currentIntent = new Intent(getApplicationContext(), TicketQRActivity.class);
        currentIntent.putExtra("ticket", ticket);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                0, currentIntent, PendingIntent.FLAG_MUTABLE);

        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notifications_active)
                .setContentTitle("Purchased ticket")
                .setContentText("You just bought a ticket.\n" +
                        "Check the flight information.")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_MAX);


        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Ticket Channel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }

        notificationManager.notify(0, builder.build());
    }
}
