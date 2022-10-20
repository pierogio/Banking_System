package ironhack.banking_system.banking_system.services;

import ironhack.banking_system.banking_system.repositories.UserRepository;
import ironhack.banking_system.banking_system.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        if (!userRepository.findByName(name).isPresent()) {
            throw new UsernameNotFoundException("User does not exist");
        }
        //return new CustomUserDetails(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User does not exist")));
        return new CustomUserDetails(userRepository.findByName(name).get());
    }
}
