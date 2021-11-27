package com.erik.projeto.entities;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "player")
public class Player implements Serializable {
	
	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="player")
	private List<Item> items;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="player")
	private List<Transaction> transactions;

	
	public Player() {
	}

	//-------Getters and Setters-------//
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setPlayer(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setPlayer(null);

		return item;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setPlayer(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setPlayer(null);

		return transaction;
	}

}