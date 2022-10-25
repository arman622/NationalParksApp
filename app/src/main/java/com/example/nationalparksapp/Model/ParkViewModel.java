package com.example.nationalparksapp.Model;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ParkViewModel extends ViewModel {

    private final MutableLiveData<Park> selectedPark = new MutableLiveData<>();
    private final MutableLiveData<List<Park>> selectedParkList = new MutableLiveData<>();
    private final MutableLiveData<String> stateCode = new MutableLiveData<>();

    public LiveData<String> getStateCode() {return stateCode; }
    public void setSelectStateCode(String code) { stateCode.setValue(code);}

    public LiveData<Park> getSelectedPark() {
        return selectedPark;
    }
    public void setSelectPark(Park park) {
        selectedPark.setValue(park);
    }

    public LiveData<List<Park>> getParkList() { return  selectedParkList; }
    public void setSelectedParkList(List<Park> parks) {
         selectedParkList.setValue(parks);
    }
}
