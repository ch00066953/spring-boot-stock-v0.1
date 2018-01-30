package wlgtext.thread;
/**
 * @author Rollen-Holt 继承Thread类,直接调用run方法
 * */
class TestThread extends Thread {
 
    public TestThread() {
 
    }
 
    public TestThread(String name) {
        this.name = name;
    }
 
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行     " + i);
        }
    }
 
    public static void main(String[] args) {
    	TestThread h1=new TestThread("A");
    	TestThread h2=new TestThread("B");
        h1.run();
        h2.run();
    }
 
    private String name;
}