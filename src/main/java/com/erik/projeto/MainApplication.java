package com.erik.projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.erik.projeto.entities.Item;
import com.erik.projeto.entities.Player;
import com.erik.projeto.enums.StatusOfItem;
import com.erik.projeto.repositories.ItemRepository;
import com.erik.projeto.repositories.PlayerRepository;

@SpringBootApplication
public class MainApplication {
	

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private ItemRepository itemRepository;
	
	
	public static void main(String[] args) {
			
		SpringApplication.run(MainApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(){
			
		return args -> {

			Player player1 = new Player();
			player1.setName("Erik");
			
			Player player2 = new Player();
			player2.setName("Duck Donald");

			Player player3 = new Player();
			player3.setName("Lima");			

			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			
			
			//---
			
			Item item1 = new Item();
			item1.setName("Item 1");
			item1.setStatusOfItem( StatusOfItem.FOR_SALE.toString() );
			item1.setPlayer(player1);
			
			Item item2 = new Item();
			item2.setName("Item 2");
			item2.setStatusOfItem( StatusOfItem.FOR_SALE.toString() );
			item2.setPlayer(player2);			
			
			Item item3 = new Item();
			item3.setName("Item 3");
			item3.setStatusOfItem( StatusOfItem.FOR_SALE.toString() );
			item3.setPlayer(player3);

			itemRepository.save(item1);
			itemRepository.save(item2);
			itemRepository.save(item3);
			
			//---


		};
	}

}