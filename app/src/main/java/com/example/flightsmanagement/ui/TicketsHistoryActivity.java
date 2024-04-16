package com.example.flightsmanagement.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightsmanagement.R;
import com.example.flightsmanagement.data.CheckTicketDto;
import com.example.flightsmanagement.data.Ticket;
import com.example.flightsmanagement.data.service.ApiService;
import com.example.flightsmanagement.data.service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketsHistoryActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_tickets);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.search_tickets), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getTickets();

        ImageView backButton = findViewById(R.id.arrow_back);
        backButton.setOnClickListener(v -> {
            finish();
        });
    }

    private void getTickets() {
        ApiService apiService = RetrofitClient.getClient("http://10.0.2.2:8080").create(ApiService.class);

        Call<List<Ticket>> ticketsCall = apiService.getAllBoughtTickets();
        ticketsCall.enqueue(new Callback<List<Ticket>>() {
            @Override
            public void onResponse(@NonNull Call<List<Ticket>> call, @NonNull Response<List<Ticket>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && !response.body().isEmpty()) {
                        List<Ticket> ticketsFound = response.body();

                        RecyclerView recyclerView = findViewById(R.id.tickets_recycler_view);

                        HistoryTicketAdapter adapter = new HistoryTicketAdapter(ticketsFound);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        Toast.makeText(getApplicationContext(), "Tickets have been found", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No tickets available", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to get tickets", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Ticket>> call, @NonNull Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Failed to connect to server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void extract_tickets(List<Ticket> ticketsFound, List<Ticket> ticketsToShow, String category) {
        for(Ticket ticket : ticketsFound) {
            if(ticket.getType().equals(category)) {
                ticketsToShow.add(ticket);
            }
        }
    }
}
