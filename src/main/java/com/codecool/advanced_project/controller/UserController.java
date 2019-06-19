package com.codecool.advanced_project.controller;

import com.codecool.advanced_project.credentials.LoginCredentials;
import com.codecool.advanced_project.credentials.RegistrationCredentials;
import com.codecool.advanced_project.entity.AppUser;
import com.codecool.advanced_project.entity.GroupEntity;
import com.codecool.advanced_project.repository.GroupRepository;
import com.codecool.advanced_project.repository.UserRepository;
import com.codecool.advanced_project.security.JwtTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenServices jwtTokenServices;

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    //Todo: Ask mentors about the need of service layer
    @Autowired
    private GroupRepository groupRepository;

    public UserController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, UserRepository users) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
    }

    @PostMapping("/auth")
    public ResponseEntity signin(@RequestBody LoginCredentials data) {
        try {
            String username = data.getUsername();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(username, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @PostMapping
    public void register(@RequestBody RegistrationCredentials credentials) {
        GroupEntity existingGroup = null;
        if (credentials.getGroupKey() != null) {
            existingGroup = groupRepository.findByKeyForConnection(credentials.getGroupKey());
        }

        AppUser appUser = AppUser.builder()
                .username(credentials.getUsername())
                .email(credentials.getEmail())
                .password(passwordEncoder.encode(credentials.getPassword()))
                .build();

        AppUser user = userRepository.save(appUser);

        if (existingGroup != null) {
            existingGroup.getMembers().add(appUser);
            //Todo: Miki look here
            groupRepository.deleteById(existingGroup.getId());
            groupRepository.saveAndFlush(existingGroup);
        }


        GroupEntity group = GroupEntity.builder()
                .idOfOwner(user.getId())
                .name(user.getUsername())
                .keyForConnection(appUser.hashCode() + "add+")
                .members(new ArrayList<>(Arrays.asList(user)))
                .build();

        groupRepository.save(group);
    }
}