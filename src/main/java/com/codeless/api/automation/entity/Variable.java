package com.codeless.api.automation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "variables")
@Getter
@Setter
public class Variable {

  @Id
  @GeneratedValue
  private Long id;
  @Column
  private String name;
  @Column
  private String value;
  @ManyToOne
  @JoinColumn(name = "environment", referencedColumnName = "id")
  private Environment environment;
}
