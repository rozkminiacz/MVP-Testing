package tech.michalik.mvptesting

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TrampolineTestSchedulers : SchedulerProvider {
    override val main: Scheduler
        get() = Schedulers.trampoline()
    override val io: Scheduler
        get() = Schedulers.trampoline()
}