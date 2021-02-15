package io.github.rura6502.jmapperbasic;

import com.googlecode.jmapper.annotations.JMap;
import com.googlecode.jmapper.annotations.JMapConversion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoUsingJMap {
  
  @JMap
  private Long id;

  @JMap
  private String name;

  @JMap
  private String age;

  @JMap("age")
  private String ageStr;

  @JMap("clazz")
  private String className;

  @JMapConversion(from={"clazz"}, to={"className"})
  public String conversionClazzToClassName(Clazz clazz) {
    return clazz.name();
  }

  @JMapConversion(from={"age"}, to={"ageStr"})
  public String conversionAgeToAgeStr(int age) {
    return String.format("%d살", age);
  }
}
