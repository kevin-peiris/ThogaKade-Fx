package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class Customer {
    private String id;
    private String title;
    private String name;
    private String address;
    private String number;
    private LocalDate dob;
}
