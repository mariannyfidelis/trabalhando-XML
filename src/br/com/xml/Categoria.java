package br.com.xml;

public class Categoria {

	private Categoria pai;
	private String nome;

	public Categoria(Categoria pai, String nome) {
		this.pai = pai;
		this.nome = nome;
	}
	
	public void setPai(Categoria pai) {
        this.pai = pai;
    }
}
