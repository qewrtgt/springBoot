package com.spring.springboot.dao.roles;


import com.spring.springboot.model.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> getAllRoles();

    public void addRole(Role role);

    public void updateRole(Role role);

    public Role getRoleById(long id);

    public void deleteRole(Role role);

    public void deleteRoleById(long id);

    public Role getRoleByName(String name);

}
