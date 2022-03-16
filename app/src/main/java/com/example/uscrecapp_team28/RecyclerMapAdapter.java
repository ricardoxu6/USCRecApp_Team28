package com.example.uscrecapp_team28;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerMapAdapter extends RecyclerView.Adapter {

    private ArrayList<BookingItem> mBookingList;
    Context context;
    private Agent mAgent;
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
    public RecyclerMapAdapter(ArrayList<BookingItem> BookingList,Agent agent) {
        mBookingList= BookingList;
        mAgent = agent;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_item, parent, false);
        context = parent.getContext();
        BookingInformationAdapter.BookingInformationViewHolder evh = new BookingInformationAdapter.BookingInformationViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BookingItem currentItem = mBookingList.get(position);
        ((BookingInformationAdapter.BookingInformationViewHolder) holder).mTextView1.setText(currentItem.getText1());
        ((BookingInformationAdapter.BookingInformationViewHolder) holder).mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mBookingList.size();
    }
}
