package com.example.nationalparksapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nationalparksapp.Model.Park;
import com.example.nationalparksapp.ParksFragment;
import com.example.nationalparksapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ParkAdapter extends RecyclerView.Adapter<ParkAdapter.ViewHolder> {
    private final List<Park> parkList;
    private OnParkClickListener listener;

    public ParkAdapter(List<Park> parkList) {
        this.parkList = parkList;
    }


    @NonNull
    @Override
    public ParkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.park_recycler_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkAdapter.ViewHolder holder, int position) {
        Park park = parkList.get(position);

        holder.fullNameTextView.setText(park.getName());
        holder.designationTextView.setText(park.getDesignation());
        holder.stateTextView.setText("States: " + park.getStates());

        if(park.getImages().size() > 0){
            Picasso.get()
                    .load(park.getImages().get(0).getUrl())
                    .placeholder(android.R.drawable.stat_sys_download)
                    .error(android.R.drawable.stat_notify_error)
                    .resize(100,100)
                    .centerCrop()
                    .into(holder.parkImageView);

        }
    }

    @Override
    public int getItemCount() {
        return parkList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView parkImageView;
        public TextView fullNameTextView;
        public TextView designationTextView;
        public TextView stateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parkImageView = itemView.findViewById(R.id.park_imageview_row);
            fullNameTextView = itemView.findViewById(R.id.full_name_textview_row);
            designationTextView = itemView.findViewById(R.id.designation_textview_row);
            stateTextView = itemView.findViewById(R.id.state_textview_row);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onParkClick(parkList.get(position));
                    }
                }
            });
        }
    }

    public interface OnParkClickListener{
        void onParkClick(Park park);
    }

    public void setOnItemClickListener(OnParkClickListener listener){
        this.listener = listener;
    }

}
