package me.strand.service.auth;

import lombok.RequiredArgsConstructor;
import me.strand.mapper.RoleMapper;
import me.strand.mapper.UserMapper;
import me.strand.model.dto.role.Role;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    public final UserMapper userMapper;
    public final RoleMapper roleMapper;

    public CustomUserDetails loadUserByUsernameAndPassword(String username, String password) {
        var userResponse = userMapper.getUserByUsername(username);

        if (userResponse != null && Objects.equals(userResponse.getPassword(), password)) {
            var roles = roleMapper.getRolesByGroupId(userResponse.getIdUserGroup());

            return new CustomUserDetails(
                    userResponse,
                    roles.stream().map(Role::getName).toList()
            );
        }

        return null;
    }

    public CustomUserDetails loadUserByUsername(String username) {
        var userResponse = userMapper.getUserByUsername(username);

        if (userResponse != null) {
            var roles = roleMapper.getRolesByGroupId(userResponse.getIdUserGroup());

            return new CustomUserDetails(
                    userResponse,
                    roles.stream().map(Role::getName).toList()
            );
        }

        return null;
    }
}
