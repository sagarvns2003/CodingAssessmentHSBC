package io.github.sagarvns2003.hsbc.service;

import io.github.sagarvns2003.hsbc.model.Employee;
import io.github.sagarvns2003.hsbc.util.FormatDecimalNumber;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Vidya Sagar Gupta
 */
public class SalaryService {
  private final FormatDecimalNumber formatDecimalNumber = new FormatDecimalNumber();

  /*
   * Find average salary in each OfficeLocation for all common Designation for given list of employees
   */
  public Map<String, Map<String, Double>> findAverageSalary(List<Employee> employees) {
    return employees.parallelStream()
        .filter(Objects::nonNull)
        .collect(
            Collectors.groupingBy(
                Employee::getOfficeLocation,
                Collectors.groupingBy(
                    Employee::getDesignation, Collectors.averagingDouble(Employee::getSalary))));
  }

  /*
   * Print average salary by officeLocation and designation
   */
  public void printAverageSalaries(Map<String, Map<String, Double>> averageSalaries) {
    if (null == averageSalaries || averageSalaries.isEmpty()) return;
    averageSalaries.forEach(
        (location, designations) -> {
          designations.forEach(
              (designation, avgSalary) -> {
                System.out.println(
                    location
                        + " --> "
                        + designation
                        + " --> "
                        + formatDecimalNumber.formatNow(avgSalary));
              });
        });
  }
}
