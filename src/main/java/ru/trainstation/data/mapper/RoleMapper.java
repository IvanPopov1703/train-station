package ru.trainstation.data.mapper;

import org.springframework.stereotype.Component;
import ru.trainstation.data.dto.RoleDto;
import ru.trainstation.data.entity.Role;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public RoleDto toRoleDto(Role role) {
        return new RoleDto(role.getRoleId(), role.getName());
    }

    public List<RoleDto> toRoleDto(List<Role> roles) {
        return roles.stream()
                .map(this::toRoleDto)
                .collect(Collectors.toList());
    }

    public Role toRoleFromRoleDto(RoleDto roleDto) {
        return new Role(roleDto.getRoleId(), roleDto.getName());
    }
}
