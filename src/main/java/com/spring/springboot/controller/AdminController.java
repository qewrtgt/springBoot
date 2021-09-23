package com.spring.springboot.controller;

import com.spring.springboot.model.User;
import com.spring.springboot.service.roles.RoleService;
import com.spring.springboot.service.users.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public String getAllUsers(ModelMap model) {

        User autorizedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("authorizedUser", autorizedUser);
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("userRolesStr", userService.getRolesString(autorizedUser));
        return "admin/admin";
    }

    @PostMapping(value = "/create")
    public String creatUser(
            @ModelAttribute("newUser") User user,
            @RequestParam(value = "listRoles", required = false) String[] listRoles) {
        user.setRoles(roleService.setRoleByName(user.getName(), listRoles));
        userService.addUser(user);
        return "redirect:/admin";
    }


    @DeleteMapping(value = "/delete")
    public String deleteUser(@ModelAttribute User user) {
        System.out.println(user);
        userService.deleteUser(user);
        return "redirect:/admin";
    }


    @PatchMapping(value = "/update")
    public String updateUser(@ModelAttribute User user, @RequestParam(value = "roleList", required = false) String[] selectedRoles) {
        System.out.println(user);
        System.out.println(Arrays.toString(selectedRoles));
        userService.updateUser(user, selectedRoles);
        return "redirect:/admin";
    }
}