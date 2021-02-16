package ru.kornilov.reha.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.service.interfaces.MailSender;

/**
 *This class is responsible for send message on Email
 */
@Service
public class MailSenderImpl implements MailSender {


    @Autowired
    private JavaMailSender mailSender;

    @Value("nazareth-israel-clinic@yandex.ru")
    private String username;

    /**
     * This method prepares and sends message on Email
     * @param user instance of User that will accept the email
     */
    @Override
    public void send(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Activate your account in Nazareth-Lazaret Clinic!");
        String message = String.format("Hello, %s!\n" +
                        "Your account has been created.\n" +
                        "To activate your account, please visit next link: http://localhost:8080/user/activate/%s",
                user.getFullName(),
                user.getActivationCode()
        );
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

}
