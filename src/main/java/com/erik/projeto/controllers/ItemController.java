package com.erik.projeto.controllers;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erik.projeto.dto.ItemDto;
import com.erik.projeto.entities.Item;
import com.erik.projeto.entities.Player;
import com.erik.projeto.enums.StatusOfItem;
import com.erik.projeto.response.StandardizedResponse;
import com.erik.projeto.service.ItemService;
import com.erik.projeto.service.PlayerService;


@RestController
@RequestMapping(value="/api/item", produces="application/json")
@CrossOrigin(origins = "*")
public class ItemController {

	
	private static final Logger log = LoggerFactory.getLogger( ItemController.class );

	@Autowired
	PlayerService playerService;
	
	@Autowired
	ItemService itemService;
	
	public ItemController() {
	}


	@PostMapping(value = "/offerForSale") 
	public ResponseEntity< StandardizedResponse<Item> > offerForSale( @Valid @RequestBody ItemDto       itemDto,
			                                                                              BindingResult resultadoDaValidacao ) throws NoSuchAlgorithmException {
		
		log.info("\n\nEndpoint: /api/item/offerForSale");


		StandardizedResponse<Item> standardizedResponse = new StandardizedResponse<Item>();
		

		//Verificando a validacao dos dados de entrada feita automaticamente pelo Hibernate, com base nas anotacoes feitas na classe "cadastroPFDto".
		if ( resultadoDaValidacao.hasErrors() ) {
			
			//A variavel "resultadoDaValidacao" eh enviado pela validacao que o Spring fez, baseado nas anotacoes de validacao que estao la na classe "cadastroPFDto".
			//Entao, pego a lista de erros dessa variavel.
			List<ObjectError> listaDeErros = resultadoDaValidacao.getAllErrors();
			
			
			for( ObjectError auxiliar : listaDeErros  ){
				
				//Pego a mensagem de erro da posicao atual.
				String mensagemDeErroExtraida = auxiliar.getDefaultMessage();
						
				standardizedResponse.getErrors().add(mensagemDeErroExtraida);
			}

			return ResponseEntity.badRequest().body( standardizedResponse );
		}	

		//---------
		
		//I check if the player I received exists in the database.
		Player player = playerService.findById( itemDto.getPlayerId() );
		
		if( player == null ) {
			
			standardizedResponse.getErrors().add("There is no player with the given id.");
			
			return ResponseEntity.badRequest().body( standardizedResponse );
		}

		//---------
		
		Item item = new Item();
		item.setName( itemDto.getName() );
		item.setPlayer( player );
		item.setStatusOfItem( StatusOfItem.FOR_SALE.toString() );
		
		//---------		
		
		itemService.saveItem( item );
		
		//---------	
		
		standardizedResponse.setConteudoDoResponse( item );
		
		return ResponseEntity.ok().body( standardizedResponse );
	}

	
	@GetMapping(value = "/allForSale") 
	public ResponseEntity< StandardizedResponse< List<Item> > > allForSale() throws NoSuchAlgorithmException {
		
		log.info("\n\nEndpoint: /api/item/allForSale");

		StandardizedResponse< List<Item> >  standardizedResponse = new StandardizedResponse< List<Item> >();
				
		//---------		
		
		List<Item> listOfItemForSale = itemService.getAllItemsForSale();
		
		//---------	
		
		standardizedResponse.setConteudoDoResponse( listOfItemForSale );
		
		return ResponseEntity.ok().body( standardizedResponse );
	}
	
}