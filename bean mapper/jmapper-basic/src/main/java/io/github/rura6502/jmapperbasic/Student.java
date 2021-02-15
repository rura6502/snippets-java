package io.github.rura6502.jmapperbasic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
  
  private Long id;

  private String name;

  private int age;

  private Clazz clazz;
}
