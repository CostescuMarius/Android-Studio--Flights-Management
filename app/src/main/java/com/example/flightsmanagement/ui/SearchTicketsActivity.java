package com.example.flightsmanagement.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightsmanagement.R;
import com.example.flightsmanagement.data.Ticket;

import java.util.ArrayList;
import java.util.Objects;

public class SearchTicketsActivity extends AppCompatActivity {
    private ArrayList<Ticket> tickets;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_tickets);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.search_tickets), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tickets = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            tickets.add(new Ticket());
        }

        RecyclerView recyclerView = findViewById(R.id.tickets_recycler_view);
        TicketAdapter adapter = new TicketAdapter(tickets);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                LinearLayoutManager.VERTICAL);
//        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.divider)));
//        recyclerView.addItemDecoration(dividerItemDecoration);

        ImageView backButton = findViewById(R.id.arrow_back);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), BookTicketActivity.class);
            startActivity(intent);
        });
    }
}
