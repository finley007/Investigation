package stock.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class JsonHelper {
	
	private static Map toMap(Object javaBean) {
        Map result = new HashMap();
        for(Class<?> clazz = javaBean.getClass() ; clazz != Object.class ; clazz = clazz.getSuperclass()) { 
        	 Method[] methods = clazz.getDeclaredMethods();
             for (Method method : methods) {
                 try {
                     if (method.getName().startsWith("get")) {
                         String field = method.getName();
                         field = field.substring(field.indexOf("get") + 3);
                         field = field.toLowerCase().charAt(0) + field.substring(1);
                         Object value = method.invoke(javaBean, (Object[]) null);
                         result.put(field, null == value ? "" : value.toString());
                     }
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
        }
        return result;
    }
	
	public static String toJson(Object object) {
		JSONObject obj = null;
		if (object instanceof Map) {
			obj = new JSONObject((Map)object);
		} else {
			obj = new JSONObject(toMap(object));
		}
		return obj.toString();
	}
   
}
