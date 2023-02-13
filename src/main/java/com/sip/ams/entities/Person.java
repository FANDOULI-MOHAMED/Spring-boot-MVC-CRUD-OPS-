package com.sip.ams.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Person {
    private int id;
    private String firstName;
    private String lastName;

}
