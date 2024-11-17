package br.com.vidaluporte.springSemWeb;

import br.com.vidaluporte.springSemWeb.principal.PrincipalJPA;
import br.com.vidaluporte.springSemWeb.principal.PrincipalLambdas;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSemWebApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringSemWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		PrincipalLambdas principal = new PrincipalLambdas();
//		principal.exibeMenu();

		PrincipalJPA principalJPA = new PrincipalJPA();
		principalJPA.exibeMenu();






	}
}
