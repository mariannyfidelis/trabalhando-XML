package br.com.test;

import java.util.List;
import org.junit.Test;
import br.com.xml.Compra;
import br.com.xml.Produto;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;

public class CompraTest {

	@Test
	public void deveSerializarComprasComProdutos() {
		
		String resultadoEsperado = "<compra>\n"+
	            "  <id>15</id>\n"+
	            "  <produtos>\n"+
	            "    <produto codigo=\"1587\">\n"+
	            "      <nome>geladeira</nome>\n"+
	            "      <preco>1000.0</preco>\n"+
	            "      <descrição>geladeira duas portas</descrição>\n"+
	            "      <categoria>simples</categoria>\n" +
	            "    </produto>\n"+
	            "    <produto codigo=\"1588\">\n"+
	            "      <nome>ferro de passar</nome>\n"+
	            "      <preco>100.0</preco>\n"+
	            "      <descrição>ferro com vaporizador</descrição>\n"+
	            "      <categoria>simples</categoria>\n" +
	            "    </produto>\n"+
	            "  </produtos>\n"+
	            "</compra>";
		
		Produto geladeira = new Produto("geladeira", 1000.0,"geladeira duas portas","simples", 1587);
		Produto ferro = new Produto("ferro de passar", 100.0,"ferro com vaporizador","simples", 1588);
		
		List<Produto> produtos = new ArrayList<>();
		produtos.add(geladeira);
		produtos.add(ferro);
		
		Compra compra = new Compra(15, produtos);
		
		XStream xtream = new XStream();
		xtream.alias("produto", Produto.class);
		xtream.alias("compra", Compra.class);
		xtream.aliasField("descrição", Produto.class, "descricao");
		xtream.useAttributeFor(Produto.class, "codigo");
		
		String comprasXml = xtream.toXML(compra);
		
		assertEquals(resultadoEsperado, comprasXml);	
	}
	
}
