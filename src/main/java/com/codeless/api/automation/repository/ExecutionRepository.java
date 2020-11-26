package com.codeless.api.automation.repository;

import com.codeless.api.automation.entity.Execution;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutionRepository extends JpaRepository<Execution, Long> {

  Optional<Execution> findByExecutionId(Long executionId);
}
