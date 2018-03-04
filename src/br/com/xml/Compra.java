package br.com.xml;

import java.util.List;

public class Compra {

	private int id;
	private List<Produto> produtos;
	
	public Compra(int id, List<Produto> produtos) {
		this.id = id;
		this.produtos = produtos;
	}
	
	
}
