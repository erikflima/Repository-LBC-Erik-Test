package com.erik.projeto.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erik.projeto.entities.Item;
import com.erik.projeto.enums.StatusOfItem;
import com.erik.projeto.repositories.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	
	public ItemService() {
	}

	//--------------------------------------------------//
	
	public void saveItem( Item item) {

		itemRepository.save(item);
	}
	
	//---
	
	public List<Item> getAllItemsForSale() {

		List<Item> listOfItemForSale = itemRepository.getAllItemsForSale( StatusOfItem.FOR_SALE.toString() );
		
		return listOfItemForSale;
	}	

	//---
	
	public Item getItemById(int id){
		

		Optional<Item> itemOpcional = itemRepository.findById(id);
		
		Item item = itemOpcional.get();
		
		return item;
	}

}