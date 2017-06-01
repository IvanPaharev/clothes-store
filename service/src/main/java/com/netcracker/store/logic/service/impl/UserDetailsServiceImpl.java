package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.UserService;
import com.netcracker.store.persistence.entity.Role;
import com.netcracker.store.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by A-one on 23.04.2017.
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(s);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoleSet());
        return buildUserForAuthentication(user, authorities);
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
                                            List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> authoritySet = new HashSet<>();
        for (Role userRole : userRoles) {
            authoritySet.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        List<GrantedAuthority> Result = new ArrayList<>(authoritySet);
        return Result;
    }
}
