package com.example.mainapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    List<House> returnList;
    Context context;

    public RecycleViewAdapter(List<House> returnList, Context context) {
        this.returnList = returnList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_house,parent,false);
        MyViewHolder holder = new MyViewHolder(view);




        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_showTown.setText(returnList.get(position).getTown());
        holder.tv_showBedroomNum.setText(String.valueOf(returnList.get(position).getBedroom()));
        holder.tv_showResalePrice.setText(String.valueOf(returnList.get(position).getResale_price()));
        Glide.with(this.context).load(returnList.get(position).getImageURL()).into(holder.iv_housePicture);



    }

    @Override
    public int getItemCount() {
        return returnList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_housePicture;
        TextView tv_showTown,tv_showBedroomNum,tv_showResalePrice;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_housePicture = itemView.findViewById(R.id.iv_housePicture);
            tv_showTown = itemView.findViewById(R.id.tv_showTown);
            tv_showBedroomNum = itemView.findViewById(R.id.tv_showBedroomNum);
            tv_showResalePrice = itemView.findViewById(R.id.tv_showResalePrice);

        }
    }
}
