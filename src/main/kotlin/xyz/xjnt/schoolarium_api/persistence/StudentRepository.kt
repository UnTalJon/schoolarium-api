package xyz.xjnt.schoolarium_api.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import xyz.xjnt.schoolarium_api.business.model.Student

@Repository
interface StudentRepository : JpaRepository<Student, String>