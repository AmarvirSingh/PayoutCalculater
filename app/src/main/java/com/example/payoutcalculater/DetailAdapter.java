package com.example.payoutcalculater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payoutcalculater.Util.DetailEntity;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {


    private List<DetailEntity> dataList;
    private Context context;

    public DetailAdapter(Context context,List<DetailEntity> entityList) {
        this.dataList = entityList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_cell_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DetailEntity entity = dataList.get(position);
        holder.name.setText( entity.getEmployer());
        holder.day.setText( entity.getDay());
        holder.hour.setText(String.valueOf(entity.getHour()));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView day;
        TextView hour;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.tvName);
            day = itemView.findViewById(R.id.tvDay);
            hour = itemView.findViewById(R.id.tvHour);

        }
    }
}
