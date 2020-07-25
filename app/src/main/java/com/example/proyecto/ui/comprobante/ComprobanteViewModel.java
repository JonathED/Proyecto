package com.example.proyecto.ui.comprobante;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ComprobanteViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ComprobanteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("-->Comprobante");
    }

    public LiveData<String> getText() {
        return mText;
    }
}