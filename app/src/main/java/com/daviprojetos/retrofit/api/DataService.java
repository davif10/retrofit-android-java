package com.daviprojetos.retrofit.api;

import com.daviprojetos.retrofit.model.Foto;
import com.daviprojetos.retrofit.model.Postagem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("/photos")
    Call <List<Foto>>recuperarFotos();

    @GET("/posts")
    Call <List<Postagem>>recuperarPostagens();
}
