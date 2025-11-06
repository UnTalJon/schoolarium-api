package xyz.xjnt.schoolarium_api.business.model

import jakarta.persistence.*
import xyz.xjnt.schoolarium_api.util.StudentId

@Entity
@Table(name = "students")
data class Student(
    @Id
    @StudentId
    val id: String? = null,

    @Column(nullable = false)
    val grade: String,

    @Column(nullable = false)
    val section: String,

    @Column(nullable = false)
    val program: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "student_id")
    val person: Person,
)
