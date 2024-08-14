package model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    private String id;
    private String title;
    private String name;
    private String address;
    private String number;
    private LocalDate dob;


}
