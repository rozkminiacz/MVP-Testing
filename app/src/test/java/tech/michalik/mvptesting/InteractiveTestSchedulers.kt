package tech.michalik.mvptesting

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class InteractiveTestSchedulers(private val testScheduler: TestScheduler) :
    SchedulerProvider {
    override val main: Scheduler
        get() = testScheduler
    override val io: Scheduler
        get() = testScheduler
}