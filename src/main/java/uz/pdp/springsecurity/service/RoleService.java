package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Role;
import uz.pdp.springsecurity.exeptions.RescuersNotFoundEx;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.RoleDto;
import uz.pdp.springsecurity.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse add(RoleDto roleDto) {
        boolean b = roleRepository.existsByName(roleDto.getName());
        if (b) return new ApiResponse("ROLE ALREADY EXIXTS", false);
        Role role = new Role();
        role.setName(roleDto.getName());
        role.setPermissions(roleDto.getPermissions());
        role.setDescription(roleDto.getDescription());
        roleRepository.save(role);
        return new ApiResponse("ADDED", true);
    }

    public ApiResponse edit(Integer id, RoleDto roleDto) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isEmpty()) return new ApiResponse("USER NOT FOUND", false);

        Role role = optionalRole.get();
        role.setName(roleDto.getName());
        role.setPermissions(roleDto.getPermissions());
        role.setDescription(roleDto.getDescription());
        roleRepository.save(role);
        return new ApiResponse("EDITED", true);
    }

    public ApiResponse get(Integer id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        return optionalRole.map(role -> new ApiResponse("FOUND", true, role)).orElseThrow(() -> new RescuersNotFoundEx("Role", "id", id));
    }

    public ApiResponse delete(Integer id) {
        boolean b = roleRepository.existsById(id);
        if (!b) return new ApiResponse("ROLE NOT FOUND", false);
        roleRepository.deleteById(id);
        return new ApiResponse("DELETED", true);
    }

    public ApiResponse getAllByBusiness(Integer business_id) {
        List<Role> allByBusiness_id = roleRepository.findAllByBusiness_Id(business_id);
        if (allByBusiness_id.isEmpty()) return new ApiResponse("NOT FOUND", false);
        return new ApiResponse("FOUND", true, allByBusiness_id);
    }
}
