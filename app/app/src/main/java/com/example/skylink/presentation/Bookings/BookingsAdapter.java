package com.example.skylink.presentation.Bookings;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

        holder.textViewBookingNumber.setText(booking.getBookingNum().toString());
        holder.textViewEconOrBus.setText(booking.getEconOrBus().toString());
        holder.textViewOutboundOrInbound.setText(booking.getBound().toString());

        if (booking.isCheckedIn()) {
            holder.buttonCheckIn.setText("Checked In");
        } else {
            holder.buttonCheckIn.setText("Online Check-In");
        }

        holder.buttonModifyBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                    builder.setTitle("Modify Booking");
                    builder.setMessage("Do you want to cancel the booking or modify the flight?");
                    builder.setPositiveButton("Cancel Booking", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder refundDialog = new AlertDialog.Builder(holder.itemView.getContext());
                            refundDialog.setTitle("Refund Successful");
                            refundDialog.setMessage("Your booking has been cancelled. Your refund will be processed within seven working days.");
                            refundDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    bookingsList.remove(adapterPosition);
                                    notifyItemRemoved(adapterPosition);
                                }
                            });
                            refundDialog.show();
                        }
                    });
                    builder.setNegativeButton("Modify Flight", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder refundDialog = new AlertDialog.Builder(holder.itemView.getContext());
                            refundDialog.setTitle("Refund Notice");
                            refundDialog.setMessage("Your refund will be processed within seven working days. Please proceed with your new booking.");
                            refundDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    bookingsList.remove(adapterPosition);
                                    notifyItemRemoved(adapterPosition);
                                }
                            });
                            refundDialog.show();
                        }
                    });
                    builder.setNeutralButton("Cancel", null);
                    builder.show();
                }
            }
        });

        holder.buttonCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    booking.setCheckedIn(true);

                    holder.buttonCheckIn.setText("Checked In");
                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                    builder.setMessage("Check-in successful")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookingsList != null ? bookingsList.size() : 0;
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewBookingNumber, textViewEconOrBus, textViewOutboundOrInbound;
        public Button buttonCheckIn, buttonModifyBooking;

        public BookingViewHolder(View itemView) {
            super(itemView);
            textViewBookingNumber = itemView.findViewById(R.id.textViewBookingNumber);
            textViewEconOrBus = itemView.findViewById(R.id.textViewEconOrBus);
            textViewOutboundOrInbound = itemView.findViewById(R.id.textViewOutboundOrInbound);
            buttonCheckIn = itemView.findViewById(R.id.buttonOnlineCheckIn);
            buttonModifyBooking = itemView.findViewById(R.id.buttonChangeBooking);
        }
    }
}
