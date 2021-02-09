package ru.kornilov.reha.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.entities.User;

@Service
public class MailSender {


    @Autowired
    private JavaMailSender mailSender;

    @Value("nazareth-israel-clinic@yandex.ru")
    private String username;

//    String emailTo, String subject, String message
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
