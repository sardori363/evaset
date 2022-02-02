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
        if (b) return new ApiResponse("Role already exist", false);
        Role role = new Role();
        role.setName(roleDto.getName());
        role.setPermissions(roleDto.getPermissions());
        role.setDescription(roleDto.getDescription());
        roleRepository.save(role);
        return new ApiResponse("Role saved", true);
    }

    public ApiResponse edit(Integer id, RoleDto roleDto) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (!optionalRole.isPresent()) return new ApiResponse("User not found", false);

        Role role = optionalRole.get();
        role.setName(roleDto.getName());
        role.setPermissions(roleDto.getPermissions());
        role.setDescription(roleDto.getDescription());
        roleRepository.save(role);
        return new ApiResponse("Edited", true);
    }

    public ApiResponse get(Integer id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        return optionalRole.map(role -> new ApiResponse("Found", true, role)).orElseThrow(() -> new RescuersNotFoundEx("Role", "id", id));
    }

    public ApiResponse delete(Integer id) {
        boolean b = roleRepository.existsById(id);
        if (!b) return new ApiResponse("Role not found", false);
        roleRepository.deleteById(id);
        return new ApiResponse("Deleted", true);
    }

    public ApiResponse getAllByBusiness(Integer business_id) {
        List<Role> allByBusiness_id = roleRepository.findAllByBusiness_Id(business_id);
        if (allByBusiness_id.isEmpty()) return new ApiResponse("not found", false);
        return new ApiResponse("found", true, allByBusiness_id);
    }
}
