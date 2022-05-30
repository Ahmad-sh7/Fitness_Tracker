package com.example.myfitnesstracker.viewmodel;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
  int x =0;

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }
}
