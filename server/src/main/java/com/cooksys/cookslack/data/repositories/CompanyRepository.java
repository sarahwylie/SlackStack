package com.cooksys.cookslack.data.repositories;

import com.cooksys.cookslack.data.model.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Boolean existsByNameAndDeletedFalse(String name);

    List<Company> findAllByDeletedFalse();
    Optional<Company> findByDeletedFalse();

}
