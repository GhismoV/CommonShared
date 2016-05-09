package it.ghismo.common.rest.support.session;

import java.util.HashMap;
import java.util.Map;

public class DataTemp {
	public enum DataTempLevel {Session, LastFunction, LastFlow}

	private Map<String, Object> sessionDataTemp = new HashMap<String, Object>();
	private Map<String, Object> lastFunctionDataTemp = new HashMap<String, Object>();
	private Map<String, Object> lastFlowDataTemp = new HashMap<String, Object>();

	
	public final static String SESSION__GENERIC__LAST_RETURNED_JSON = "session.generic.last_returned.json";
	
	public DataTemp() {
	}

	
	public Map<String, Object> getLevelMap(DataTempLevel level) {
		Map<String, Object> ret = null;
		switch (level) {
		case Session:
			ret = sessionDataTemp;
			break;
		case LastFunction:
			ret = lastFunctionDataTemp;
			break;
		case LastFlow:
			ret = lastFlowDataTemp;
			break;
		default:
			break;
		}
		return ret;
	}
	public Object get(DataTempLevel level, String key) {
		Object ret = null;
		if(level != null && key != null) {
			Map<String, Object> map = getLevelMap(level);
			if(map != null) {
				ret = map.get(key);
			}
		}
		return ret;
	}
	public Object get(String key) {
		Object ret = null;
		if(key != null) {
			for(DataTempLevel level : DataTempLevel.values()) {
				ret = get(level, key);
				if(ret != null) break;
			}
		}
		return ret;
	}
	public void put(DataTempLevel level, String key, Object value) {
		if(level != null && key != null) {
			Map<String, Object> map = getLevelMap(level);
			if(map != null) {
				map.put(key, value);
			}
		}
	}
	public Object remove(DataTempLevel level, String key) {
		Object ret = null;
		if(level != null && key != null) {
			Map<String, Object> map = getLevelMap(level);
			if(map != null) {
				ret = map.remove(key);
			}
		}
		return ret;
	}
	public Object remove(String key) {
		Object ret = null;
		if(key != null) {
			for(DataTempLevel level : DataTempLevel.values()) {
				ret = remove(level, key);
				if(ret != null) break;
			}
		}
		return ret;
	}
	public void clear(DataTempLevel level) {
		if(level != null) {
			Map<String, Object> map = getLevelMap(level);
			if(map != null) {
				map.clear();
			}
		}
	}
	public void clearMulti(DataTempLevel... levels) {
		if(levels != null) {
			for (DataTempLevel level : levels) {
				clear(level);
			}
		}
	}	
}
