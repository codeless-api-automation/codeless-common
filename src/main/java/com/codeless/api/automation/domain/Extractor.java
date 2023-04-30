package com.codeless.api.automation.domain;

import java.util.List;
import lombok.Data;

@Data
public class Extractor {

  private String dslName;
  private List<Attribute> inputFields;
}
