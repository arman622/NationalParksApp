package com.example.nationalparksapp.Data;

import com.example.nationalparksapp.Model.Park;

import java.util.List;

public interface AsyncResponse {
    void processPark(List<Park> parks);
}
