package com.example.nationalparksapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nationalparksapp.Adapter.ParkAdapter;
import com.example.nationalparksapp.Data.AsyncResponse;
import com.example.nationalparksapp.Data.Repository;
import com.example.nationalparksapp.Model.Park;
import com.example.nationalparksapp.Model.ParkViewModel;

import java.util.ArrayList;
import java.util.List;


public class ParksFragment extends Fragment {

    private RecyclerView mParkRecyclerView;
    private ParkAdapter mParkAdapter;
    private ParkViewModel parkViewModel;

    public ParksFragment() {
        // Required empty public constructor
    }

    public static ParksFragment newInstance() {
        ParksFragment fragment = new ParksFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parks, container, false);

        //setup for recycler view;
        mParkRecyclerView = view.findViewById(R.id.park_recycler);
        mParkRecyclerView.setHasFixedSize(true);
        mParkRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        parkViewModel = new ViewModelProvider(requireActivity()).get(ParkViewModel.class);

        //Getting ParkList from ViewModel.observe and setAdapter into RecyclerView
        parkViewModel.getParkList().observe(this, new Observer<List<Park>>() {
            @Override
            public void onChanged(List<Park> parkList) {

                if(parkList != null) {
                    mParkAdapter = new ParkAdapter(parkList);
                    mParkRecyclerView.setAdapter(mParkAdapter);
                }

                //OnClickerListener Event
                mParkAdapter.setOnItemClickListener(new ParkAdapter.OnParkClickListener() {
                    @Override
                    public void onParkClick(Park park) {
                        Log.d("Clicked", "onParkClick: " + park.getName());
                        parkViewModel.setSelectPark(park);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.park_fragment, ParkDescriptionFragment.newInstance())
                                .commit();
                    }
                });
            }
        });
    }
}