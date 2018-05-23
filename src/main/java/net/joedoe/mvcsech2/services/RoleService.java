package net.joedoe.mvcsech2.services;

import net.joedoe.mvcsech2.domains.Role;
import net.joedoe.mvcsech2.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IService<Role> {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<Role> listAll() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public Role getById(Integer id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role saveOrUpdate(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }
}
