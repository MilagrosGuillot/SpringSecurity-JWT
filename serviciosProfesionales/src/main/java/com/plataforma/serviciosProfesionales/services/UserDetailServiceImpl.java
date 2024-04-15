package com.plataforma.serviciosProfesionales.services;

import com.plataforma.serviciosProfesionales.dto.request.AuthCreateUserRequest;
import com.plataforma.serviciosProfesionales.dto.request.AuthLoginRequest;
import com.plataforma.serviciosProfesionales.dto.response.ProfesionalResponse;
import com.plataforma.serviciosProfesionales.dto.response.AuthResponse;
import com.plataforma.serviciosProfesionales.dto.response.UsersResponse;
import com.plataforma.serviciosProfesionales.persistence.repository.ProfesionalRepository;
import com.plataforma.serviciosProfesionales.persistence.repository.RolRepository;
import com.plataforma.serviciosProfesionales.persistence.repository.UsuarioRepository;
import com.plataforma.serviciosProfesionales.persistence.entitys.Calificaciones;
import com.plataforma.serviciosProfesionales.persistence.entitys.Profesional;
import com.plataforma.serviciosProfesionales.persistence.entitys.Rol;
import com.plataforma.serviciosProfesionales.persistence.entitys.Usuario;
import com.plataforma.serviciosProfesionales.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario userEntity = userRepository.findUsuarioByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRolEnum().name()))));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));


        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList);
    }

    public AuthResponse createUser(AuthCreateUserRequest createRoleRequest) {

        String username = createRoleRequest.username();
        String password = createRoleRequest.password();
        List<String> rolesRequest = createRoleRequest.roleRequest().roleListName();

        Set<Rol> roleEntityList = rolRepository.findRolByRolEnumIn(rolesRequest).stream().collect(Collectors.toSet());

        if (roleEntityList.isEmpty()) {
            throw new IllegalArgumentException("The roles specified does not exist.");
        }

        Usuario userEntity = Usuario.builder().username(username).password(passwordEncoder.encode(password)).roles(roleEntityList).isEnabled(true).accountNoLocked(true).accountNoExpired(true).credentialNoExpired(true).build();

        Usuario userSaved = userRepository.save(userEntity);

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userSaved.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRolEnum().name()))));

        userSaved.getRoles().stream().flatMap(role -> role.getPermissionList().stream()).forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        SecurityContext securityContextHolder = SecurityContextHolder.getContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved, null, authorities);

        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, "User created successfully", accessToken, true);
        return authResponse;
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);
        AuthResponse authResponse = new AuthResponse(username, "User loged succesfully", accessToken, true);
        return authResponse;
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException(String.format("Invalid username or password"));
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    public AuthResponse allprofesionals(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);
        AuthResponse authResponse = new AuthResponse(username, "User loged succesfully", accessToken, true);
        return authResponse;
    }

    public List<ProfesionalResponse> getAllProfesionals() {
        List<Profesional> profesionals = profesionalRepository.findAll();
        return profesionals.stream()
                .map(this::mapProfesionalToResponseDto)
                .collect(Collectors.toList());
    }

    public List<UsersResponse> getAllUsers() {
        List<Usuario> profesionales = userRepository.findAllUsuarios();
        return profesionales.stream()
                .map(this::mapUsuarioToResponseDto)
                .collect(Collectors.toList());

    }

    // Método privado para mapear una entidad que sale hacia una respuesta como objeto DTO
    private ProfesionalResponse mapProfesionalToResponseDto(Profesional profesional) {
        ProfesionalResponse profesionalResponseDto = new ProfesionalResponse();
        profesionalResponseDto.setDNI(profesional.getDNI());

        // Obtenemos el objeto Calificaciones del profesional
        Calificaciones calificaciones = profesional.getCalificaciones();

        // Establecemos la calificación y la opinión del objeto Calificaciones
        calificaciones.setCalificacion(profesional.getCalificaciones().getCalificacion());
        calificaciones.setOpinion(profesional.getCalificaciones().getOpinion());

        // Establecemos el objeto Calificaciones modificado en el ProfesionalResponse
        profesionalResponseDto.setCalificaciones(calificaciones);

        // Resto del código para establecer otros atributos del ProfesionalResponse
        profesionalResponseDto.setCorreo(profesional.getCorreo());
        profesionalResponseDto.setUsername(profesional.getUsername());
        profesionalResponseDto.setCategoriasDeTrabajos(profesional.getCategoriasDeTrabajos());

        return profesionalResponseDto;
    }
    // Método privado para mapear una entidad que sale hacia una respuesta como objeto DTO
    private UsersResponse mapUsuarioToResponseDto(Usuario usuario) {
        UsersResponse usersResponseDto = new UsersResponse();
        usersResponseDto.setUsername(usuario.getUsername());
        usersResponseDto.setCorreo(usuario.getCorreo());
        usersResponseDto.setEnabled(usuario.isEnabled());
        usersResponseDto.setAccountNoExpired(usuario.isAccountNoExpired());
        usersResponseDto.setAccountNoLocked(usuario.isAccountNoLocked());
        usersResponseDto.setCredentialNoExpired(usuario.isCredentialNoExpired());

        // Mapear los roles
        Set<String> roles = new HashSet<>();

        usersResponseDto.setRoles(usuario.getRoles());

        // Mapear los profesionales asociados al usuario
        Set<String> profesional = new HashSet<>();

        usersResponseDto.setProfesional(usuario.getProfesional());

        return usersResponseDto;
    }


    // public List<Categorias> getcategory(String categorias) {
    //    List<Categorias> profesionales = categoriaDeTrabajoRepository.findBycategorias(categorias);
    //  return profesionales;

    //   }


}
