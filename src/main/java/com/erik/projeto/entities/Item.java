package com.erik.projeto.entities;
import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "item")
public class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String statusOfItem;

	//bi-directional many-to-one association to Player
	@JsonIgnore
	@ManyToOne (fetch=FetchType.LAZY)
	private Player player;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="item")
	private List<Transaction> transactions;
	
	
	public Item() {
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

	
	public String getStatusOfItem() {
		return this.statusOfItem;
	}

	public void setStatusOfItem(String statusOfItem) {
		this.statusOfItem = statusOfItem;
	}

	
	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setItem(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setItem(null);

		return transaction;
	}

}