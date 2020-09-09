package com.byteobject.prototype.springsecurity.demo.service

import com.byteobject.prototype.springsecurity.demo.repository.DepartmentDO
import com.byteobject.prototype.springsecurity.demo.repository.DepartmentNotFoundException
import com.byteobject.prototype.springsecurity.demo.repository.DepartmentRepository
import com.github.dozermapper.core.Mapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service("departmentService")
class DepartmentServiceImpl(private val departmentRepository: DepartmentRepository, private val mapper: Mapper): DepartmentService {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl::class.java)
    }

    override fun getDepartmentById(id: Int): Optional<DepartmentBO> {
        LOGGER.info("get department by id $id")
        val departmentDO = departmentRepository.getDepartmentById(id)
        if (departmentDO.isEmpty)
            throw DepartmentNotFoundException(DepartmentRepository.DEPARTMENT_BY_ID_NOT_FOUND, listOf(id))
        return departmentDO.map { mapper.map(it, DepartmentBO::class.java) }
    }

    override fun getAllDepartments(): MutableList<DepartmentBO> {
        LOGGER.info("get all departments")
        return departmentRepository.allDepartments.stream().map { mapper.map(it, DepartmentBO::class.java) }
                .collect(Collectors.toList())
    }

    override fun createDepartment(departmentBO: DepartmentBO?): DepartmentBO {
        LOGGER.info("create department $departmentBO")
        return mapper.map(departmentRepository.createDepartment(mapper.map(departmentBO, DepartmentDO::class.java)),
                DepartmentBO::class.java)
    }

    override fun updateDepartment(departmentBO: DepartmentBO?): DepartmentBO {
        LOGGER.info("update department $departmentBO")
        if (departmentRepository.getDepartmentById(departmentBO!!.id).isEmpty)
            throw DepartmentNotFoundException(DepartmentRepository.DEPARTMENT_BY_ID_NOT_FOUND, listOf(departmentBO.id))
        return mapper.map(departmentRepository.updateDepartment(mapper.map(departmentBO, DepartmentDO::class.java)),
                DepartmentBO::class.java)
    }

    override fun deleteDepartmentById(id: Int) {
        LOGGER.info("delete department by id $id")
        departmentRepository.deleteDepartmentById(id)
    }

}