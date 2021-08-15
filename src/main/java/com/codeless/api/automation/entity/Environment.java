package com.codeless.api.automation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "environments")
@Getter
@Setter
public class Environment {

  @Id
  @GeneratedValue
  private Long id;
  @Column
  private String name;
}
