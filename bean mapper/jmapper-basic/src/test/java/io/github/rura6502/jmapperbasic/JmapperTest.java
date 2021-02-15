package io.github.rura6502.jmapperbasic;

import static org.assertj.core.api.Assertions.assertThat;

import com.googlecode.jmapper.JMapper;

import org.junit.jupiter.api.Test;

public class JmapperTest {

  @Test
  void test_jmapper_with_jmap() {

    JMapper<StudentDtoUsingJMap, Student> studentMapper
      = new JMapper<>(StudentDtoUsingJMap.class, Student.class);
    
    Student student = new Student(1l, "student01", 10, Clazz.A);
    StudentDtoUsingJMap studentDto = studentMapper.getDestination(student);

    assertThat(student.getName()).isEqualTo(studentDto.getName());
    assertThat(student.getAge() + "").isEqualTo(studentDto.getAge());
    assertThat(student.getAge() + "살").isEqualTo(studentDto.getAgeStr());
  }


  @Test
  void test_jmapper_with_extend() {
    JMapper<StudentDtoWithExtend, Student> studentMapper
      = new JMapper<>(StudentDtoWithExtend.class, Student.class);
    
    Student student = new Student(1l, "student01", 10, Clazz.A);
    StudentDtoWithExtend studentDto = studentMapper.getDestination(student);

    assertThat(student.getName()).isEqualTo(studentDto.getName());
    assertThat(student.getAge() + "").isEqualTo(studentDto.getAge());
    assertThat(student.getAge() + "살").isEqualTo(studentDto.getAgeStr());
  }


  @Test
  void test_jmapper_with_extend_and_jglobalmap() {
    JMapper<StudentDtoWithExtendAndJGlobalMap.WithId, Student> studentMapper
      = new JMapper<>(StudentDtoWithExtendAndJGlobalMap.WithId.class, Student.class);
    
    Student student = new Student(1l, "student01", 10, Clazz.A);
    StudentDtoWithExtendAndJGlobalMap.WithId studentDto = studentMapper.getDestination(student);

    assertThat(student.getName()).isEqualTo(studentDto.getName());
    assertThat(student.getAge()).isEqualTo(studentDto.getAge());
  }
  
}
