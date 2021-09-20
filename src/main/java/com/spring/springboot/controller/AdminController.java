package com.spring.springboot.controller;

import com.spring.springboot.model.User;
import com.spring.springboot.service.roles.RoleService;
import com.spring.springboot.service.users.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
                model.addAttribute("authorizedUser",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
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


    @DeleteMapping(value = "/{id}/delete")
    public String deleteUser(
            @PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }


    @PostMapping(value = "/{id}/edit")
    public String editUser(
            ModelMap model,
            @PathVariable("id") long id) {
        model.addAttribute("rolesList", roleService.getAllRoles());
        model.addAttribute("userEdit", userService.getUserById(id));
        return "redirect:/admin";
    }
}