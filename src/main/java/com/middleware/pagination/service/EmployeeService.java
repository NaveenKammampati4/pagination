package com.middleware.pagination.service;

import com.middleware.pagination.entity.Employee;
import com.middleware.pagination.repo.EmployeeRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public List<Employee> fetchAllEmployees(){
        return employeeRepo.findAll();
    }

    public List<Employee> sortBasedUpOnField(String field){
        return employeeRepo.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public Page<Employee> getEmployeesWithPagination(int offSet, int pageSize){
        return employeeRepo.findAll(PageRequest.of(offSet,pageSize));
    }

    public Page<Employee> getEmployeesWithPaginationAndSorting(int offSet, int pageSize, String field){
        return employeeRepo.findAll(PageRequest.of(offSet,pageSize).withSort(Sort.by(Sort.Direction.ASC, field)));
    }

    public Page<Employee> getAllEmployeesWithPagination(Pageable pageable){
        return employeeRepo.findAll(pageable);
    }

//    public Page<Employee> search(String q, String department, double minSalary, double maxSalary, int page, int size){
//        Specification<Employee> specification=Specification.where(EmployeeSpecs.nameOrDepartment(q))
//                .and(EmployeeSpecs.departmentEq(department));
//    }
}
