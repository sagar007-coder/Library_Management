package com.example.Library.Management.repositories;

import com.example.Library.Management.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository <Admin , Integer> {
}
