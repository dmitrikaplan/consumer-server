package ru.kaplaan.consumer.service.metrics

import org.springframework.stereotype.Service

@Service
interface MetricService {

    fun countNonArchiveVacancies(): Long

    fun countArchiveVacancies(): Long
}