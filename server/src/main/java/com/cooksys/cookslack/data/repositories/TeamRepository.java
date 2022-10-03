package com.cooksys.cookslack.data.repositories;

import com.cooksys.cookslack.data.model.entities.Company;
import com.cooksys.cookslack.data.model.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Boolean existsByNameAndCompanyAndDeletedFalse(String name, Company company);

    List<Team> findAllByCompanyAndDeletedFalse(Company company);


}
