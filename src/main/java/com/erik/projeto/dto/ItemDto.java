package com.erik.projeto.dto;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public class ItemDto {

	@NotEmpty(message = "The field 'name' cannot be empty.")
	@Length(min = 3, max = 200, message = "Name must contain between 3 and 200 characters")
	private String name;

	@Positive  (message = "The field 'playerId' must be positive value.")
	private int playerId;
	
	public ItemDto() {
	}

	//-------------------------Getters and Setters----------------------//	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

}