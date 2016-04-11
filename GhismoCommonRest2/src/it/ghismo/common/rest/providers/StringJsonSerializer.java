package it.ghismo.common.rest.providers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import it.ghismo.common.utils.StringUtil;

public class StringJsonSerializer extends JsonSerializer<String> {
	@Override
	public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
			JsonProcessingException {

		if(value != null) {
//			jgen.writeStartObject();
			String ris = (value != null) ? value.trim() : null;
			jgen.writeString(StringUtil.escapeUnicode(ris));
//		    jgen.writeEndObject();			
		}
		
	}
	  
	  
	  
	  
}
