package ru.kaplaan.consumer.service.metrics.impl

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.repository.consumer.vacancy.VacancyRepository
import ru.kaplaan.consumer.service.metrics.MetricService

@Service
class MetricServiceImpl(
    private val vacancyRepository: VacancyRepository
): MetricService {
    override fun countArchiveVacancies(): Long {
        return vacancyRepository.countArchiveVacancies()
    }

    override fun countNonArchiveVacancies(): Long {
        return vacancyRepository.countNonArchiveVacancies()
    }
}