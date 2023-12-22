package com.example.Library.Management.services;


import com.example.Library.Management.models.Admin;
import com.example.Library.Management.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public void create(Admin admin){
        adminRepository.save(admin);
    }

    public Admin find(Integer adminID){
       return adminRepository.findById(adminID).orElse(null);

    }
}
