package com.example.tgdt.Adapter;

import com.example.tgdt.Model.Employee;

public class EmployeeDataUtils {

    public static Employee[] getEmployees()  {
        Employee emp1 = new Employee("Điện thoại");
        Employee emp2 = new Employee("Laptop");
        Employee emp3 = new Employee("Đồng hồ");
        Employee emp4 = new Employee("Phụ kiện");

        return new Employee[] {emp1, emp2, emp3, emp4};
    }

}
