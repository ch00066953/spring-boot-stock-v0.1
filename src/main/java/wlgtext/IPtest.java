package wlgtext;

import java.net.InetAddress;

public class IPtest {
	public static void main(String[] args) throws Exception {
        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println(ip);
   }

}
