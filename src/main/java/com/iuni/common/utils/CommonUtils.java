package com.iuni.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;

/**
 * CommonUtils
 * @author CaiKe
 * @version gionee-common-1.0.0
 */
public final class CommonUtils {
   
    
	@SuppressWarnings("rawtypes")
	public static Collection collectByPropertyName(Collection collection, String propertyName) {
        return CollectionUtils.collect(collection, new BeanToPropertyValueTransformer(propertyName));
    }
    
    public static String map2String(Map<String, String> map){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> m : map.entrySet()) {
            sb.append(m.getValue());
        }
        return sb.toString();
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<String> CollectionToStringArray(Collection collection,String propertyName){
    	String[] iDArray = new String[0];
    	if (collection!=null && collection.size()>0) {
    		Collection<String> IdCoction = ((Collection<String>)collectByPropertyName(collection, propertyName));
    		iDArray = (IdCoction!=null && IdCoction.size()>0 )? IdCoction.toArray(new String[IdCoction.size()]):new String[0];	
		}
    	return filterRepeatItemString(Arrays.asList(iDArray));
    }
    
    /**
     * @Description: 返回一个List<String>,去除参数列表中的重复字符串 
     * @param  List<String> iDArray
     * @return List<String>
     */
    public static List<String> filterRepeatItemString(List<String> iDArray) {
		List<String> iDArrayRetn = new ArrayList<String>(0);
		if (iDArray != null && iDArray.size() > 0) {
			iDArrayRetn = new ArrayList<String>(iDArray.size());
			for (String iDITEM : iDArray) {
				if (iDArrayRetn.contains(iDITEM)) {
					continue;
				} else {
					iDArrayRetn.add(iDITEM);
				}
			}
		}
		return iDArrayRetn;
	}
    
}
