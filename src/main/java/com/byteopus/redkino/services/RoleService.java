package com.byteopus.redkino.services;

import com.byteopus.redkino.models.Role;
import com.byteopus.redkino.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public void save(Role role) {
        roleRepository.save(role);
    }
    public Role findByName(String name){
        return roleRepository.findByName(name).orElse(null);
    }
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}

