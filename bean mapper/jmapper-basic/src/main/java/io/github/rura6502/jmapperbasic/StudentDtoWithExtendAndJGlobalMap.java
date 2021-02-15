package io.github.rura6502.jmapperbasic;

import com.googlecode.jmapper.annotations.JGlobalMap;
import com.googlecode.jmapper.annotations.JMap;
import com.googlecode.jmapper.annotations.JMapConversion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JGlobalMap
public class StudentDtoWithExtendAndJGlobalMap {
  
  private String name;

  private int age;

  private  Clazz clazz;

  @Data
  @JGlobalMap
  @EqualsAndHashCode(callSuper = true)
  public static class WithId extends StudentDtoWithExtendAndJGlobalMap {
    private Long id;
  }
}
