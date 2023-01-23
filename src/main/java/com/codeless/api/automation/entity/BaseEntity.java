package com.codeless.api.automation.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

  @CreationTimestamp
  @Column(name = "created")
  protected LocalDateTime created;

  @UpdateTimestamp
  @Column(name = "last_modified")
  protected LocalDateTime lastModified;
}
