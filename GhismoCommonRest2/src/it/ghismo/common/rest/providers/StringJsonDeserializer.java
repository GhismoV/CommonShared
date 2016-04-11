package it.ghismo.common.rest.providers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import it.ghismo.common.utils.StringUtil;

public class StringJsonDeserializer extends JsonDeserializer<String> {

	@Override
	public String deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String ris = (jp.getText() != null) ? jp.getText().trim() : null;
		return StringUtil.unescapeUnicode(ris);
	}

}
