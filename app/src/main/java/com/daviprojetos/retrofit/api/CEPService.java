package com.daviprojetos.retrofit.api;

import com.daviprojetos.retrofit.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {
    @GET("{cep}/json/")
    Call <CEP> recuperarCep(@Path("cep") String cep);


}
