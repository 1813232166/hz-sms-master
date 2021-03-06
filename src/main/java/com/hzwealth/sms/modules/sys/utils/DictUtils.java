/**
 * 
 */
package com.hzwealth.sms.modules.sys.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hzwealth.sms.common.mapper.JsonMapper;
import com.hzwealth.sms.common.utils.CacheUtils;
import com.hzwealth.sms.common.utils.SpringContextHolder;
import com.hzwealth.sms.modules.sys.dao.DictDao;
import com.hzwealth.sms.modules.sys.entity.Dict;

/**
 * 字典工具类
 * @author Administrator
 * @version 2013-5-29
 */
public class DictUtils {
	
	private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);    

	public static final String CACHE_DICT_MAP = "dictMap";
	
	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static List<Dict> getDictList(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_DICT_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (Dict dict : dictDao.findAllList(new Dict())){
				List<Dict> dictList = dictMap.get(dict.getType());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		List<Dict> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
	public static List<Dict> getDictListValue(String type,String values){
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = null;

		String[] value = values.split(",");
		Dict dict = new Dict();
		dict.setType(type);
		dict.setValueList(value);
		List<Dict> dictList = dictDao.findByType(dict);	
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		Boolean isV=false;
		for(int i=0;i<dictList.size();i++){	
			Dict d=dictList.get(i);
			for(String v:value){
				if(v!=null){
					if(v.equals(d.getValue())){isV=true;}					
				}
			}	
			if(isV){dictList.remove(i);}
		}
		return dictList;
	}

	
	
	/**
	 * 返回字典列表（JSON）
	 * @param type
	 * @return
	 */
	public static String getDictListJson(String type){
		return JsonMapper.toJsonString(getDictList(type));
	}
	
}
