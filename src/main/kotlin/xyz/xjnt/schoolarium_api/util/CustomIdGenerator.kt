package xyz.xjnt.schoolarium_api.util

import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicLong


@Component
class CustomIdGenerator(private val env: Environment) : IdentifierGenerator {
    companion object {
        private val sequence = AtomicLong(1)
    }

    override fun generate(session: SharedSessionContractImplementor?, `object`: Any?): Any {
        val currentYear = java.time.Year.now().value % 100
        val prefix = env.getProperty("app.school-code") ?: "STD"
        val sequenceValue = sequence.getAndIncrement()

        return "$currentYear$prefix${String.format("%05d", sequenceValue)}"
    }
}