package com.erik.projeto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.erik.projeto.entities.Player;
public interface PlayerRepository extends JpaRepository<Player, Integer> {
	

	//Anotacao que diz para o Hibernate que esse metodo eh so de leitura, isso ajuda a melhorar a performance.
	@Transactional(readOnly = true)
	Player findById(int id);

}