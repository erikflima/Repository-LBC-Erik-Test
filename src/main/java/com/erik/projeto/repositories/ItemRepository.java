package com.erik.projeto.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erik.projeto.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
   @Query(value="select * from item    i where i.status_of_item = :Status", nativeQuery=true)
   public List<Item> getAllItemsForSale( String Status );
      
}