/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgrDaemon;

/**
 *
 * @author william
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
    @SuppressWarnings("static-access")
    public static void sendMessage(String smtpHost, String from,
            String fromUserPassword, String to, String subject,
            String messageText, String messageType) throws MessagingException {
        // 第一步：配置javax.mail.Session对象  
        //System.out.println("为" + smtpHost + "配置mail session对象");
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.starttls.enable", "true");//使用 STARTTLS安全连接  
        //props.put("mail.smtp.port", "25");             //google使用465或587端口  
        props.put("mail.smtp.auth", "true");        // 使用验证  
        //props.put("mail.debug", "true");  
        Session mailSession = Session.getInstance(props, new MyAuthenticator(from, fromUserPassword));

        // 第二步：编写消息  
        //System.out.println("编写消息from——to:" + from + "——" + to);

        InternetAddress fromAddress = new InternetAddress(from);
        InternetAddress toAddress = new InternetAddress(to);

        MimeMessage message = new MimeMessage(mailSession);

        message.setFrom(fromAddress);
        message.addRecipient(RecipientType.TO, toAddress);

        message.setSentDate(Calendar.getInstance().getTime());
        message.setSubject(subject);
        message.setContent(messageText, messageType);

        // 第三步：发送消息  
        Transport transport = mailSession.getTransport("smtp");
        //transport.connect(smtpHost, "chaofeng19861126", fromUserPassword);
        transport.connect();
        transport.send(message, message.getRecipients(RecipientType.TO));
        //System.out.println("message yes");
    }

    public static boolean autoReplyMail(String sto, String smsg) {
//        try {
//            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String curtime = sd.format(new Date());
//            SendMail.sendMessage("smtp.exmail.qq.com", "",
//                    "", sto, "[赢多智能]-[" + curtime + "]",
//                    "-----------[机器选股列表]-----------<br><br>" + smsg + "<br><br>免责声明<br>" + "-----------------<br><br>" +
//    "本邮件仅呈现机器人深度学习的成果，并不构成任何投资建议或实际的投资操作。<br>我们不保证其中的观点和判断不会发生任何调整或变更，投资者据此发生的投资行为与本公司无关!",
//                    "text/html;charset=gb2312");
//        } catch (MessagingException e) {
//            // TODO Auto-generated catch block
//            //e.printStackTrace();
//            return false;
//        }
        return true;
    }

}

class MyAuthenticator extends Authenticator {

    String userName = "";
    String password = "";

    public MyAuthenticator() {

    }

    public MyAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}
