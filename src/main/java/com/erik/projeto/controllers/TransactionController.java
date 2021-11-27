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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.erik.projeto.dto.BuyAnItemDto;
import com.erik.projeto.dto.TransactionCompletedDto;
import com.erik.projeto.entities.Item;
import com.erik.projeto.entities.Player;
import com.erik.projeto.response.StandardizedResponse;
import com.erik.projeto.service.ItemService;
import com.erik.projeto.service.PlayerService;
import com.erik.projeto.service.TransactionService;

@RestController
@RequestMapping(value="/api/transaction", produces="application/json")
@CrossOrigin(origins = "*")
public class TransactionController {

	
	private static final Logger log = LoggerFactory.getLogger( TransactionController.class );

	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	ItemService itemService;
	
	
	public TransactionController() {
	}


	@PostMapping(value ="/buyAnItem") 
	public ResponseEntity< StandardizedResponse<TransactionCompletedDto> > buyAnItem ( @Valid @RequestBody BuyAnItemDto buyAnItemDto,
			                                                                                               BindingResult resultadoDaValidacao ) throws NoSuchAlgorithmException {

	
		log.info("\n\nEndpoint: /api/transaction/buyAnItem");

		
		StandardizedResponse< TransactionCompletedDto >  standardizedResponse = new StandardizedResponse< TransactionCompletedDto >();
		
		
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
		
		//--
		//TODO
		// Check if the seller exists
		// Check if the buyer exists
		// check if the item exists
		
		//---
		
		Player buyer  = playerService.findById( buyAnItemDto.getPlayerId_buyer() );
		Player seller = playerService.findById( buyAnItemDto.getPlayerId_seller() );
		Item   item   = itemService.getItemById(buyAnItemDto.getItemId() );
		
		//---
		
		TransactionCompletedDto transactionCompletedDto = transactionService.saveTransaction( buyer, seller, item  );
		
		//---
		
		standardizedResponse.setConteudoDoResponse( transactionCompletedDto );
		
		return ResponseEntity.ok().body( standardizedResponse );
	}


}