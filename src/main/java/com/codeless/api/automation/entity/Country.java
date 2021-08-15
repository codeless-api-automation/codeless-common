package com.codeless.api.automation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "countries")
@Getter
@Setter
public class Country {

  @Id
  @GeneratedValue
  private Long id;
  @Column
  private String iso2;
  @Column(name = "display_name")
  private String displayName;
}
