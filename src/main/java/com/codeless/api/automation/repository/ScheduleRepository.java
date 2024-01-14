package com.codeless.api.automation.repository;

import com.codeless.api.automation.entity.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

  List<Schedule> findAllByTestIdAndUsername(Long testId, String username);

}