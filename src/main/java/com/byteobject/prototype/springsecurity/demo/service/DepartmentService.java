package com.byteobject.prototype.springsecurity.demo.service;

import com.byteobject.prototype.springsecurity.demo.repository.DepartmentDO;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    public static final String DEPARTMENT_BY_ID_NOT_FOUND = "department_by_id_not_found";

    public Optional<DepartmentBO> getDepartmentById(int id);

    public List<DepartmentBO> getAllDepartments();

    public DepartmentBO createDepartment(DepartmentBO departmentBO);

    public DepartmentBO updateDepartment(DepartmentBO departmentBO);

    public void deleteDepartmentById(int id);
}
