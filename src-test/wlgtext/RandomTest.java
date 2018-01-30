package wlgtext;

public class RandomTest {
	public static void main(String[] args) {
		System.out.println(getRandom(3220.78, 3194.97));
	}

	public static double getRandom(double begin, double end) {
		double random = Math.random();
		return random * (end - begin) + begin;
	}
}
