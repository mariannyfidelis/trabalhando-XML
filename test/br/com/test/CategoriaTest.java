package br.com.test;

import org.junit.Test;
import br.com.xml.Categoria;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.TreeMarshaller.CircularReferenceException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoriaTest {

	@Test
	public void testaReferenciaCircular() {
		
		String resultadoEsperado = "<categoria id=\"1\">\n" + 
				"  <pai id=\"2\">\n" + 
				"    <pai id=\"3\">\n" + 
				"      <pai reference=\"1\"/>\n" + 
				"      <nome>futebol</nome>\n" + 
				"    </pai>\n" + 
				"    <nome>geral</nome>\n" + 
				"  </pai>\n" + 
				"  <nome>esporte</nome>\n" + 
				"</categoria>";
		
		XStream xstream = new XStream();
		xstream.alias("categoria", Categoria.class);
		xstream.setMode(XStream.ID_REFERENCES);
		
		Categoria esporte = new Categoria(null, "esporte");
		Categoria futebol = new Categoria(esporte, "futebol");
		Categoria geral = new Categoria(futebol, "geral");
		esporte.setPai(geral); // fechou o ciclo
		
		assertEquals(resultadoEsperado, xstream.toXML(esporte));
	}
	
	@Test(expected=CircularReferenceException.class)
	public void testeReferenciaCrcularComNO_REFERENCES() {
		
		String resultadoEsperado = "<categoria id=\"1\">\n" + 
				"  <pai id=\"2\">\n" + 
				"    <pai id=\"3\">\n" + 
				"      <pai reference=\"1\"/>\n" + 
				"      <nome>futebol</nome>\n" + 
				"    </pai>\n" + 
				"    <nome>geral</nome>\n" + 
				"  </pai>\n" + 
				"  <nome>esporte</nome>\n" + 
				"</categoria>";
		
		XStream xstream = new XStream();
		xstream.alias("categoria", Categoria.class);
		xstream.setMode(XStream.NO_REFERENCES);
		
		Categoria esporte = new Categoria(null, "esporte");
		Categoria futebol = new Categoria(esporte, "futebol");
		Categoria geral = new Categoria(futebol, "geral");
		esporte.setPai(geral); // fechou o ciclo
		
		assertEquals(resultadoEsperado, xstream.toXML(esporte));
	}
}
