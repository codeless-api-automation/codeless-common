package com.codeless.api.automation.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Data
public abstract class BaseEntity {

  @Id
  @GeneratedValue
  protected Long id;

  @CreationTimestamp
  @Column(name = "created")
  protected LocalDateTime created;

  @UpdateTimestamp
  @Column(name = "last_modified")
  protected LocalDateTime lastModified;
}
