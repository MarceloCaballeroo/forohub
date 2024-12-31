package com.example.forohub.controller;

import com.example.forohub.model.User;
import com.example.forohub.security.UserPassAuthToken;
import com.example.forohub.domain.user.DataAuthUser;
import com.example.forohub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity DataAuthUser(@RequestBody @Valid DataAuthUser datosAutenticacionUsuario) {
        Authentication authToken = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((User) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new UserPassAuthToken(JWTtoken));
    }
}