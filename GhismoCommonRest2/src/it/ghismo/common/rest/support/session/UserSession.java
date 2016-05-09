package it.ghismo.common.rest.support.session;

import it.ghismo.common.rest.support.Language;

public class UserSession {
	
	private String id = null;
	private boolean isProfiloTemporaneo = false;
	private boolean isAttiva = false;
	private boolean isAccessoCompleto = false;

	private Language lang = null;
	private DataTemp dataTemp = new DataTemp();
	
	public UserSession() {
	}

	
	/****************** servizi complessi *********************/
	public void disattiva() {
		isAttiva = false;
		isAccessoCompleto = false;
	}
	/**********************************************************/


	public DataTemp getDataTemp() { return dataTemp; }
	

	/**************** getters and Setters *********************/
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public boolean isProfiloTemporaneo() {
		return isProfiloTemporaneo;
	}
	public void setProfiloTemporaneo(boolean isProfiloTemporaneo) {
		this.isProfiloTemporaneo = isProfiloTemporaneo;
	}

	public boolean isAttiva() {
		return isAttiva;
	}
	public void setAttiva(boolean isAttiva) {
		this.isAttiva = isAttiva;
	}

	public boolean isAccessoCompleto() {
		return isAccessoCompleto;
	}
	public void setAccessoCompleto(boolean isAccessoCompleto) {
		this.isAccessoCompleto = isAccessoCompleto;
	}

	public Language getLang() {
		return lang;
	}
	public void setLang(Language lang) {
		this.lang = lang;
	}
	
	/**********************************************************/

	
	
	

}
