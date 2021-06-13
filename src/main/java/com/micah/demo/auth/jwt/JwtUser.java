package com.micah.demo.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.MessageFormat;
import java.util.Date;

@AllArgsConstructor
@Data
public class JwtUser {
    private String username;
    private Date dateIssued;

    @Override
    public String toString() {
        return MessageFormat.format(
                "JwtUser(username={0}, dateIssued={1})",
                username,
                dateIssued
        );
    }
}
