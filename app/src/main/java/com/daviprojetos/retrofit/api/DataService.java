package com.daviprojetos.retrofit.api;

import com.daviprojetos.retrofit.model.Foto;
import com.daviprojetos.retrofit.model.Postagem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataService {
    //////////Recupera os dados////////
    //Para formato Json
    @GET("/photos")
    Call <List<Foto>>recuperarFotos();

    @GET("/posts")
    Call <List<Postagem>>recuperarPostagens();

    //////////Envia os dados////////
    @POST("/posts")
    Call<Postagem> salvarPostagem(@Body Postagem postagem);

    //userId=1234&título postagem&body= Corpo postagem (Para API que usam métodos de comunicação XML ou outra comunicação)
    @FormUrlEncoded
    @POST("/posts")
    Call<Postagem> salvarPostagem(
            @Field("userId") String userId,
            @Field("title") String title,
            @Field("body") String body
    );

    //////////Atualiza os dados////////

    @PUT("/posts/{id}")
    Call<Postagem> atualizarPostagem(@Path("id") int id, @Body Postagem postagem);
    //Post: Atualiza todos os campos da Postagem(Inclusive com nulo)

    @PATCH("/posts/{id}")
    Call<Postagem>atualizarPostagemPatch(@Path("id") int id, @Body Postagem postagem);
    //Patch: se algum campo for enviado como nulo ele mantem o campo com o valor já existente
    //Quando é enviado como nulo, é considerado um campo que não queira atualizar

    /////////Deletar os dados///////

    @DELETE("/posts/{id}")
    Call<Void> removerPostagem(@Path("id") int id);


}
