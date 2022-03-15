package com.example.uscrecapp_team28;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class BookingInformationAdapter extends RecyclerView.Adapter {
    private ArrayList<BookingItem> mBookingList;
    Context context;
    public static class BookingInformationViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public AppCompatButton cancelButton;

        public BookingInformationViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            cancelButton = itemView.findViewById(R.id.CancelButton);
        }
    }
    public BookingInformationAdapter(ArrayList<BookingItem> BookingList) {
        mBookingList= BookingList;
    }
    @Override
    public BookingInformationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item, parent, false);
        context = parent.getContext();
        BookingInformationViewHolder evh = new BookingInformationViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BookingItem currentItem = mBookingList.get(position);
        ((BookingInformationViewHolder) holder).mTextView1.setText(currentItem.getText1());
        ((BookingInformationViewHolder) holder).mTextView2.setText(currentItem.getText2());
        ((BookingInformationViewHolder) holder).cancelButton.setTag(currentItem.getmReservationId());
        ((BookingInformationViewHolder) holder).cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reservation_id = (String)view.getTag();
                try {
                    new CancelBooking(reservation_id).execute().get();

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                context.startActivity(new Intent(context,BookingInformationActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {

        return mBookingList.size();
    }
    //cancel the booking information based on reservation_id
    class CancelBooking extends AsyncTask<Void, Void, Void> {
        String reservation_id;

        //set parameter for async function
        public CancelBooking(String reservation_id){
            this.reservation_id = reservation_id;
        }
        @Override
        protected Void doInBackground(Void... voids){
            try{
                System.out.println("enter background");
                //connect to sql database
                Class.forName("com.mysql.jdbc.Driver");
                String connectionUrl = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3479112?characterEncoding=latin1";
                Connection connection = DriverManager.getConnection(connectionUrl,"sql3479112","k1Q9Fq3375");
                Statement s = connection.createStatement();
                System.out.println("after connection");
                //query the database for all user's reservation
                String query = String.format(
                        "DELETE from reservation\n" +
                        "\tWHERE reservation.reservation_id=%s;", reservation_id);

                s.executeUpdate(query);
                System.out.println(String.format("after execution of query delete %s from database",reservation_id));
            } catch (Exception e){
                System.out.println("Exception");
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
        }
    }
}
