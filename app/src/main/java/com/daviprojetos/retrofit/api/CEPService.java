package com.daviprojetos.retrofit.api;

import com.daviprojetos.retrofit.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CEPService {
    @GET("01001000/json/")
    Call <CEP> recuperarCep();


}
