package br.com.test;

import java.util.List;
import org.junit.Test;
import br.com.xml.Compra;
import br.com.xml.CompraDiferenteConverter;
import br.com.xml.Livro;
import br.com.xml.Musica;
import br.com.xml.Produto;
import java.util.ArrayList;
import java.util.LinkedList;

import com.thoughtworks.xstream.XStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompraTest {

	@Test
	public void deveSerializarComprasComProdutos() {

		String resultadoEsperado = "<compra>\n"
		+		"  <id>15</id>\n" 
				+ "  <produtos>\n"
				+ "    <produto codigo=\"1587\">\n"
				+ "      <nome>geladeira</nome>\n" 
				+ "      <preco>1000.0</preco>\n"
				+ "      <descrição>geladeira duas portas</descrição>\n" 
				+ "      <categoria>simples</categoria>\n"
				+ "    </produto>\n" 
				+ "    <produto codigo=\"1588\">\n" 
				+ "      <nome>ferro de passar</nome>\n"
				+ "      <preco>100.0</preco>\n" 
				+ "      <descrição>ferro com vaporizador</descrição>\n"
				+ "      <categoria>simples</categoria>\n" 
				+ "    </produto>\n" 
				+ "  </produtos>\n" 
				+ "</compra>";

		Compra compra = compraGeladeiraEFerro();

		XStream xstream = xtreamCompraEProduto();

		String comprasXml = xstream.toXML(compra);

		assertEquals(resultadoEsperado, comprasXml);
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
	public void deveSerializarDuasGeladeirasIguais() {

		String resultadoEsperado = "<compra>\n" + "  <id>15</id>\n" + "  <produtos>\n"
				+ "    <produto codigo=\"1587\">\n" + "      <nome>geladeira</nome>\n" + "      <preco>1000.0</preco>\n"
				+ "      <descrição>geladeira duas portas</descrição>\n" + "      <categoria>simples</categoria>\n"
				+ "    </produto>\n" + "    <produto codigo=\"1587\">\n" + "      <nome>geladeira</nome>\n"
				+ "      <preco>1000.0</preco>\n" + "      <descrição>geladeira duas portas</descrição>\n"
				+ "      <categoria>simples</categoria>\n" + "    </produto>\n" + "  </produtos>\n" + "</compra>";

		Compra compra = compraDuasGeladeirasIguais();
		XStream xstream = xtreamCompraEProduto();
		xstream.setMode(XStream.NO_REFERENCES);
		String xmlGerado = xstream.toXML(compra);

		assertEquals(resultadoEsperado, xmlGerado);
	}

	@Test
	public void deveSerializarComColecoesImplicitas() {

		String resultadoEsperado = "<compra>\n" + "  <id>15</id>\n" + "  <produto codigo=\"1587\">\n"
				+ "    <nome>geladeira</nome>\n" + "    <preco>1000.0</preco>\n"
				+ "    <descrição>geladeira duas portas</descrição>\n" + "    <categoria>simples</categoria>\n"
				+ "  </produto>\n" + "  <produto codigo=\"1587\">\n" + "    <nome>geladeira</nome>\n"
				+ "    <preco>1000.0</preco>\n" + "    <descrição>geladeira duas portas</descrição>\n"
				+ "    <categoria>simples</categoria>\n" + "  </produto>\n" + "</compra>";

		Compra compra = compraDuasGeladeirasIguais();
		XStream xstream = xtreamCompraEProduto();
		xstream.setMode(XStream.NO_REFERENCES);
		xstream.addImplicitCollection(Compra.class, "produtos");
		String xmlGerado = xstream.toXML(compra);

		assertEquals(resultadoEsperado, xmlGerado);
	}

	@Test
	public void testCompraLivroEmusica() {

		String expected = "<compra>\n" + "  <id>15</id>\n" + "  <produtos class=\"linked-list\">\n"
				+ "    <livro codigo=\"1589\">\n" + "      <nome>O Pássaro Raro</nome>\n"
				+ "      <preco>100.0</preco>\n" + "      <descrição>dez histórias sobre a existência</descrição>\n"
				+ "      <categoria>simples</categoria>\n" + "    </livro>\n" + "    <musica codigo=\"1590\">\n"
				+ "      <nome>Meu Passeio</nome>\n" + "      <preco>100.0</preco>\n"
				+ "      <descrição>música livre</descrição>\n" + "      <categoria>simples</categoria>\n"
				+ "    </musica>\n" + "  </produtos>\n" + "</compra>";

		Compra compra = compraLivroeMusica();
		XStream xstream = xstreamLivroMusica();

		String xmlgerado = xstream.toXML(compra);
		assertEquals(expected, xmlgerado);
	}
	
	@Test
	public void deveUsarUmConversorDiferente() {
	
		String xmlEsperado ="<compra estilo=\"novo\">\n" + 
				"  <id>15</id>\n" + 
				"  <fornecedor>mariannyfidelis@gmail.com</fornecedor>\n" + 
				"  <endereco>\n" + 
				"    <linha1>Rua Vergueiro 3185</linha1>\n" + 
				"    <linha2>8 andar - Sao Paulo - SP</linha2>\n" + 
				"  </endereco>\n" + 
				"  <produtos>\n" + 
				"    <livro codigo=\"1589\">\n" + 
				"      <nome>O Pássaro Raro</nome>\n" + 
				"      <preco>100.0</preco>\n" + 
				"      <descrição>dez histórias sobre a existência</descrição>\n" +
				"      <categoria>simples</categoria>\n"+
				"    </livro>\n" + 
				"    <musica codigo=\"1590\">\n" + 
				"      <nome>Meu Passeio</nome>\n" + 
				"      <preco>100.0</preco>\n" + 
				"      <descrição>música livre</descrição>\n" +
				"      <categoria>simples</categoria>\n"+
				"    </musica>\n" + 
				"  </produtos>\n" + 
				"</compra>";
		
		Compra compra = compraLivroeMusica();
		XStream xstream = xtreamCompraEProduto();
		
		xstream.registerConverter(new CompraDiferenteConverter());
	
		String xmlGerado = xstream.toXML(compra);

		assertEquals(xmlEsperado, xmlGerado);
		
		Compra deserializada = (Compra) xstream.fromXML(xmlGerado);
		assertEquals(compra, deserializada);
		
	}
	

	private XStream xtreamCompraEProduto() {
		XStream xstream = new XStream();
		xstream.alias("produto", Produto.class);
		xstream.alias("compra", Compra.class);
		xstream.alias("livro", Livro.class);
		xstream.alias("musica", Musica.class);
		xstream.aliasField("descrição", Produto.class, "descricao");
		xstream.useAttributeFor(Produto.class, "codigo");
		return xstream;
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

	private XStream xstreamLivroMusica() {

		XStream xstream = new XStream();
		xstream.alias("produto", Produto.class);
		xstream.alias("compra", Compra.class);
		xstream.alias("livro", Livro.class);
		xstream.alias("musica", Musica.class);
		xstream.useAttributeFor(Produto.class, "codigo");
		xstream.aliasField("descrição", Produto.class, "descricao");

		return xstream;
	}

	private Compra compraLivroeMusica() {

		Livro livro = new Livro("O Pássaro Raro", 100.0, "dez histórias sobre a existência", "simples", 1589);
		Musica musica = new Musica("Meu Passeio", 100.0, "música livre", "simples", 1590);
		List<Produto> produtos = new LinkedList<>();
		produtos.add(livro);
		produtos.add(musica);

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