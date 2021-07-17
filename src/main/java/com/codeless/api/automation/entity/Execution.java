package com.codeless.api.automation.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "executions")
@Data
public class Execution extends BaseEntity {

  @Column
  private String name;
  @Enumerated(EnumType.ORDINAL)
  @Column
  private ExecutionType type;
  @Enumerated(EnumType.ORDINAL)
  @Column
  private ExecutionStatus status;
  @Column(name = "schedule_id")
  private Long scheduleId;
  @OneToOne
  @JoinColumn(name = "region", referencedColumnName = "id")
  private Region region;
  @ManyToMany
  private Set<Test> tests;
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "result", referencedColumnName = "id")
  private Result result;
}
