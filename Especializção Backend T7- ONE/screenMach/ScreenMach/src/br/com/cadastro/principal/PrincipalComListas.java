package br.com.cadastro.principal;

import br.com.cadastro.modelo.Filme;
import br.com.cadastro.modelo.Serie;
import br.com.cadastro.modelo.Titulo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PrincipalComListas {
    public static void main(String[] args) {
        Filme meuFilme = new Filme("hitiman", 2010);
        meuFilme.avalia(10.00);
        Filme outroFilme = new Filme("avatar",2013 );
        outroFilme.avalia(8.0);
        Serie serie = new Serie("lost", 2000);

        Filme dogville = new Filme("dogville",2003 );
        dogville.avalia(5.0);
        ArrayList<Titulo> titulos = new ArrayList<>();
        titulos.add(meuFilme);
        titulos.add(outroFilme);
        titulos.add(serie);
        titulos.add(dogville);

        System.out.println("tamanho da Lista: " + titulos.size());
        System.out.println("Nome do Primeiro Título da Lista: " + titulos.get(0).getNome() );

       for(Titulo titulo: titulos){
           System.out.println(titulo);
           if(titulo instanceof Filme filme && filme.getClassificacao() > 2){
               System.out.println("Classificação: " + filme.getClassificacao());

           }
           System.out.println("---------------------------------------");
      }
        System.out.println("após ordenação Alfabetica");
        Collections.sort(titulos);
        titulos.forEach(System.out::println);

        System.out.println("após ordenação ano lançamento");
        titulos.sort(Comparator.comparing(Titulo::getAnoLancamento));
        titulos.forEach(System.out::println);









    }

}
