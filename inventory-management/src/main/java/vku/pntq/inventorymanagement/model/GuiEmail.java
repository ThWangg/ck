package vku.pntq.inventorymanagement.model;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class GuiEmail {

    private final String username = "guimaresetmk@gmail.com";
    private final String password = "nyvi pjyb cmlq hzvi";

    public boolean sendResetPasswordEmail(String mailNguoiNhan, String maXacNhan) {
        String host = "smtp.gmail.com";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailNguoiNhan));
            message.setSubject("Mã xác thực đổi mật khẩu");

            String noiDung = "Bạn đã yêu cầu thay đổi mật khẩu. Mã xác thực của bạn là: " + maXacNhan;

            message.setText(noiDung);

            Transport.send(message);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}