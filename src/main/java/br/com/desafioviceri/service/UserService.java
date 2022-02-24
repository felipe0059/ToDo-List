package br.com.desafioviceri.service;

import br.com.desafioviceri.model.User;
import br.com.desafioviceri.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    //RETORNA POR NOME
    public List<User> findByName(String name) {
        return userRepository.findUserByName(name);
    }

    //ATUALIZA POR ID
    public ResponseEntity<User> updateUserById(User user, Long id) {
        return userRepository.findById(id)
                .map(userToUpdate -> {
                    userToUpdate.setName(user.getName());
                    userToUpdate.setLastname(user.getLastname());
                    userToUpdate.setEmail(user.getEmail());
                    userToUpdate.setPassword(user.getPassword());
                    User updated = userRepository.save((userToUpdate));
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    //DELETA POR ID
    public ResponseEntity<Object> deleteById(Long id) {
        return userRepository.findById(id)
                .map(userDelete -> {
                    userRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    //CADASTRA USUARIO NO BANCO
    public Optional<Object> registerUser(User newUser) {
        return userRepository.findByEmail(newUser.getEmail()).map(existentUser -> {
            return Optional.empty();
        }).orElseGet(() -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashPass = encoder.encode(newUser.getPassword());
            newUser.setPassword(hashPass);
            return Optional.ofNullable(userRepository.save(newUser));
        });
    }

    //COMPARA CREDENCIAIS NO BANCO (LOGIN)
    public Optional<?> getCredentials(User userToAuth) {
        return userRepository.findByEmail(userToAuth.getEmail()).map(existentUser -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (encoder.matches(userToAuth.getPassword(), existentUser.getPassword())) {
                String basicStructure = userToAuth.getEmail() + ":" + userToAuth.getPassword();
                byte[] authBase64 = Base64.encodeBase64(basicStructure.getBytes(Charset.forName("US-ASCII")));
                String autorizationHeader = "Basic " + new String(authBase64);

                userToAuth.setId(existentUser.getId());
                userToAuth.setName(existentUser.getName());
                userToAuth.setEmail(existentUser.getEmail());
                userToAuth.setPassword(existentUser.getPassword());
                return Optional.ofNullable(userToAuth);
            } else {
                return Optional.empty();
            }
        }).orElseGet(() -> {
            return Optional.empty();
        });
    }

}