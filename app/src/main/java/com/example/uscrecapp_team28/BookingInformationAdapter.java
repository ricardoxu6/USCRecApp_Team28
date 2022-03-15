package com.example.uscrecapp_team28;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookingInformationAdapter extends RecyclerView.Adapter {
    private ArrayList<BookingItem> mBookingList;
    public static class BookingInformationViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;

        public BookingInformationViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
        }
    }
    public BookingInformationAdapter(ArrayList<BookingItem> BookingList) {
        mBookingList= BookingList;
        for(BookingItem b:mBookingList){
            System.out.println(b.getText1());
            System.out.println(b.getText2());
        }
    }
    @Override
    public BookingInformationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item, parent, false);
        BookingInformationViewHolder evh = new BookingInformationViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BookingItem currentItem = mBookingList.get(position);
        ((BookingInformationViewHolder) holder).mTextView1.setText(currentItem.getText1());
        ((BookingInformationViewHolder) holder).mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {

        return mBookingList.size();
    }
}
