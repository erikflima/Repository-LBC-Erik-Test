package com.erik.projeto.dto;
import javax.validation.constraints.Positive;

public class BuyAnItemDto {

	
	@Positive  (message = "The field 'playerId_buyer' must be positive value.")
	private int playerId_buyer;
	
	@Positive  (message = "The field 'playerId_seller' must be positive value.")
	private int playerId_seller;

	@Positive  (message = "The field 'itemId' must be positive value.")
	private int itemId;	
	
	BuyAnItemDto(){
	}

	//-------------------------Getters and Setters----------------------//
	
	public int getPlayerId_buyer() {
		return playerId_buyer;
	}

	public void setPlayerId_buyer(int playerId_buyer) {
		this.playerId_buyer = playerId_buyer;
	}


	public int getPlayerId_seller() {
		return playerId_seller;
	}

	public void setPlayerId_seller(int playerId_seller) {
		this.playerId_seller = playerId_seller;
	}

	
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

}