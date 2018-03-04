package br.com.test;

import org.junit.Test;
import br.com.xml.Produto;
import com.thoughtworks.xstream.XStream;
import static org.junit.Assert.assertEquals;

public class ProdutoTest{

	@Test
	public void deveGerarOxmlComNomeDescricaoPesoAdequado() {
		
		String resultadoEsperado = "<produto codigo=\"1587\">\n" +
		        "  <nome>geladeira</nome>\n" +
		        "  <preco>1000.0</preco>\n" +
		        "  <descrição>geladeira duas portas</descrição>\n" +
		        "  <categoria>simples</categoria>\n" +
		        "</produto>";
		
		Produto geladeira = new Produto("geladeira", 1000.0,"geladeira duas portas","simples", 1587);
		
		//Criando XStream
		XStream xtream = new XStream();
		
		//Renomeando a tag Produto e a tag Descrição
		xtream.alias("produto", Produto.class);
		xtream.aliasField("descrição", Produto.class, "descricao");
		xtream.useAttributeFor(Produto.class, "codigo");  //definindo um atributo
		
		String xmlGerado = xtream.toXML(geladeira);
		
		System.out.println("RESULTADO ESPERADO");
		System.out.print(resultadoEsperado);
		
		System.out.println("\nXML GERADO");
		System.out.print(xmlGerado);
		
		assertEquals(resultadoEsperado, xmlGerado);
		
	}
	
}
