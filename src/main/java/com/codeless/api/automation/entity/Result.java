package com.codeless.api.automation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "results")
@Data
public class Result {

  @Id
  @GeneratedValue
  private Long id;
  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false)
  private TestStatus status;
  @Lob
  @Column(nullable = false)
  private String logs;
}
