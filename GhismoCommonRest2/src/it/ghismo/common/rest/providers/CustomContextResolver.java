package it.ghismo.common.rest.providers;


import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.ghismo.common.rest.utils.JsonUtil;




@Provider
@Produces("application/json")
public class CustomContextResolver implements ContextResolver<ObjectMapper> {

    private ObjectMapper mapper = null;
    @SuppressWarnings("rawtypes")
	private JsonUtil jsonUtil = null;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public CustomContextResolver() {
    	jsonUtil = new JsonUtil(null);
    	mapper = jsonUtil != null ? jsonUtil.getMapper() : new ObjectMapper();
    }
    
    
	@Override
	public ObjectMapper getContext(Class<?> type) {
	    return mapper;
	}

}