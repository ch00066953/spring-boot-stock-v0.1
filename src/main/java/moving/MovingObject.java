package moving;

public abstract class MovingObject {
	
	public static String name ="";  
	public static String high ="";  
	public static String low ="";  

	public MovingObject() {
		set();
	}
	
	public abstract void set();
}
