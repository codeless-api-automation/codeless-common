package com.codeless.api.automation.repository;

import com.codeless.api.automation.entity.Test;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

  Optional<Test> findByIdAndUsername(Long id, String username);
  void deleteByIdAndUsername(Long id, String username);
  Page<Test> findAllByUsername(String username, Pageable pageable);

}
