package com.vseti.processor.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private String email = "";
    private String login = "";
    private String password = "";
    private String role = Role.USER.name();
    private String surname = "";
    private String name = "";
    private String patronymic = "";
    private int age = 0;
}
