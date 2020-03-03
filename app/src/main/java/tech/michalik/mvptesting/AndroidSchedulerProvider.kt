package tech.michalik.mvptesting

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AndroidSchedulerProvider : SchedulerProvider {
    override val main: Scheduler
        get() = AndroidSchedulers.mainThread()
    override val io: Scheduler
        get() = Schedulers.io()
}
