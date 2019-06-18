package com.codecool.advanced_project.credentials;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationCredentials {
    private String username;
    private String password;
    private String groupKey;
    private String email;
}

//Todo: extends instead of copying fields, conflict with Lombok