package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;
import ru.kornilov.reha.DAO.UserDAO;
import ru.kornilov.reha.entities.Role;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.exceptions.UploadImgException;
import ru.kornilov.reha.service.interfaces.UserService;
import ru.kornilov.reha.service.interfaces.MailSender;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * This class contains methods that serve Users
 */

@Service
public class UserServiceImpl implements UserService {

    @Value("C:/Users/Serega/Desktop/T-System/Reha/src/uploads/")
    private String uploadPath;

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private MailSender mailSender;

    /**
     * This method gets user form DB by username
     * @param name String username
     * @return User
     */
    @Override
    @Transactional
    public User findByUsername(String name) {
        return userDAO.findByUsername(name);
    }

    /**
     * This method add User in DB
     * and sends message to user`s Email
     * @param user
     */
    @Override
    @Transactional
    public void addUser(User user) {
        user.setEnabled(false);
        user.setActivationCode(UUID.randomUUID().toString());
        userDAO.addUser(user);
        mailSender.send(user);
    }

    /**
     * This method updates user in DB
     * and sends message to user`s Email
     * @param user instance of User
     */
    @Override
    @Transactional
    public void updateUser(User user) {
        user.setEnabled(false);
        user.setActivationCode(UUID.randomUUID().toString());
        mailSender.send(user);
        userDAO.updateUser(user);
    }

    /**
     * This method delete user from DB
     * @param user instance of User
     */
    @Override
    @Transactional
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    /**
     * This method gets all users from DB
     * @return List of User
     */
    @Override
    @Transactional
    public List<User> allUsers() {
        return userDAO.allUsers();
    }

    /**
     * This method gets users from DB by Role = ROLE_DOCTOR
     * @return List of User
     */
    @Override
    @Transactional
    public List<User> findAllUsersRolDoctor() {

        List<User> users = allUsers().stream()
                .filter(user -> user.getRoles().contains(Role.ROLE_DOCTOR)).collect(Collectors.toList());
        return users.size() > 0 ? users : null;

    }

    /**
     * This method gets user from DB by field ActivationCode
     * and set Enabled true
     * @param code
     */
    @Override
    @Transactional
    public void activateUser(String code){
        User user = userDAO.findByActivationCode(code).get(0);

        user.setEnabled(true);
    }

    /**
     * This method validate image file,
     * upload image
     * and set User`s image name
     * @param user instance of User
     * @param file image file
     * @return String error
     * @throws UploadImgException error during upload
     */
    @Override
    @Transactional
    public String addImg(User user, MultipartFile file) throws UploadImgException {
        if(file.getSize() != 0){
            File uploadDir = new File(uploadPath);

            if(checkTypeFile(file.getOriginalFilename())) {

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                try {
                    file.transferTo(new File(uploadPath + "/" + resultFilename));
                } catch (IOException e) {
                    throw new UploadImgException(e);
                }

                user.setImgName(resultFilename);
                updateUser(user);
                return "";
            } else return "Only .JPEG .JPG .PNG files!";
        } return "You need to select file first!";
    }

    /**
     * This method validate type image file
     * @param fileName image file
     * @return boolean true when valid, else false
     */
    private boolean checkTypeFile(String fileName){

        String fileExtentions = ".JPEG,.JPG,.PNG";

        int lastIndex = fileName.lastIndexOf('.');

        String fileTypeStr = fileName.substring(lastIndex);

        if (fileExtentions.contains(fileTypeStr)){
            return true;
        } else return false;
    }

    /**
     * This method create redirect url depends User role
     * @return String redirect url
     */
    @Override
    public String getAuthorizedRedirect(){
        String viewName = "";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        switch (auth.getAuthorities().toString()) {

            case ("[ROLE_ADMIN]"):
                viewName = "redirect:/admin";
                break;
            case ("[ROLE_DOCTOR]"):
                viewName = "redirect:/patient-list";
                break;
            case ("[ROLE_NURSE]"):
                viewName = "redirect:/event-list";
                break;
            default: viewName = "main/login";
                break;
        }

        return viewName;
    }

    /**
     * This method validate User,
     * if valid update in in DB
     * @param bindingResult instance of bindingResult
     * @param newUser instance of User
     * @return Set of String errors
     */
    @Override
    @Transactional
    public Set<String> editUser(BindingResult bindingResult, User newUser){
        Set<String> errors = validateUser(bindingResult, newUser);

        if (errors.isEmpty()) {
            updateUser(newUser);
        }

        return errors;
    }

    /**
     * This method validate User,
     * if valid adds it in DB
     * @param bindingResult instance of BindingResult
     * @param newUser instance of User
     * @return Set of String errors
     */
    @Override
    @Transactional
    public Set<String> createUser(BindingResult bindingResult, User newUser){
        Set<String> errors = validateUser(bindingResult, newUser);

        User userFromDb = findByUsername(newUser.getUsername());
        if (userFromDb != null) {
            errors.add("duplicate username");
        }

        if (errors.isEmpty()) {
            addUser(newUser);
        }
        return errors;
    }

    /**
     * This method adds errors from BindingResult
     * to Set of String
     * @param bindingResult instance of BindingResult
     * @param newUser instance of User
     * @return Set of String errors
     */
    @Override
    public Set<String> validateUser(BindingResult bindingResult, User newUser){

        Set<String> errors = new HashSet<>();

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                String fieldError = ((FieldError) error).getField();
                errors.add(fieldError);
            }
        }

        return errors;
    }


}