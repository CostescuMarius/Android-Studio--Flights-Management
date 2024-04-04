package com.example.flightsmanagement.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightsmanagement.R;
import com.example.flightsmanagement.data.Ticket;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {
    private final List<Ticket> ticketList;

    public TicketAdapter(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_item, parent, false);
        return new TicketViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket ticket = ticketList.get(position);
        holder.departureAirportTextView.setText("Departure: " + ticket.getDepartureAirport());
        holder.departureTimeTextView.setText("Departure time: " + ticket.getDepartureTime());
        holder.arrivalAirportTextView.setText("Arrival: " + ticket.getArrivalAirport());
        holder.arrivalTimeTextView.setText("Arrival time: " + ticket.getArrivalTime());
        holder.dateTextView.setText("Date:" + ticket.getDate().toString());
        holder.priceTextView.setText("Price: " + ticket.getPrice());
        holder.typeTextView.setText("Type: " + ticket.getType());
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView departureAirportTextView;
        public TextView departureTimeTextView;
        public TextView arrivalAirportTextView;
        public TextView arrivalTimeTextView;
        public TextView dateTextView;
        public TextView priceTextView;
        public TextView typeTextView;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            departureAirportTextView = itemView.findViewById(R.id.departureAirportTextView);
            departureTimeTextView = itemView.findViewById(R.id.departureTimeTextView);
            arrivalAirportTextView = itemView.findViewById(R.id.arrivalAirportTextView);
            arrivalTimeTextView = itemView.findViewById(R.id.arrivalTimeTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Ticket ticket = ticketList.get(position);

                Intent intent = new Intent(itemView.getContext(), BuyTicketActivity.class);
                intent.putExtra("ticket", ticket);
                itemView.getContext().startActivity(intent);
            }
        }
    }
}

