package com.byteobject.prototype.springsecurity.demo.controller;

import com.byteobject.prototype.springsecurity.demo.service.DepartmentBO;
import com.byteobject.prototype.springsecurity.demo.service.DepartmentService;
import com.github.dozermapper.core.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController("departmentController")
@RequestMapping("/api/v1/department")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;

    private final Mapper mapper;

    @Autowired
    public DepartmentController(DepartmentService departmentService, Mapper mapper) {
        this.departmentService = departmentService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('department:read', 'department:read_write')")
    public DepartmentDTO getDepartmentById(@PathVariable("id") int id) {
        LOGGER.info("get department by id {}", id);
        return departmentService.getDepartmentById(id).map(it -> mapper.map(it, DepartmentDTO.class)).get();
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('department:read', 'department:read_write')")
    public List<DepartmentDTO> getAllDepartments() {
        LOGGER.info("get all departments");
        return departmentService.getAllDepartments().stream().map(it -> mapper.map(it, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('department:read_write')")
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO, UriComponentsBuilder uriComponentsBuilder) {
        LOGGER.info("create department");
        DepartmentDTO created = mapper.map(departmentService.createDepartment(mapper.map(departmentDTO, DepartmentBO.class))
                , DepartmentDTO.class);
        UriComponents uri = uriComponentsBuilder.path("/api/v1/department/{id}").buildAndExpand(created.getId());
        return ResponseEntity.created(uri.toUri()).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('department:read_write')")
    public DepartmentDTO updateDepartment(@PathVariable("id") int id, @RequestBody DepartmentDTO departmentDTO) {
        LOGGER.info("update department");
        departmentDTO.setId(id);
        return mapper.map(departmentService.updateDepartment(mapper.map(departmentDTO, DepartmentBO.class)), DepartmentDTO.class);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('department:read_write')")
    public void deleteDepartment(@PathVariable("id") int id) {
        LOGGER.info("delete department");
        departmentService.deleteDepartmentById(id);
    }

}
