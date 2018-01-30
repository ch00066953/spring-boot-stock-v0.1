package wlgtext;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Reflect {

	public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Reflect rf = new Reflect();
		rf.run();
	}
	
	public void run() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
		this.getClass().getDeclaredMethod("methodAa1LOAN").invoke(this);
	}
	
	public void methodAa1LOAN() {
		System.out.println("11111");

	}
}
