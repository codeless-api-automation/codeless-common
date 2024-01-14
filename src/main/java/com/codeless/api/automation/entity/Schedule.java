package com.codeless.api.automation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "schedules")
@Getter
@Setter
public class Schedule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String name;
  @Column(nullable = false, unique = true)
  private String username;
  @Column(name = "uuid")
  private String uuid;
  @Column
  private String timer;
  @Column
  private String emails;
  @OneToOne
  @JoinColumn(name = "region", referencedColumnName = "id")
  private Region region;
  @Column(name = "test_id")
  private Long testId;
}
