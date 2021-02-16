package ru.kornilov.reha.service.interfaces;

import ru.kornilov.reha.entities.User;

public interface MailSender {
    //    String emailTo, String subject, String message
    void send(User user);
}
