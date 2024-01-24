package com.codeless.api.automation.repository;

import com.codeless.api.automation.entity.Schedule;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

  Page<Schedule> findAllByUsername(String username, Pageable pageable);
  List<Schedule> findAllByTestIdAndUsername(Long testId, String username);

}