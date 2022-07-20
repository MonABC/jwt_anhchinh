package com.example.jwt_anhchinh2007.security.userprincal;

import com.example.jwt_anhchinh2007.model.Users;
import com.example.jwt_anhchinh2007.repo.IUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private IUsersRepo usersRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepo.findByUsername(username).orElseThrow(() ->new UsernameNotFoundException("user ot found -> username password"+username));

        return UserPrinciple.build(users);
    }
}
