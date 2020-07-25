package com.example.proyecto.ui.ubigeo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UbigeoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UbigeoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("-->Ubigeo");
    }

    public LiveData<String> getText() {
        return mText;
    }
}