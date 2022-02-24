package br.com.desafioviceri.security;

import br.com.desafioviceri.model.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@NoArgsConstructor
public class UserDetailsImplements implements UserDetails {

    private static final long serialVersionUID = 1L;
    private String email;
    private String senha;
    private List<GrantedAuthority> authorities;

    public UserDetailsImplements(User user) {
        this.email = user.getEmail();
        this.senha = user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

