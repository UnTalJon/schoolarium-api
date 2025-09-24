package xyz.xjnt.schoolarium_api.business.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "students")
data class Student(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val grade: String,

    val section: String,

    val program: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "student_id")
    val person: Person,
)
