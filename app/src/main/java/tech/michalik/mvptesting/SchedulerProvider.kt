package tech.michalik.mvptesting

import io.reactivex.Scheduler

interface SchedulerProvider {
    val main: Scheduler
    val io: Scheduler
}