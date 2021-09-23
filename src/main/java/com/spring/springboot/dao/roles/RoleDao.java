package com.spring.springboot.dao.roles;


import com.spring.springboot.model.Role;

import java.util.List;

public interface RoleDao{
   List<Role> getAllRoles();

   void addRole(Role role);

   void updateRole(Role role);

   Role getRoleById(long id);

   void deleteRole(Role role);

   void deleteRoleById(long id);

   Role getRoleByName(String name);

}
