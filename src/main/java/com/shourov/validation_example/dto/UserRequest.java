package com.shourov.validation_example.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserRequest {
    private int age;
    @NotNull(message = "Name should not be null.")
    private String name;
    @Email(message = "Invalid email format.")
    private String email;
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be ten digits.")
    private String mobile;
    @Min(value = 18, message = "Age below 18 is not permitted.")
    @Max(value = 70, message = "Age above 70 is not permitted.")
    private String gender;
    @NotBlank
    private String nationality;
}
