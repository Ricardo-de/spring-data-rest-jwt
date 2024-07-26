// src/main/java/com/api/productos/mypackages/models/LoginRequest.java
package com.api.productos.mypackages.models.request.login;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class LoginRequest {
    private String usuario;
    private String contrasenia;
}