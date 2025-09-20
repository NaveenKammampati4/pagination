package com.middleware.pagination.controller;

import com.middleware.pagination.entity.Employee;
import com.middleware.pagination.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.fetchAllEmployees());
    }

    @GetMapping("/sort/{field}")
    public ResponseEntity<List<Employee>> fetchByField(@PathVariable String field){
        return ResponseEntity.ok(employeeService.sortBasedUpOnField(field));
    }


    @GetMapping("/pagination/{offSet}/{pageSize}")
    public ResponseEntity<Page<Employee>> getEmployees(@PathVariable int offSet, @PathVariable int pageSize){
        return ResponseEntity.ok(employeeService.getEmployeesWithPagination(offSet,pageSize));
    }

    @GetMapping("/paginationAndSorting/{offSet}/{pageSize}/{field}")
    public ResponseEntity<Page<Employee>> getEmployeesAndSorting(@PathVariable int offSet, @PathVariable int pageSize, @PathVariable String field){
        return ResponseEntity.ok(employeeService.getEmployeesWithPaginationAndSorting(offSet,pageSize,field));
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<Page<Employee>> getAllEmployeeWithPagination(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int pageSize,
                                                                       @RequestParam(defaultValue = "id") String sortBy,
                                                                       @RequestParam(defaultValue = "true") boolean ascending
                                                                       ){
        Sort sort=ascending ?Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page, pageSize,sort);
        return ResponseEntity.ok(employeeService.getAllEmployeesWithPagination(pageable));
    }
}
