package com.erik.projeto.service;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erik.projeto.dto.TransactionCompletedDto;
import com.erik.projeto.entities.Item;
import com.erik.projeto.entities.Player;
import com.erik.projeto.entities.Transaction;
import com.erik.projeto.enums.StatusOfItem;
import com.erik.projeto.enums.TypeOfTransation;
import com.erik.projeto.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	ItemService itemService;
	
	public TransactionService() {
	}

	//------------------------------------//
	
	public TransactionCompletedDto saveTransaction( Player buyer, Player seller, Item item  ) {
		
	
		Transaction transaction1 = new Transaction();
		transaction1.setType( TypeOfTransation.SALE.toString() );
		transaction1.setCreation( new Date() );
		transaction1.setItem(item);
		transaction1.setPlayer(seller);
		
		transactionRepository.save( transaction1 );
		
		//----
		
		item.setPlayer(buyer);
		item.setStatusOfItem( StatusOfItem.IN_USE.toString() );
		
		//Update item in the data base.
		itemService.saveItem(item);

		//----
		
		Transaction transaction2 = new Transaction();
		transaction2.setType( TypeOfTransation.PURCHASE.toString() );
		transaction2.setCreation( new Date() );
		transaction2.setItem(item);
		transaction2.setPlayer(buyer);
		
		transactionRepository.save( transaction2 );
		
		//---
		
		TransactionCompletedDto transactionCompletedDto = new TransactionCompletedDto();
		transactionCompletedDto.setSellerTransaction(transaction1);
		transactionCompletedDto.setBuyerTransaction( transaction2 );
		
		return transactionCompletedDto;
	}


}