package br.com.test;

import java.util.List;
import org.junit.Test;
import br.com.xml.Compra;
import br.com.xml.Produto;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompraTest {

	@Test
	public void deveSerializarComprasComProdutos() {

		String resultadoEsperado = "<compra>\n" + "  <id>15</id>\n" + "  <produtos>\n"
				+ "    <produto codigo=\"1587\">\n" + "      <nome>geladeira</nome>\n" + "      <preco>1000.0</preco>\n"
				+ "      <descrição>geladeira duas portas</descrição>\n" + "      <categoria>simples</categoria>\n"
				+ "    </produto>\n" + "    <produto codigo=\"1588\">\n" + "      <nome>ferro de passar</nome>\n"
				+ "      <preco>100.0</preco>\n" + "      <descrição>ferro com vaporizador</descrição>\n"
				+ "      <categoria>simples</categoria>\n" + "    </produto>\n" + "  </produtos>\n" + "</compra>";

		Compra compra = compraGeladeiraEFerro();

		XStream xtream = xtreamCompraEProduto();

		String comprasXml = xtream.toXML(compra);

		assertEquals(resultadoEsperado, comprasXml);
	}

	private XStream xtreamCompraEProduto() {
		XStream xtream = new XStream();
		xtream.alias("produto", Produto.class);
		xtream.alias("compra", Compra.class);
		xtream.aliasField("descrição", Produto.class, "descricao");
		xtream.useAttributeFor(Produto.class, "codigo");
		return xtream;
	}

	@Test
	public void deveDesSerializarUmXmlGerandoComprasComProdutos() {

		String resultadoEsperado = "<compra>\n" + "  <id>15</id>\n" + "  <produtos>\n"
				+ "    <produto codigo=\"1587\">\n" + "      <nome>geladeira</nome>\n" + "      <preco>1000.0</preco>\n"
				+ "      <descrição>geladeira duas portas</descrição>\n" + "      <categoria>simples</categoria>\n"
				+ "    </produto>\n" + "    <produto codigo=\"1588\">\n" + "      <nome>ferro de passar</nome>\n"
				+ "      <preco>100.0</preco>\n" + "      <descrição>ferro com vaporizador</descrição>\n"
				+ "      <categoria>simples</categoria>\n" + "    </produto>\n" + "  </produtos>\n" + "</compra>";

		Compra compraOrigem = compraGeladeiraEFerro();

		XStream xtream = xtreamCompraEProduto();

		Compra compraDeserializada = (Compra) xtream.fromXML(resultadoEsperado);

		assertEquals(compraDeserializada, compraOrigem);

	}
	
	@Test
	public void deveSerializarDuasGeladeiras() {
		
		String resultadoEsperado = "<compra>\n" 
		        + "  <id>15</id>\n"
		        + "  <produtos>\n" 
		        + "    <produto codigo=\"1587\">\n"
		        + "      <nome>geladeira</nome>\n"
		        + "      <preco>1000.0</preco>\n"
		        + "      <descrição>geladeira duas portas</descrição>\n"
		        + "      <categoria>simples</categoria>\n"
		        + "    </produto>\n"
		        + "    <produto codigo=\"1587\">\n"
		        + "      <nome>geladeira</nome>\n"
		        + "      <preco>1000.0</preco>\n"
		        + "      <descrição>geladeira duas portas</descrição>\n"
		        + "      <categoria>simples</categoria>\n"
		        + "    </produto>\n"
		        + "  </produtos>\n" 
		        + "</compra>";
		
		Compra compra = compraDuasGeladeirasIguais();
		XStream xtream = xtreamCompraEProduto();
		xtream.setMode(XStream.NO_REFERENCES);
		String xmlGerado = xtream.toXML(compra);
		
		assertEquals(resultadoEsperado, xmlGerado);
		
	}

	private Compra compraGeladeiraEFerro() {
		Produto geladeira = geladeira();
		Produto ferro = ferro();

		List<Produto> produtos = new ArrayList<>();
		produtos.add(geladeira);
		produtos.add(ferro);

		Compra compra = new Compra(15, produtos);
		return compra;
	}

	private Produto ferro() {
		return new Produto("ferro de passar", 100.0, "ferro com vaporizador", "simples", 1588);
	}

	private Produto geladeira() {
		return new Produto("geladeira", 1000.0, "geladeira duas portas", "simples", 1587);
	}

	private Compra compraDuasGeladeirasIguais() {
		Produto geladeira = geladeira();
		List<Produto> produtos = new ArrayList<>();
		produtos.add(geladeira);
		produtos.add(geladeira);
		
		Compra compra = new Compra(15, produtos);
		
		return compra;

	}
}