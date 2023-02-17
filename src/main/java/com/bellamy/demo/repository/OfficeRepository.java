package com.bellamy.demo.repository;

import com.bellamy.demo.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfficeRepository extends JpaRepository<Office, UUID> {
    //List<Employee> findEmployees(Office office);
}
