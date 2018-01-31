package wlgtext;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import tool.StringX;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MessageDigest
/*     */ {
/*     */   public MessageDigest() {}
/*     */   
/*     */   public static byte[] getDigest(String algorithm, byte[] srcData)
/*     */     throws NoSuchAlgorithmException
/*     */   {
/*  26 */     byte[] dgt = null;
/*  27 */     if (srcData == null) return dgt;
/*  28 */     if (algorithm == null) algorithm = "MD5";
/*  29 */     java.security.MessageDigest md = java.security.MessageDigest.getInstance(algorithm.toUpperCase());
/*  30 */     md.reset();
/*  31 */     md.update(srcData);
/*  32 */     dgt = md.digest();
/*  33 */     return dgt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] getDigest(String algorithm, String srcData)
/*     */     throws NoSuchAlgorithmException
/*     */   {
/*  45 */     if (srcData == null) return null;
/*  46 */     byte[] sb = null;
/*     */     try {
/*  48 */       sb = srcData.getBytes("UTF-8");
/*     */     } catch (UnsupportedEncodingException e) {
/*  51 */       sb = srcData.getBytes();
/*     */     }
/*  53 */     return getDigest(algorithm, sb);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getDigestAsLowerHexString(String algorithm, String srcData)
/*     */     throws NoSuchAlgorithmException
/*     */   {
/*  66 */     if (srcData == null) return null;
/*  67 */     return StringX.bytesToHexString(getDigest(algorithm, srcData.getBytes()), false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getDigestAsUpperHexString(String algorithm, String srcData)
/*     */     throws NoSuchAlgorithmException
/*     */   {
/*  80 */     if (srcData == null) return null;
/*  81 */     return StringX.bytesToHexString(getDigest(algorithm, srcData.getBytes()), true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getDigestAsLowerHexString(String algorithm, byte[] srcData)
/*     */     throws NoSuchAlgorithmException
/*     */   {
/*  94 */     return StringX.bytesToHexString(getDigest(algorithm, srcData), false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getDigestAsUpperHexString(String algorithm, byte[] srcData)
/*     */     throws NoSuchAlgorithmException
/*     */   {
/* 107 */     return StringX.bytesToHexString(getDigest(algorithm, srcData), true);
/*     */   }
/*     */ }

/* Location:           D:\wlgwork\AmarGCI_TX\lib\are-1.0b93-rc3_g.jar
 * Qualified Name:     com.amarsoft.are.security.MessageDigest
 * Java Class Version: 1.4 (48.0)
 * JD-Core Version:    0.7.0.1
 */