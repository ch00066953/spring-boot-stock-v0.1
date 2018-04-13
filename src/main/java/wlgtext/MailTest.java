package wlgtext;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.Session;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class MailTest {
	private static String smtpHost = "smtp.sina.com";
	private static String from = "ch000669531@sina.com";
	private static String to = "272026941@qq.com";
    
    public static void sendMessage(String smtpHost,
                                   String from, String to,
                                   String subject, String messageText)
            throws MessagingException,java.io.UnsupportedEncodingException {

        // Step 1:  Configure the mail session
        System.out.println("Configuring mail session for: " + smtpHost);
        java.util.Properties props = new java.util.Properties();
        props.setProperty("mail.smtp.auth", "true");//指定是否需要SMTP验证
        props.setProperty("mail.smtp.host", smtpHost);//指定SMTP服务器
        props.put("mail.transport.protocol", "smtp");
        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);//是否在控制台显示debug信息

        // Step 2:  Construct the message
        System.out.println("Constructing message -  from=" + from + "  to=" + to);
        InternetAddress fromAddress = new InternetAddress(from);
        InternetAddress toAddress = new InternetAddress(to);

        MimeMessage testMessage = new MimeMessage(mailSession);
        testMessage.setFrom(fromAddress);
        testMessage.addRecipient(javax.mail.Message.RecipientType.TO, toAddress);
        testMessage.setSentDate(new java.util.Date());
        testMessage.setSubject(MimeUtility.encodeText(subject,"gb2312","B"));

        testMessage.setContent(messageText, "text/html;charset=gb2312");
        System.out.println("Message constructed");

        // Step 3:  Now send the message
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(smtpHost, "ch000669531@sina.com", "woshiwang1");
        transport.sendMessage(testMessage, testMessage.getAllRecipients());
        transport.close();


        System.out.println("Message sent!");
    }

    /**
     * 错误信息邮件
     * @param error
     */
    public static void sendError(String error){
         String subject = "错误信息！"; //subject javamail自动转码

         try {
             MailTest.sendMessage(smtpHost, from, to, subject, error);
         }
         catch (javax.mail.MessagingException exc) {
             exc.printStackTrace();
         }
         catch (java.io.UnsupportedEncodingException exc) {
             exc.printStackTrace();
         }
    }
    
    /**
     * 提示信息邮件
     * @param subject
     * @param messageText
     */
    public static void sendPrompt(String subject, String messageText){
        try {
            MailTest.sendMessage(smtpHost, from, to, subject, messageText);
        }
        catch (javax.mail.MessagingException exc) {
            exc.printStackTrace();
        }
        catch (java.io.UnsupportedEncodingException exc) {
            exc.printStackTrace();
        }
   }
    
    public static void main(String[] args) {

        String smtpHost = "smtp.sina.com";
        String from = "ch000669531@sina.com";
        String to = "1026768953@qq.com";
        String subject = "html"; //subject javamail自动转码

        StringBuffer theMessage = new StringBuffer();
        //theMessage.append("<h2><font color=\"red\">这倒霉孩子</font></h2>");
        //theMessage.append("<hr>");
        //theMessage.append("<i>年年失望年年望</i>");
        theMessage.append("<a href=http://aaa/>aaa</a>");

        try {
            MailTest.sendMessage(smtpHost, from, to, subject, theMessage.toString());
        }
        catch (javax.mail.MessagingException exc) {
            exc.printStackTrace();
        }
        catch (java.io.UnsupportedEncodingException exc) {
            exc.printStackTrace();
        }
    }
}