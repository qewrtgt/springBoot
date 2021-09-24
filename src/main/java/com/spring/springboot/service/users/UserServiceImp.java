package com.spring.springboot.service.users;

import com.spring.springboot.dao.roles.RoleDao;
import com.spring.springboot.dao.users.UserDao;
import com.spring.springboot.model.Role;
import com.spring.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImp implements UserService {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    private RoleDao roleDao;


    public UserServiceImp(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user, String[] selectedRoles) {
        if (!user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (selectedRoles != null) {
            Set<Role> roles = new HashSet();
            for (String nameRole : selectedRoles) {
                roles.add(roleDao.getRoleByName(nameRole));
            }
            user.setRoles(roles);
        }
        userDao.updateUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }


    @Autowired
    public void setCryptPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //    PasswordEncoderBean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public String getRolesString(User user) {
        Set<Role> userRolesSet = user.getRoles();
        StringBuilder userRoles = new StringBuilder();
        for (Role role : userRolesSet) {
            userRoles.append(role.toString()).append(" ");
        }
        return userRoles.toString();
    }
}
