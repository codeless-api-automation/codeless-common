package com.codeless.api.automation.repository;

import com.codeless.api.automation.entity.Execution;
import com.codeless.api.automation.entity.ExecutionStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutionRepository extends JpaRepository<Execution, Long> {

  List<Execution> findAllByTestIdAndUsernameAndStatusIn(Long testId, String username, List<ExecutionStatus> statusList);
}
