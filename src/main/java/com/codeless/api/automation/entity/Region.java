package com.codeless.api.automation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "regions")
@Getter
@Setter
public class Region {

  @Id
  @GeneratedValue
  private Long id;
  @Column(name = "country_id")
  private Long countryId;
  @Column
  private String city;
  @Column(name = "default_region")
  private boolean defaultRegion;
}
