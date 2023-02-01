package com.celalgundogdu.organizationservice.repository;

import com.celalgundogdu.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByOrganizationCode(String organizationCode);

    boolean existsByOrganizationCode(String organizationCode);
}
