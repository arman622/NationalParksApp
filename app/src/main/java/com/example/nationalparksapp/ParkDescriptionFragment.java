package com.example.nationalparksapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nationalparksapp.Adapter.ViewPagerAdapter;
import com.example.nationalparksapp.Model.Activities;
import com.example.nationalparksapp.Model.Addresses;
import com.example.nationalparksapp.Model.OperatingHours;
import com.example.nationalparksapp.Model.Park;
import com.example.nationalparksapp.Model.ParkViewModel;
import com.example.nationalparksapp.Model.StandardHours;
import com.example.nationalparksapp.Model.Topics;

import java.text.MessageFormat;

public class ParkDescriptionFragment extends Fragment {

    private ParkViewModel parkViewModel;
    private TextView nameTextView, typeParkTextView, descriptionTextView, activitiesTextView,
            topicsTextView, entranceFeesTextView, operatingHoursTextView, addressesTextView,
            directionTextview;
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    public ParkDescriptionFragment() {
    }

    public static ParkDescriptionFragment newInstance() {
        ParkDescriptionFragment fragment = new ParkDescriptionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_park_description, container, false);
        nameTextView = view.findViewById(R.id.name_textview);
        viewPager = view.findViewById(R.id.detail_viewpager);
        typeParkTextView = view.findViewById(R.id.type_park_textview);

        descriptionTextView = view.findViewById(R.id.description_textview);
        activitiesTextView = view.findViewById(R.id.activities_textview);
        topicsTextView = view.findViewById(R.id.topics_textview);
        entranceFeesTextView = view.findViewById(R.id.entrance_fees_textview);
        operatingHoursTextView = view.findViewById(R.id.operating_hours_textview);
        addressesTextView = view.findViewById(R.id.addresses_textview);
        directionTextview = view.findViewById(R.id.direction_textview);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        parkViewModel = new ViewModelProvider(requireActivity()).get(ParkViewModel.class);

        parkViewModel.getSelectedPark().observe(this, new Observer<Park>() {
            @Override
            public void onChanged(Park park) {
                nameTextView.setText(park.getName());
                typeParkTextView.setText(park.getDesignation() + ", " + park.getStates());
                //SetUp for ViewPager and adapter
                viewPagerAdapter = new ViewPagerAdapter(park.getImages());
                viewPager.setAdapter(viewPagerAdapter);

                //Description
                descriptionTextView.setText(park.getDescription());

                //Activities For Loop && String Builder
                StringBuilder activitiesStringBuilder = new StringBuilder("");
                for (int i = 0; i < park.getActivities().size(); i++ ){
                    Activities currActivities = park.getActivities().get(i);
                    activitiesStringBuilder.append(currActivities.getName() + " | ");
                }
                activitiesTextView.setText(activitiesStringBuilder.toString());

                //Topics For Loop && String Builder
                StringBuilder topicsStringBuilder = new StringBuilder("");
                for (int i = 0; i < park.getTopics().size(); i++ ){
                    Topics currTopic = park.getTopics().get(i);
                    topicsStringBuilder.append(currTopic.getName() + " | ");
                }
                topicsTextView.setText(topicsStringBuilder.toString());

                //Entrance Fees
                if(park.getEntranceFees().size() > 0){
                    entranceFeesTextView.setText("Costs: $" + park.getEntranceFees().get(0).getCost());
                } else {
                    entranceFeesTextView.setText("Info not available");
                }

                //OperatingHours String Builder
                StringBuilder operatingHoursStringBuilder = new StringBuilder("");
                OperatingHours operatingHours = park.getOperatingHours().get(0);
                StandardHours standardHours = operatingHours.getStandardHours();
                operatingHoursStringBuilder.append("Monday: " + standardHours.getMonday()+"\n");
                operatingHoursStringBuilder.append("Tuesday: " + standardHours.getTuesday()+"\n");
                operatingHoursStringBuilder.append("Wednesday: " + standardHours.getWednesday()+"\n");
                operatingHoursStringBuilder.append("Thursday: " + standardHours.getThursday() + "\n");
                operatingHoursStringBuilder.append("Friday: " +standardHours.getFriday() + "\n");
                operatingHoursStringBuilder.append("Saturday: " + standardHours.getSaturday() + "\n");
                operatingHoursStringBuilder.append("Sunday: " + standardHours.getSunday());
                operatingHoursTextView.setText(operatingHoursStringBuilder.toString());

                //Addresses get the first address (Physical addresses)
                Addresses address = park.getAddresses().get(0);
                addressesTextView.setText(MessageFormat.format("{0}, {1}, {2}, {3}",
                        address.getLine1(), address.getCity(), address.getStateCode(), address.getPostalCode()));

                //DirectionInfo
                if(!TextUtils.isEmpty(park.getDirectionsInfo())){
                    directionTextview.setText(park.getDirectionsInfo());
                } else {
                    directionTextview.setText("Direction not available");
                }
            }
        });


    }
}