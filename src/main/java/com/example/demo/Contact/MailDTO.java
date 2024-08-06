package com.example.demo.Contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MailDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("grade")
    private int grade;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("message")
    private String message;
}
