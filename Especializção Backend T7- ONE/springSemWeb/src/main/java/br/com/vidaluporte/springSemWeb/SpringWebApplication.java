package br.com.vidaluporte.springSemWeb;

import br.com.vidaluporte.springSemWeb.principal.PrincipalJPA;
import br.com.vidaluporte.springSemWeb.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebApplication  {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebApplication.class, args);
	}

}
