package com.codeless.api.automation.repository;

import com.codeless.api.automation.entity.Execution;
import com.codeless.api.automation.entity.ExecutionStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutionRepository extends JpaRepository<Execution, Long> {

  Optional<Execution> findByIdAndUsername(Long id, String username);
  Page<Execution> findAllByUsername(String username, Pageable pageable);
  List<Execution> findAllByTestIdAndUsernameAndStatusIn(Long id, String username, List<ExecutionStatus> statusList);
}
