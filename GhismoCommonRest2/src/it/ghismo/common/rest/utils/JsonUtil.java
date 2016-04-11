package it.ghismo.common.rest.utils;

import java.io.ByteArrayOutputStream;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import it.ghismo.common.rest.providers.BooleanJsonSerializer;
import it.ghismo.common.rest.providers.CustomCharacterEscapes;
import it.ghismo.common.rest.providers.StringJsonDeserializer;
import it.ghismo.common.rest.providers.StringJsonSerializer;


public class JsonUtil<T> {

    protected ObjectMapper mapper = null;
    protected SimpleModule customModule = null;
    protected Class<T> clazz = null;
	
	
	protected <A> void addAbstractMapping(Class<A> abstractClass, Class<? extends A> implementationClass) {
		customModule.addAbstractTypeMapping(abstractClass, implementationClass);
	}
	
	protected void addAbstractMappings() {}
	
	public ObjectMapper getMapper() {
		return mapper;
	}
	
    public JsonUtil(Class<T> c) {
		super();

		this.clazz = c;
		
        mapper = new ObjectMapper();
        AnnotationIntrospector primary = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
        AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
        AnnotationIntrospector pair = new AnnotationIntrospectorPair(primary, secondary);        
        mapper.setAnnotationIntrospector(pair);
        
    	mapper.setSerializationInclusion(Include.NON_EMPTY);

        mapper.getFactory().setCharacterEscapes(new CustomCharacterEscapes());
        
		customModule = new SimpleModule("customModule", new Version(1,0,0,null,null,null));
		customModule.addDeserializer(String.class, new StringJsonDeserializer());
		customModule.addSerializer(Boolean.class, new BooleanJsonSerializer());
		customModule.addSerializer(String.class, new StringJsonSerializer());

    	
    	addAbstractMappings();

    	mapper.registerModule(customModule);      

	}

    
    
	public T jsonToObject(String jsonString) {
    	T bean = null;
        try {
        	//JsonNode jsonNode = mapper.readTree(jsonString);
			bean = mapper.readValue(jsonString, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return bean;
    }

	public <A> A jsonToObject(String jsonString, Class<A> clazzA) {
    	A bean = null;
        try {
        	//JsonNode jsonNode = mapper.readTree(jsonString);
			bean = mapper.readValue(jsonString, clazzA);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return bean;
    }

	
    public String objectToJson(Object bean) {
    	String ris = null;
    	ByteArrayOutputStream os = new ByteArrayOutputStream();
    	try {
			mapper.writeValue(os, bean);
			ris = new String(os.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}    	
    	return ris;
    }
	
}
