package com.spring.springboot.service.roles;

import com.spring.springboot.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    public List<Role> getAllRoles();

    public void addRole(Role role);

    public void updateRole(Role role);

    public Role getRoleById(long id);

    public void deleteRole(Role role);

    public void deleteRoleById(long id);

    Set<Role> setRoleByName(String name, String[] rolesName);
}
