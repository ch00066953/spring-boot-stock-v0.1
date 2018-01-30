package wlgtext;

import java.util.HashMap;

public class TheadLoaclUtil {

	public static ThreadLocal<HashMap> map0 = new ThreadLocal<HashMap>(){ 
        @Override 
        protected HashMap initialValue() { 
            System.out.println(Thread.currentThread().getName()+"initialValue"); 
            return new HashMap(); 
        } 
    }; 
    
    public static HashMap map1 = new HashMap<String, String>();
}
