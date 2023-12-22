package com.example.Library.Management.controllers;


import com.example.Library.Management.dtos.CreateAdminRequest;
import com.example.Library.Management.services.AdminService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController

{
    @Autowired
    AdminService adminService;
    @PostMapping("/admin")
    public void createAdmin(@RequestBody CreateAdminRequest createAdminRequest){
        adminService.create(createAdminRequest.to());
    }

}
