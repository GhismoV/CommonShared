package it.ghismo.common.rest.providers;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;

// First, definition of what to escape
public class CustomCharacterEscapes extends CharacterEscapes {
	private static final long serialVersionUID = 5018635769741474908L;

	private final int[] asciiEscapes;
    
    public CustomCharacterEscapes()
    {
        // start with set of characters known to require escaping (double-quote, backslash etc)
        int[] esc = CharacterEscapes.standardAsciiEscapesForJSON();
        // and force escaping of a few others:
        
        esc['\\'] = CharacterEscapes.ESCAPE_NONE;
        esc['"'] = CharacterEscapes.ESCAPE_NONE;
        
        esc['<'] = CharacterEscapes.ESCAPE_STANDARD;
        esc['>'] = CharacterEscapes.ESCAPE_STANDARD;
        esc['&'] = CharacterEscapes.ESCAPE_STANDARD;
        esc['\''] = CharacterEscapes.ESCAPE_STANDARD;
        
        asciiEscapes = esc;
    }
    // this method gets called for character codes 0 - 127
    @Override public int[] getEscapeCodesForAscii() {
        return asciiEscapes;
    }
    // and this for others; we don't need anything special here
    @Override public SerializableString getEscapeSequence(int ch) {
    	/*
        if (ch == 'd') {
            return new SerializedString("[D]");
        }
        */
        // no further escaping (beyond ASCII chars) needed:
    	
        return null;
    }

}
