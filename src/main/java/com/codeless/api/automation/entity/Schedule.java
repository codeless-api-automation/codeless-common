package com.codeless.api.automation.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "schedules")
@Data
public class Schedule {

  @Id
  @GeneratedValue
  private Long id;
  @Column
  private String name;
  @Column(name = "internal_name")
  private String internalName;
  @OneToOne
  @JoinColumn(name = "region", referencedColumnName = "id")
  private Region region;
  @ManyToMany(targetEntity = Test.class)
  @JoinTable(name = "tests_schedules",
      joinColumns = {@JoinColumn(name = "id")},
      inverseJoinColumns = {@JoinColumn(name = "test_id")})
  private List<Test> tests;
}
