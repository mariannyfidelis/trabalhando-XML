package br.com.xml;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.SingleValueConverter;

public class SingleConverter implements SingleValueConverter {

	@Override
	public boolean canConvert(Class type) {
		return type.isAssignableFrom(Double.class);
	}

	public String toString(Object value) {
	    Double valor = (Double) value;
	    Locale brasil = new Locale("pt","br");
	    NumberFormat formatador = NumberFormat.getCurrencyInstance(brasil);
	    String valorEmReais = formatador.format(valor);
	    return valorEmReais;
	}

	@Override
	public Object fromString(String str) {
		Locale brasil = new Locale("pt", "br");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(brasil);
		
		try {
			return formatter.parse(str);
		} catch (ParseException e) {
			throw new ConversionException("erro na conversao ... ", e);
		}
	}

}
