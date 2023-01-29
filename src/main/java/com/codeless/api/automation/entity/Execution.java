package com.codeless.api.automation.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "executions")
@Getter
@Setter
public class Execution extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
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
  @Column(name = "test_id")
  private Long testId;
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "result", referencedColumnName = "id")
  private Result result;
}
