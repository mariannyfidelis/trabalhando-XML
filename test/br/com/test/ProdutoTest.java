package br.com.test;

import org.junit.Test;

import br.com.xml.PrecoConverter;
import br.com.xml.Produto;
import br.com.xml.SingleConverter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.SingleValueConverter;

import static org.junit.Assert.assertEquals;

public class ProdutoTest{

	@Test
	public void deveGerarOxmlComNomeDescricaoPesoAdequado() {
		
		String resultadoEsperado = "<produto codigo=\"1587\">\n" +
		        "  <nome>geladeira</nome>\n" +
		        "  <preco>R$ 1.000,00</preco>\n" +
		        "  <descrição>geladeira duas portas</descrição>\n" +
		        "  <categoria>simples</categoria>\n" +
		        "</produto>";
		
		Produto geladeira = new Produto("geladeira", 1000.0,"geladeira duas portas","simples", 1587);
		
		XStream xstream = new XStream();
		xstream.alias("produto", Produto.class);
		xstream.aliasField("descrição", Produto.class, "descricao");
		xstream.useAttributeFor(Produto.class, "codigo"); 
		//xstream.registerLocalConverter(Produto.class, "preco", new PrecoConverter());
		xstream.registerLocalConverter(Produto.class, "preco", new SingleConverter());
		
		String xmlGerado = xstream.toXML(geladeira);
		
		System.out.println("RESULTADO ESPERADO");
		System.out.print(resultadoEsperado);
		
		System.out.println("\nXML GERADO");
		System.out.print(xmlGerado);
		
		assertEquals(resultadoEsperado, xmlGerado);
	}
}
