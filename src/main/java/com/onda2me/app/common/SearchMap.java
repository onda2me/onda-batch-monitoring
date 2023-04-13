package com.onda2me.app.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.ibatis.type.Alias;

import com.onda2me.app.util.PageUtil;


/**
 * Mybatis DB 자동맵핑을 위한 클래스
 * 
 * 
 * @author Administrator
 * 
 * 
 * 예시 ::
	    <select id="listWeatherDay"  parameterType="com.onda2me.app.common.SearchMap" resultType="SearchMap">
			select inputYmd, inputHour, count(weatherCode) as count
			from  t_weather
			where inputYmd = #{inputYmd} 
			group by inputYmd, inputHour	
	    </select>
   		
 *
 */
@Alias("searchMap")
public class SearchMap extends HashMap<String, Object> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 603335565444529367L;

    public SearchMap() {
        super();
    }
    
    /**
     * @param initialCapacity
     */
    public SearchMap(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * @param initialCapacity
     * @param loadFactor
     */
    public SearchMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    /**
     * @param map
     */
    public SearchMap(HashMap<String, Object> map) {
        super(map);
    }
    
    public void initParam(String key, Object value) {
        
        if(super.get(key) == null)
            super.put(key, value);
    }
    
    
/*
    public boolean containsKey(String key) {
        return super.containsKey(key.toLowerCase());
    }



    public Object get(String key) {
        return super.get(key.toLowerCase());
    }


    public Object put(String key, Object value) {
        return super.put(key.toLowerCase(), value);
    }
*/
    public Double getDouble(String key) {
    	
		double d_value = 0.0;
		Object value = super.get(key);
		
		if (value == null || "".equals(value.toString())) 
			return 0.0;
				
		try {
			d_value = Double.parseDouble(value.toString());
		} catch (Exception e) {
			
		}
        return d_value;
    }

    public int getInt(String key) {
        
    	int i_value = 0;
		Object value = super.get(key);
		
		if (value == null || "".equals(value.toString())) 
			return 0;
				
		try {
		    
			i_value = Integer.parseInt(value.toString());
		} catch (Exception e) {
			
		}
        return i_value;
    }
    
    public String getString(String key) {
        Object obj = super.get(key);
        
        if(obj == null)
            return "";
  
        return obj.toString();
    }
    
    public void setPgtl(PageUtil pgtl) {
        
        put("pgtl", pgtl);
    }
    
    public PageUtil getPgtl() {
        
        return (PageUtil)super.get("pgtl");
    }
       
/*
    public Object putDouble(String key, Double value) {
        return super.put(key, value);
    }
    
*/    
    public void putAll(HashMap<String, Object> map) {
        Iterator<String> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            Object value = map.get(key);
            this.put(key, value);
        }
    }
    
    public String getParams() {
        StringBuilder params = new StringBuilder();        
        Iterator<String> iter = this.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            
            if(!"currPage".equals(key) && !"_csrf".equals(key)) {
                Object value = this.get(key);                
                params.append(key).append("=").append(value).append("&");
            }
        }
        return params.toString();
    }    

/*
    public Object remove(String key) {
        return super.remove(key.toLowerCase());
    }
    
*/    
}
