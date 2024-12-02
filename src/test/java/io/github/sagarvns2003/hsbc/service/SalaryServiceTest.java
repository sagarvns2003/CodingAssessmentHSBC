package io.github.sagarvns2003.hsbc.service;

import static org.junit.jupiter.api.Assertions.*;

import io.github.sagarvns2003.hsbc.model.Employee;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.regex.Pattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SalaryServiceTest {

  private SalaryService salaryService;
  private List<Employee> employees;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  public void setUp() {
    salaryService = new SalaryService();
    employees = new ArrayList<>();
    employees.add(new Employee("Ashish", "A", "IT", "Pune", "Software Engineer", 10000d));
    employees.add(new Employee("Amit", "R", "HR", "Pune", "Recruiter", 12000d));
    employees.add(new Employee("Ramesh", "D", "HR", "Pune", "Senior Recruiter", 14000d));
    employees.add(new Employee("Jaya", "S", "IT", "Pune", "Tech Lead", 15000d));
    employees.add(new Employee("Smita", "M", "IT", "Bangalore", "Recruiter", 16000d));
    employees.add(new Employee("Umesh", "A", "IT", "Bangalore", "Software Engineer", 12000d));
    employees.add(new Employee("Pooja", "R", "HR", "Bangalore", "Software Engineer", 12000d));
    employees.add(new Employee("Ramesh", "D", "HR", "Pune", "Recruiter", 16000d));
    employees.add(new Employee("Bobby", "S", "IT", "Bangalore", "Tech Lead", 20000d));
    employees.add(new Employee("Vipul", "M", "IT", "Bangalore", "Software Engineer", 14000d));
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  void testFindAverageSalary() {
    Map<String, Map<String, Double>> averageSalaries = salaryService.findAverageSalary(employees);
    assertNotNull(averageSalaries);
    assertEquals(2, averageSalaries.size());
    assertTrue(averageSalaries.get("Pune").containsKey("Software Engineer"));
    assertTrue(averageSalaries.get("Bangalore").containsKey("Software Engineer"));
  }

  @Test
  void testPrintAverageSalaries() {
    salaryService.printAverageSalaries(salaryService.findAverageSalary(employees));

    assertEquals(4, countOccurrences(outContent.toString(), "Pune"));
    assertEquals(3, countOccurrences(outContent.toString(), "Bangalore"));

    assertTrue(outContent.toString().contains("Pune --> Senior Recruiter --> 14000"));
    assertTrue(outContent.toString().contains("Pune --> Tech Lead --> 15000"));
    assertTrue(outContent.toString().contains("Pune --> Software Engineer --> 10000"));
    assertTrue(outContent.toString().contains("Pune --> Recruiter --> 14000"));
    assertTrue(outContent.toString().contains("Bangalore --> Tech Lead --> 20000"));
    assertTrue(outContent.toString().contains("Bangalore --> Software Engineer --> 12666.67"));
    assertTrue(outContent.toString().contains("Bangalore --> Recruiter --> 16000"));
  }

  private static long countOccurrences(String source, String find) {
    return Pattern.compile(find) // Pattern
        .matcher(source) // Mather
        .results() // Stream<MatchResults>
        .count();
  }
}
