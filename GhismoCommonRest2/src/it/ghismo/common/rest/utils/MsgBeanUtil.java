package it.ghismo.common.rest.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.ghismo.common.rest.jaxb.msg.BeanType;
import it.ghismo.common.rest.jaxb.msg.DataFieldsType;
import it.ghismo.common.rest.jaxb.msg.FieldType;
import it.ghismo.common.utils.StringUtil;

public class MsgBeanUtil {
	public enum MsgBeanUtilCachingMode{Complete, Progressive, NoCache} 
	public enum MsgBeanUtilOperation{Get, Remove} 

	private BeanType bean = null;
	private MsgBeanUtilCachingMode cachingMode = MsgBeanUtilCachingMode.Complete;
	
	private Map<String, FieldType> fieldsMap = null;
	
	
	public MsgBeanUtil(List<FieldType> fields, MsgBeanUtilCachingMode cachingMode) {
		this.bean = new BeanType();
		DataFieldsType dft = new DataFieldsType();
		this.bean.setDataFields(dft);
		dft.getFields().addAll(fields);
		construct(bean, cachingMode);
	}
	
	public MsgBeanUtil(BeanType bean, MsgBeanUtilCachingMode cachingMode) {
		construct(bean, cachingMode);
	}
	
	private void construct(BeanType bean, MsgBeanUtilCachingMode cachingMode) {
		this.bean = bean;
		this.cachingMode = cachingMode != null ? cachingMode : MsgBeanUtilCachingMode.Complete;
		if(this.cachingMode != MsgBeanUtilCachingMode.NoCache) {
			if(this.cachingMode == MsgBeanUtilCachingMode.Complete) {
				cacheAll(true);
			}
		}
	}
	
	private void cacheAll(boolean clear) {
		// inizilizzazione mappa
		if(this.fieldsMap == null) {
			this.fieldsMap = new HashMap<String, FieldType>();
		} else if(clear) {
			this.fieldsMap.clear();
		} else ;
		
		// caching
		if(this.bean != null && this.bean.getDataFields() != null) {
			List<FieldType> fields = this.bean.getDataFields().getFields();
			if(fields != null) {
				for (FieldType field : fields) {
					this.fieldsMap.put(field.getId(), field);
				}
			}
		}
	}
	
	
	public FieldType getField(String fieldId) {
		return getField(fieldId, false);
	}
	public FieldType getField(String fieldId, boolean iterateIfNotFound) {
		return opField(MsgBeanUtilOperation.Get, fieldId, iterateIfNotFound);
	}
	public FieldType removeField(String fieldId) {
		return opField(MsgBeanUtilOperation.Remove, fieldId, true);
	}
	private FieldType opField(MsgBeanUtilOperation op, String fieldId, boolean iterateIfNotFound) {
		FieldType ris = null;
		if(this.bean != null && !StringUtil.isStringEmpty(fieldId)) {
			if(this.cachingMode != MsgBeanUtilCachingMode.NoCache && fieldsMap != null) {
				ris = (op == MsgBeanUtilOperation.Remove) ?  fieldsMap.remove(fieldId) : fieldsMap.get(fieldId);
			}
			if(ris == null || op == MsgBeanUtilOperation.Remove) {
				if(
					op == MsgBeanUtilOperation.Remove
					||
					this.cachingMode == MsgBeanUtilCachingMode.NoCache
					||
					(
						this.cachingMode == MsgBeanUtilCachingMode.Progressive 
						|| 
						(this.cachingMode == MsgBeanUtilCachingMode.Complete && iterateIfNotFound) 
					)
				) {
					if(this.bean.getDataFields() != null) {
						List<FieldType> fields = this.bean.getDataFields().getFields();
						if(fields != null) {
							int size = fields.size();
							int i = 0;
							FieldType field = null;
							while(i < size) {
								field = fields.get(i);
								if(op == MsgBeanUtilOperation.Get && this.cachingMode == MsgBeanUtilCachingMode.Progressive && fieldsMap != null && fieldsMap.get(field.getId()) == null) {
									fieldsMap.put(field.getId(), field);
								}
								if(fieldId.equals(field.getId())) {
									ris = field;
									if(op == MsgBeanUtilOperation.Remove) fields.remove(i);
									break;
								} else i++;
							}
						}
					}
				}
			}
		}
		return ris;
	}

}
