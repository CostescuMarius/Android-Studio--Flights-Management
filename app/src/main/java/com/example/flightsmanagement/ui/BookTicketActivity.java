package com.example.flightsmanagement.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.flightsmanagement.R;
import com.example.flightsmanagement.data.AirportListResponse;
import com.example.flightsmanagement.database.ApiService;
import com.example.flightsmanagement.database.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookTicketActivity extends AppCompatActivity {
    private Spinner departureAirports;
    private Spinner arrivalAirports;
    private EditText dateText;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.book_ticket_activity);

        departureAirports = findViewById(R.id.departureAirports);
        arrivalAirports = findViewById(R.id.arrivalAirports);
        dateText = findViewById(R.id.editTextDate);

        category = "All";

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getAllAirports();

        Button allTicketsCategory = findViewById(R.id.all_tickets_category);
        allTicketsCategory.setOnClickListener(v -> {
            category = "All";
        });

        Button fullTicketCategory = findViewById(R.id.full_category);
        fullTicketCategory.setOnClickListener(v -> {
            category = "Full";
        });

        Button studentTicketCategory = findViewById(R.id.student_category);
        studentTicketCategory.setOnClickListener(v -> {
            category = "Student";
        });

        Button under1TicketCategory = findViewById(R.id.under_1_year_category);
        under1TicketCategory.setOnClickListener(v -> {
            category = "Child";
        });

        Button searchTicketsButton = findViewById(R.id.search_button);
        searchTicketsButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SearchTicketsActivity.class);
            intent.putExtra("departure", (String) departureAirports.getSelectedItem());
            intent.putExtra("arrival", (String) arrivalAirports.getSelectedItem());
            intent.putExtra("date", dateText.getText().toString());
            intent.putExtra("category", category);
            startActivity(intent);
        });

        ImageView backButton = findViewById(R.id.arrow_back);
        backButton.setOnClickListener(v -> {
            finish();
        });
    }

    private void getAllAirports() {
        ApiService apiService = RetrofitClient.getClient("http://10.0.2.2:8080").create(ApiService.class);

        Call<AirportListResponse> airportNamesCall = apiService.getAllAirportNames();
        airportNamesCall.enqueue(new Callback<AirportListResponse>() {

            @Override
            public void onResponse(@NonNull Call<AirportListResponse> call, @NonNull Response<AirportListResponse> response) {
                if (response.isSuccessful()) {
                    AirportListResponse airports = response.body();

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, airports.getAirports());
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    departureAirports.setAdapter(adapter);
                    arrivalAirports.setAdapter(adapter);

                    Toast.makeText(getApplicationContext(), "Airports have been found", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to get airport names", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AirportListResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Failed to connect to server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
