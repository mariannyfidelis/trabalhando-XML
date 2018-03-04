package br.com.xml;

public class Produto {

	public String nome;
	public double preco;
	public String descricao;
	public String categoria;
	private int   codigo;
	
	public Produto(String nome, double preco, String descricao, String categoria, int codigo) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.categoria = categoria;
		this.codigo = codigo;
	}
	
	
}
