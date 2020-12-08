package com.example.a31_livedata_viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<UserProfile> liveData = new MutableLiveData<>();

    public MutableLiveData<UserProfile> getLiveData() {
        return liveData;
    }

    public void setLiveData(MutableLiveData<UserProfile> liveData) {
        this.liveData = liveData;
    }
}
