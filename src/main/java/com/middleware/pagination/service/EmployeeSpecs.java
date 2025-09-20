package com.middleware.pagination.service;

import com.middleware.pagination.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecs {

    public static Specification<Employee> nameOrDepartment(String q) {
        if (q == null || q.isEmpty()) {
            return null;
        }
        String like = "%" + q.toLowerCase() + "%";
        return (root, cq, cb) -> cb.or(
                cb.like(cb.lower(root.get("name")), like),
                cb.like(cb.lower(root.get("department")), like)
        );
    }

    public static Specification<Employee> departmentEq(String dept){
        if (dept==null || dept.isEmpty()){
            return null;
        }
        return (root,cq,cb)->cb.equal(root.get("department"),dept);
    }

    public static Specification<Employee> salaryBetween(double min, double max){
        if (min==0.0 && max==0.0){
           return null;
        }
        if (min!=0.0 && max!=0.0){
            return (root,cq,cb)->cb.between(root.get("salary"),min,max);
        }
        if (min!=0.0){
            return (root,cq,cb)->cb.ge(root.get("salary"),min);
        }
        return (root, cq,cb)->cb.le(root.get("salary"),max);
    }

}
