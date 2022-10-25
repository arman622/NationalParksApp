package com.example.nationalparksapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nationalparksapp.Model.Images;
import com.example.nationalparksapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ImageSlider> {
    private List<Images> mImageList;

    public ViewPagerAdapter(List<Images> mImageList) {
        this.mImageList = mImageList;
    }

    @NonNull
    @Override
    public ViewPagerAdapter.ImageSlider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_row, parent,false);
        return new ImageSlider(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.ImageSlider holder, int position) {
        Images images = mImageList.get(position);

        Picasso.get()
                .load(images.getUrl())
                .fit()
                .placeholder(android.R.drawable.stat_sys_download)
                .placeholder(android.R.drawable.stat_notify_error)
                .into(holder.imageViewPageViewer);
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    public class ImageSlider extends RecyclerView.ViewHolder {

        private ImageView imageViewPageViewer;

        public ImageSlider(@NonNull View itemView) {
            super(itemView);
            imageViewPageViewer = itemView.findViewById(R.id.viewpager_imageview);
        }
    }
}
