package com.daviprojetos.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daviprojetos.retrofit.api.CEPService;
import com.daviprojetos.retrofit.api.DataService;
import com.daviprojetos.retrofit.model.CEP;
import com.daviprojetos.retrofit.model.Foto;
import com.daviprojetos.retrofit.model.Postagem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button botaoRecuperar;
    private TextView textoResultado;
    private Retrofit retrofit;
    //private List<Foto> listaFotos = new ArrayList<>();
    private List<Postagem> listaPostagem = new ArrayList<>();
    private DataService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botaoRecuperar = findViewById(R.id.buttonRecuperar);
        textoResultado = findViewById(R.id.textResultado);

        retrofit = new Retrofit.Builder()
                //.baseUrl("https://viacep.com.br/ws/")
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(DataService.class);

        botaoRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recuperarCEPRetrofit();
                //recuperarListaRetrofit();
                //salvarPostagem();
                //atualizarPostagem();
                removerPostagem();

            }
        });

    }

    private void removerPostagem(){
        Call<Void> call = service.removerPostagem(2);//Simulando a remoção do id 2
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    textoResultado.setText("Status: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    private void atualizarPostagem(){
        //Postagem postagem = new Postagem("1234",null,"Corpo postagem");
        //Call<Postagem> call = service.atualizarPostagem(2,postagem);
        Postagem postagem = new Postagem();
        postagem.setBody("Corpo da postagem alterado");

        Call<Postagem> call = service.atualizarPostagemPatch(2,postagem);
        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {

                if(response.isSuccessful()){
                    Postagem postagemResposta = response.body();
                    textoResultado.setText(
                            "Status: "+ response.code()+
                                    " id: "+postagemResposta.getId()+
                                    " userId: "+postagemResposta.getUserId()+
                                    " titulo: "+postagemResposta.getTitle()+
                                    " body: "+postagemResposta.getBody()

                    );
                }

            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });


    }

    private void salvarPostagem(){
        //Configurar objeto postagem
        //Postagem postagem = new Postagem("1234","Título Postagem!","Corpo postagem");

        //Recupera o serviço e salva postagem
        Call<Postagem> call = service.salvarPostagem("1234","Título Postagem!","Corpo postagem");

        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if(response.isSuccessful()){
                    Postagem postagemResposta = response.body();
                    textoResultado.setText(
                            "Código: "+ response.code()+
                            " id: "+postagemResposta.getId()+
                            " Título: "+postagemResposta.getTitle());
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    private void recuperarListaRetrofit(){
        //Call<List<Foto>> call = service.recuperarFotos();
        Call<List<Postagem>> call = service.recuperarPostagens();

        call.enqueue(new Callback<List<Postagem>>() {
            @Override
            public void onResponse(Call<List<Postagem>> call, Response<List<Postagem>> response) {
                if(response.isSuccessful()){
                    listaPostagem = response.body();
                    for(int i = 0 ; i<listaPostagem.size();i++){
                        Postagem postagem = listaPostagem.get(i);
                        System.out.println("Resultado: "+ postagem.getId() +" / "+ postagem.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Postagem>> call, Throwable t) {

            }
        });

    }
    private void recuperarCEPRetrofit(){
        CEPService cepService = retrofit.create(CEPService.class);
        Call<CEP> call = cepService.recuperarCep("01001000");
        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if(response.isSuccessful()){
                    CEP cep = response.body();
                    textoResultado.setText(cep.getLogradouro() +" / "+ cep.getBairro());
                }

            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });
    }
}