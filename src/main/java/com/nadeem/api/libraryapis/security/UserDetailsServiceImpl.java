package com.nadeem.api.libraryapis.security;

import com.nadeem.api.libraryapis.exceptions.LibraryResourceNotFoundException;
import com.nadeem.api.libraryapis.user.User;
import com.nadeem.api.libraryapis.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = null;

        try {
            user = userService.getUserByUsername(username);
        } catch (LibraryResourceNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }

        return user;
    }
}