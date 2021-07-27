package com.codeless.api.automation.repository;

import com.codeless.api.automation.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  User findByUsername(String name);
  User findByUuidAndUsername(String uuid, String name);
}
