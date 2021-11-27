package com.erik.projeto.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erik.projeto.entities.Player;
import com.erik.projeto.repositories.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	
	
	public PlayerService() {
	}

	//-----------------------------------------------//
	
	public Player findById( int id ){
		
		return playerRepository.findById( id );

	}

}