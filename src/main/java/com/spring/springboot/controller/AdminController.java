package com.spring.springboot.controller;

import com.spring.springboot.model.User;
import com.spring.springboot.service.roles.RoleService;
import com.spring.springboot.service.users.UserService;
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
    public String allUsers(ModelMap model) {

        model.addAttribute("users", userService.getAllUsers());
        return "admin/admin";
    }

    @PostMapping()
    public String creatUser(
            @ModelAttribute("newUser") User user,
            @RequestParam(value = "rolesList", required = false) String[] checkboxRoles) {

        user.setRoles(roleService.setRoleByName(user.getName(), checkboxRoles));
        userService.addUser(user);
        return "redirect:/admin";
    }


    @GetMapping(value = "/new")
    public String addUser(
            ModelMap model) {

        model.addAttribute("user", new User());
        return "admin/new";
    }


    @GetMapping(value = "/{id}/delete")
    public String deleteUser(
            @PathVariable("id") long id) {

        userService.deleteUserById(id);
        return "redirect:/admin";
    }


    @GetMapping(value = "/{id}/edit")
    public String editUser(
            ModelMap model,
            @PathVariable("id") long id) {

        model.addAttribute("rolesList", roleService.getAllRoles());
        model.addAttribute("userEdit", userService.getUserById(id));
        return "admin/edit";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("userEdit") User user,
                         @PathVariable("id") int id,
                         @RequestParam(value = "rolesList", required = false) String[] checkboxRoles) {

        user.setRoles(roleService.setRoleByName(user.getName(), checkboxRoles));
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
