package wlgtext;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;

public class md5 {

	@Test
	public void md5test() throws NoSuchAlgorithmException {
		try {
			System.out.println(MessageDigest.getDigestAsUpperHexString("MD5","woshiwang6"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void jiamitest() throws IOException {
	           String msg = "semssems";  
	           BASE64Encoder encode = new BASE64Encoder();  
	           String jiami =encode.encode(msg.getBytes());  
	           BASE64Decoder decoder = new BASE64Decoder();  
	           String jiemi = new String(decoder.decodeBuffer(jiami));  
	           System.out.println("加密前："+msg);  
	           System.out.println("加密后"+jiami);  
	           System.out.println("解密后"+jiemi);  
	}
}
