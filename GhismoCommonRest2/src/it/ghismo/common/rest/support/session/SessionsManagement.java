package it.ghismo.common.rest.support.session;

import java.util.Hashtable;
import java.util.Map;

import it.ghismo.common.rest.resources.CommonRsc;
import it.ghismo.common.utils.generators.SessionIdGenerator;

public class SessionsManagement {
	public enum VerifySessionStatus {NoSessions, SessionStatusError, SessionUnknown, SessionViolation, SessionDisabled, SessionOkUncomplete, SessionOk};

	private Map<String, UserSession> sessions = new Hashtable<String, UserSession>();
	private Map<String, UserSession> removedSessions = new Hashtable<String, UserSession>();

	public SessionsManagement() {
	}

	
	public synchronized String getNewSessionId() {
		String ret = null;
		SessionIdGenerator.setSessionIdLength(32);
		do {
			ret = SessionIdGenerator.generateSessionId();
		} while(isInSessions(ret));

		return ret;
	}

	public long getNumberOfSessions() {
		if(sessions != null) {
			return sessions.size();
		}
		return 0;
	}

	private UserSession getSessionInfo(String sessionId) {
		if(sessions != null && sessionId != null) {
			return sessions.get(sessionId);
		}
		return null;
	}
	public boolean removeSessionInfo(String sessionId) {
		if(sessions != null && sessionId != null) {
			UserSession us = sessions.remove(sessionId);
			if(us != null) {
				us.disattiva();
				// metti le info nelle sessioni rimosse
				addInRemovedSessions(us);
			}
			return us != null;
		}
		return false;
	}
	public boolean isInSessions(String sessionId) {
		boolean ris = false;
		if(sessions != null && sessionId != null) {
			return (sessions.get(sessionId) != null);
		}
		return ris;
	}
	public boolean addInSessions(UserSession us) {
		boolean ris = false;
		if(sessions != null && us != null) {
			us.setAttiva(true);
			sessions.put(us.getId(), us);
			ris = true;
		}
		return ris;
	}

	private UserSession getRemovedSessionInfo(String sessionId) {
		if(removedSessions != null && sessionId != null) {
			return removedSessions.get(sessionId);
		}
		return null;
	}
	private boolean addInRemovedSessions(UserSession rus) {
		boolean ris = false;
		if(removedSessions != null && rus != null) {
			removedSessions.put(rus.getId(), rus);
			ris = true;
		}
		return ris;
	}

	public UserSession getSession(String sessionId) {
		UserSession ris = getSessionInfo(sessionId);
		if(ris == null) {
			ris = getRemovedSessionInfo(sessionId);
		}
		return ris;
	}
	public boolean isSessionOk(UserSession se, String mac, CommonRsc resource) {
		return isSessionOk(se, mac, false, resource);
	}
	public boolean isSessionOk(UserSession us, String mac, boolean accettaAccessiIncompleti, CommonRsc resource) {
		//TODO da gestire resource per bloccare richieste a risorse le cui funzionalita' non sono permesse all'utente

		VerifySessionStatus status = verifySession(us);
		return (status == VerifySessionStatus.SessionOk) || ( accettaAccessiIncompleti && status == VerifySessionStatus.SessionOkUncomplete );
	}
	public VerifySessionStatus verifySession(UserSession se) {
		VerifySessionStatus ris;
		if(sessions != null && se != null) {
			UserSession tmpSe = sessions.get(se.getId());
			if(tmpSe != null) {
				if(tmpSe == se) {
					if(se.isAttiva()) {
						if(se.isAccessoCompleto()) {
							ris = VerifySessionStatus.SessionOk;
						} else {
							ris = VerifySessionStatus.SessionOkUncomplete;
						}
					} else {
						ris = VerifySessionStatus.SessionStatusError;
					}
				} else {
					ris = VerifySessionStatus.SessionViolation;
				}
			} else {
				UserSession tmpRemSe = removedSessions.get(se.getId());
				if(tmpRemSe != null) {
					if(tmpRemSe == se) {
						if(se.isAttiva()) {
							ris = VerifySessionStatus.SessionStatusError;
						} else {
							ris = VerifySessionStatus.SessionDisabled;
						}
					} else {
						ris = VerifySessionStatus.SessionViolation;
					}
				} else {
					ris = VerifySessionStatus.SessionUnknown;
				}
			}
		} else {
			ris = VerifySessionStatus.NoSessions;
		}
		return ris;
	}

}
