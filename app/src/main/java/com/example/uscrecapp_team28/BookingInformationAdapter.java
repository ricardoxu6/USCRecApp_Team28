package com.example.uscrecapp_team28;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
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

public class BookingInformationAdapter<MyActivity> extends RecyclerView.Adapter {
    private ArrayList<BookingItem> mBookingList;
    Context context;
    private Agent mAgent;
    public static class BookingInformationViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public AppCompatButton cancelButton;
        public RelativeLayout backImage;

        public BookingInformationViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            cancelButton = itemView.findViewById(R.id.CancelButton);
            backImage = (RelativeLayout)itemView.findViewById(R.id.imageBackGround);
        }
    }
    public BookingInformationAdapter(ArrayList<BookingItem> BookingList,Agent agent) {
        mBookingList= BookingList;
        mAgent = agent;
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
//        System.out.println(currentItem.getmText1());
//        String PACKAGE_NAME = context.getApplicationContext().getPackageName();
        if (currentItem.getmText1().equals("Lyon Center")) {
            ((BookingInformationViewHolder) holder).backImage.setBackground(ContextCompat.getDrawable(context, R.drawable.lyon));
        } else {
            ((BookingInformationViewHolder) holder).backImage.setBackground(ContextCompat.getDrawable(context, R.drawable.village));
        }
        ((BookingInformationViewHolder) holder).cancelButton.setTag(currentItem.getmReservationId());
        ((BookingInformationViewHolder) holder).cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reservation_id = (String)view.getTag();
                mAgent.cancel_reservation(reservation_id);
                context.startActivity(new Intent(context,BookingInformationActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBookingList.size();
    }
}
