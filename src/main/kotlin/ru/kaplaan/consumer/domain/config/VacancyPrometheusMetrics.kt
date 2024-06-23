package ru.kaplaan.consumer.domain.config

import io.micrometer.core.instrument.MeterRegistry
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Scheduled
import ru.kaplaan.consumer.service.metrics.MetricService
import java.util.concurrent.atomic.AtomicLong

@Configuration
class VacancyPrometheusMetrics(
    meterRegistry: MeterRegistry,
    private val metricService: MetricService
) {


    private val log = LoggerFactory.getLogger(javaClass)

    private val countNonArchiveVacancies: AtomicLong =
        meterRegistry.gauge("non-archive-vacancies", AtomicLong(0))!!

    private val countArchiveVacancies: AtomicLong =
        meterRegistry.gauge("archive-vacancies", AtomicLong(0))!!


    @Scheduled(fixedDelay = 5000L, initialDelay = 0)
    fun scheduleCountNonArchiveVacancies(){
        countNonArchiveVacancies.set(metricService.countNonArchiveVacancies())
        log.debug("count non archive vacancies")
    }


    @Scheduled(fixedDelay = 5000L, initialDelay = 0)
    fun scheduleCountArchiveVacancies(){
        countArchiveVacancies.set(metricService.countArchiveVacancies())
        log.debug("count archive vacancies")
    }
}