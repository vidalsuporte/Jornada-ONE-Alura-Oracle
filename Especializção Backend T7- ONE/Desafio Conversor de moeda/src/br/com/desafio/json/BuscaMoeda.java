package br.com.desafio.json;

import br.com.desafio.dto.DtoMoedaConsulta;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class BuscaMoeda {


    String url = "https://v6.exchangerate-api.com/v6/b28d5bc8a7d09335dfdec12b/latest/";
    Gson gson = new Gson();
    public DtoMoedaConsulta buscaMoeda()  {
try{
        Scanner entrada = new Scanner(System.in);


        System.out.println("Digite a moeda");
        String moeda = entrada.next().toUpperCase();

        url += moeda;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)).
                build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        /*System.out.println(response.body());
        System.out.println("--------------------");*/
          DtoMoedaConsulta dtoMoedaConsulta = gson.fromJson(response.body(), DtoMoedaConsulta.class);
        return dtoMoedaConsulta;

} catch (IOException e) {
    throw new RuntimeException(e);

} catch (InterruptedException e) {
//            throw new RuntimeException(e);

}
        return null;

}



    public DtoMoedaConsulta getMoeda(String moeda)  {
        try{

            url += moeda;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url)).
                    build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        /*System.out.println(response.body());
        System.out.println("--------------------");*/
            DtoMoedaConsulta dtoMoedaConsulta = gson.fromJson(response.body(), DtoMoedaConsulta.class);
            return dtoMoedaConsulta;

        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (InterruptedException e) {
//            throw new RuntimeException(e);

        }
        return null;

    }



}
