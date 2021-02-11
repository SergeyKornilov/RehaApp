package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kornilov.reha.DAO.UserDAO;
import ru.kornilov.reha.entities.Role;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.service.mail.MailSender;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Value("C:/Users/Serega/Desktop/T-System/Reha/src/uploads/")
    private String uploadPath;

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private MailSender mailSender;

    @Transactional
    public User findByUsername(String name) {
        return userDAO.findByUsername(name);
    }

    @Transactional
    public void addUser(User user) {
        user.setEnabled(false);

        user.setActivationCode(UUID.randomUUID().toString());

        userDAO.addUser(user);
        mailSender.send(user);
    }

    @Transactional
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Transactional
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Transactional
    public List<User> allUsers() {
        return userDAO.allUsers();
    }

    @Transactional
    public List<User> findAllUsersRolDoctor() {

        List<User> users = allUsers().stream()
                .filter(user -> user.getRoles().contains(Role.ROLE_DOCTOR)).collect(Collectors.toList());
        return users.size() > 0 ? users : null;

    }

    @Transactional
    public void activateUser(String code){
        User user = userDAO.findByActivationCode(code).get(0);

        user.setEnabled(true);

    }

    @Transactional
    public String addImg(User user, MultipartFile file) throws IOException {
        System.out.println(file);
        if(file.getSize() != 0){
            File uploadDir = new File(uploadPath);

            if(checkTypeFile(file.getOriginalFilename())) {


                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                user.setImgName(resultFilename);
                updateUser(user);
                return "";
            } else return "Only .JPEG .JPG .PNG files!";
        } return "You need to select file first!";
    }

    private boolean checkTypeFile(String fileName){

        String fileExtentions = ".JPEG,.JPG,.PNG";

        int lastIndex = fileName.lastIndexOf('.');

        String fileTypeStr = fileName.substring(lastIndex);

        if (fileExtentions.contains(fileTypeStr)){
            return true;
        } else return false;
    }
}