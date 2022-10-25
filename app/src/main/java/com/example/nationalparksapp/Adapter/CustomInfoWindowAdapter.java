package com.example.nationalparksapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nationalparksapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;
    private View view;
    private LayoutInflater layoutInflater;

    public CustomInfoWindowAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.custom_info_window, null);
    }

    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        return null;
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        TextView parkNameTextView = view.findViewById(R.id.info_park_name_textview);
        TextView parkStatesTextView = view.findViewById(R.id.info_state_textview);

        parkNameTextView.setText(marker.getTitle());
        parkStatesTextView.setText("States: " + marker.getSnippet());
        return view;
    }
}
