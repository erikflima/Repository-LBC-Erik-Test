package com.erik.projeto.dto;
import com.erik.projeto.entities.Transaction;

public class TransactionCompletedDto {
	
	private Transaction buyerTransaction;
	
	private Transaction sellerTransaction;


	public TransactionCompletedDto(){
	}

	//-------------------------Getters and Setters----------------------//	
	
	public Transaction getBuyerTransaction() {
		return buyerTransaction;
	}

	public void setBuyerTransaction(Transaction buyerTransaction) {
		this.buyerTransaction = buyerTransaction;
	}


	public Transaction getSellerTransaction() {
		return sellerTransaction;
	}

	public void setSellerTransaction(Transaction sellerTransaction) {
		this.sellerTransaction = sellerTransaction;
	}
	
}