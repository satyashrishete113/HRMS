package com.company.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.employees.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
