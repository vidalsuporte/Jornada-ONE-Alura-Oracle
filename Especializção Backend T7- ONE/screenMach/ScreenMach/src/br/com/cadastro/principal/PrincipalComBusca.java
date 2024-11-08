package br.com.cadastro.principal;

import br.com.cadastro.dto.DTOTitulo;
import br.com.cadastro.modelo.Titulo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.source.tree.TryTree;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);

        List<Titulo> titulos = new ArrayList<>();
        String filme = "";
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (!filme.equalsIgnoreCase("sair")) {
            System.out.println("Digite o nome do filme: ");
            filme = leitura.nextLine();
            if(filme.equalsIgnoreCase("sair")){
                break;
            }

            String endereco = "https://www.omdbapi.com/?apikey=4dfbd808&t=" + filme.replace(' ', '+');

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println(response.body());



                DTOTitulo titulo = gson.fromJson(response.body(), DTOTitulo.class);
                System.out.println("Dados dto" + titulo);

                //try {
                 titulos.add(new Titulo(titulo));


            } catch (NumberFormatException e) {
                System.out.println("Algo deu errado.");
                System.out.println(e.getMessage());

            } catch (IllegalArgumentException e) {
                System.out.println("Algum erro na busca.");
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(titulos.toString());

        FileWriter escrita = new FileWriter("filmes.json");
        escrita.write(gson.toJson(titulos));
        escrita.close();

        System.out.println("O programa finalizou");

    }






}
