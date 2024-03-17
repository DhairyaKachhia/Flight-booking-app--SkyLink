package com.example.skylink.presentation.Bookings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.skylink.R;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import java.util.List;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.BookingViewHolder> {

    private List<iFlightInfo> bookingsList;

    public BookingsAdapter(List<iFlightInfo> bookingsList) {
        this.bookingsList = bookingsList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booking_card, parent, false);
        return new BookingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        iFlightInfo booking = bookingsList.get(position);
        holder.textViewEconOrBus.setText(booking.getEconOrBus().toString());
//        holder.textViewFlightNumber.setText();
    }

    @Override
    public int getItemCount() {
        return bookingsList != null ? bookingsList.size() : 0;
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewEconOrBus;

        public TextView textViewFlightNumber;


        public BookingViewHolder(View itemView) {
            super(itemView);
            textViewEconOrBus = itemView.findViewById(R.id.textViewEconOrBus);
            textViewFlightNumber = itemView.findViewById(R.id.textViewFlightNumber);
        }
    }
}
