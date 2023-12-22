package com.example.Library.Management.dtos;


import com.example.Library.Management.models.Student;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    private Integer age;


    public Student to(){
        return  Student.builder()
                .name(this.name)
                .email(this.email)
                .age(this.age)
                .build();
    }



}
