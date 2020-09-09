package com.byteobject.prototype.springsecurity.demo.repository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {

    public static final String DEPARTMENT_BY_ID_NOT_FOUND = "department_by_id_not_found";

    public Optional<DepartmentDO> getDepartmentById(int id);

    public List<DepartmentDO> getAllDepartments();

    public DepartmentDO createDepartment(DepartmentDO departmentDO);

    public DepartmentDO updateDepartment(DepartmentDO departmentDO);

    public void deleteDepartmentById(int id);

}
