package com.codeless.api.automation.repository;

import com.codeless.api.automation.entity.Region;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

  Optional<Region> findByCity(String city);
}
