package ru.kaplaan.consumer.web.controller.metrics

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kaplaan.consumer.service.metrics.MetricService

@RestController
@RequestMapping("/metrics")
class MetricController(
    private val metricService: MetricService
) {

    @GetMapping("/non-archive-vacancies")
    fun countNonArchiveVacancies(): Long{
        return metricService.countNonArchiveVacancies()
    }

    @GetMapping("/archive-vacancies")
    fun countArchiveVacancies(): Long{
        return metricService.countArchiveVacancies()
    }
}