package pe.edu.upc.safezone.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.safezone.entities.Usuario;
import pe.edu.upc.safezone.repositories.IUsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = repo.findOneByEmailUsuario(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("User not exists: " + username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        System.out.println("===== LOGIN DEBUG =====");
        System.out.println("EMAIL: " + username);
        System.out.println("USUARIO EN BD: " + usuario.getEmailUsuario());
        System.out.println("STATUS: " + usuario.getStatusUsuario());

        System.out.println("ROLES EN BD:");
        usuario.getRol().forEach(r ->
                System.out.println(" - " + r.getNameRol())
        );

        // 🔥 AQUÍ ESTABA EL ERROR (AHORA CORREGIDO)
        usuario.getRol().forEach(r -> {
            roles.add(new SimpleGrantedAuthority(r.getNameRol()));
        });

        System.out.println("ROLES SPRING SECURITY:");
        roles.forEach(r ->
                System.out.println(" - " + r.getAuthority())
        );

        System.out.println("=======================");

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmailUsuario(),
                usuario.getPasswordUsuario(),
                usuario.getStatusUsuario(),
                true,
                true,
                true,
                roles
        );
    }
}