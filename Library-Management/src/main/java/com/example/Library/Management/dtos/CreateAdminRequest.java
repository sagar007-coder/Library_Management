package com.example.Library.Management.dtos;


import com.example.Library.Management.models.Admin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAdminRequest {


    @NotBlank
    private String name;

    @NotBlank
    private String email;

    public Admin to(){

        return Admin.builder()
                .name(this.name)
                .email((this.email))
                .build();
    }
}
