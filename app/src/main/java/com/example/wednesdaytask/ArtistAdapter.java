package com.example.wednesdaytask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wednesdaytask.Models.Result;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

    LayoutInflater layoutInflater;
    List<Result> resultList;

    public ArtistAdapter(Context context, List<Result> resultList) {
        this.layoutInflater = layoutInflater.from(context);
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =layoutInflater.inflate(R.layout.custom_grid_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(layoutInflater.getContext()).load(resultList.get(position).getArtworkUrl30()).into(holder.artistImg);
        holder.name.setText(resultList.get(position).getArtistName());
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView artistImg;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            artistImg = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
        }
    }

}
