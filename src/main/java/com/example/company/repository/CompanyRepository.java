package com.example.company.repository;

import com.example.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company , Long> {

    @Query(value = "select * from company where name like ?1",
            nativeQuery = true)
    List<Company> findCompaniesByNameContaining(String name);
}
