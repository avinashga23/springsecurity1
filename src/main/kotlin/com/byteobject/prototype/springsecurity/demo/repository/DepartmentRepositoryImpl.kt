package com.byteobject.prototype.springsecurity.demo.repository

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.HashMap

@Repository("departmentRepository")
open class DepartmentRepositoryImpl: DepartmentRepository {

    companion object {
        private var id = 0
        private val departmentMap = HashMap<Int, DepartmentDO>();
        private val LOGGER = LoggerFactory.getLogger(DepartmentRepositoryImpl::class.java)
    }

    override fun getDepartmentById(id: Int): Optional<DepartmentDO> {
        LOGGER.info("getting department by id $id")
        return Optional.ofNullable(departmentMap[id])
    }

    override fun getAllDepartments(): MutableList<DepartmentDO> {
        LOGGER.info("getting all departments")
        return departmentMap.values.stream().collect(Collectors.toList())
    }

    override fun createDepartment(departmentDO: DepartmentDO?): DepartmentDO {
        LOGGER.info("create department")
        departmentDO?.id = ++id
        departmentMap[id] = departmentDO!!
        return departmentDO
    }

    override fun updateDepartment(departmentDO: DepartmentDO?): DepartmentDO {
        LOGGER.info("update department")
        departmentMap[departmentDO?.id!!] = departmentDO!!

        return departmentDO
    }

    override fun deleteDepartmentById(id: Int) {
        LOGGER.info("delete department by id $id")
        departmentMap.remove(id)
    }

}