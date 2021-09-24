package com.spring.springboot.service.roles;

import com.spring.springboot.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    List<Role> getAllRoles();

    void addRole(Role role);

    void updateRole(Role role);

    Role getRoleById(long id);

    void deleteRole(Role role);

    void deleteRoleById(long id);

    Set<Role> setRoleByName(String name, String[] rolesName);
}
