package it.ghismo.common.rest.providers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class BooleanJsonSerializer extends JsonSerializer<Boolean> {
	@Override
	public void serialize(Boolean value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
			JsonProcessingException {

		if(value != null) {
//			jgen.writeStartObject();
			jgen.writeString(value.booleanValue() ? "true" : "false");
//		    jgen.writeEndObject();			
		}
		
	}
	  
	  
	  
	  
}
