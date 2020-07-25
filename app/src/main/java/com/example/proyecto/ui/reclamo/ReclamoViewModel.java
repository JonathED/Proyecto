package com.example.proyecto.ui.reclamo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReclamoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReclamoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("-->Reclamo");
    }

    public LiveData<String> getText() {
        return mText;
    }
}