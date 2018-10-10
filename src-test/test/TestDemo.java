package test;

class Test<T> {

	private T ob; // 定义泛型成员变量

	public Test(T ob) {

		this.ob = ob;

	}

	public T getOb() {

		return ob;

	}

	public void setOb(T ob) {

		this.ob = ob;

	}

	public void showType() {

		System.out.println("T的实际类型是: " + ob.getClass().getName());

	}

}

public class TestDemo {

	public static void main(String[] args) {

		// 定义泛型类Gen的一个Integer版本

		Test<Integer> intOb = new Test<Integer>(88);

		intOb.showType();

		int i = intOb.getOb();

		System.out.println("value= " + i);

		System.out.println("----------------------------------");

		// 定义泛型类Gen的一个String版本

		Test<String> strOb = new Test<String>("Hello Gen!");

		strOb.showType();

		String s = strOb.getOb();

		System.out.println("value= " + s);

	}

}