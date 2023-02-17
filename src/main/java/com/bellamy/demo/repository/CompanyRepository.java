package com.bellamy.demo.repository;

import com.bellamy.demo.entity.Company;
import com.bellamy.demo.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    @Query("SELECT office FROM Office office WHERE office.company.id = :companyId")
    List<Office> findOfficesById(UUID companyId);

}
