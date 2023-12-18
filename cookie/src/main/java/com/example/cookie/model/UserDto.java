package com.example.cookie.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private String id;

    private String name;

    private String password;
}
