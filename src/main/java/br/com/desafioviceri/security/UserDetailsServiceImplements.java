package br.com.desafioviceri.security;

import br.com.desafioviceri.model.User;
import br.com.desafioviceri.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

    private @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user= userRepository.findByEmail(username);

        if (user.isPresent()) {
            return new UserDetailsImplements(user.get());
        } else {
            throw new UsernameNotFoundException(username + " não encontrado.");
        }
    }

}
