package com.codeless.api.automation.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
  @GeneratedValue
  private Long id;
  @Column
  private String name;
  @Column(name = "internal_name")
  private String internalName;
  @Lob
  @Column
  private String timer;
  @Lob
  @Column
  private String emails;
  @OneToOne
  @JoinColumn(name = "region", referencedColumnName = "id")
  private Region region;
  @ManyToMany
  private Set<Test> tests;
}
