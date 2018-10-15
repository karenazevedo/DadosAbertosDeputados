package com.karen.trabalhofinal.Interfaces;

import com.karen.trabalhofinal.Model.InfoDeputado;

import org.json.JSONObject;

public interface LoadReceiverDelegate {

    void setLoadStatus(boolean status);

    void processFinish(InfoDeputado result);

}
