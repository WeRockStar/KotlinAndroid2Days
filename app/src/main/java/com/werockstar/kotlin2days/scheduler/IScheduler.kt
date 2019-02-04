package com.werockstar.kotlin2days.scheduler

import io.reactivex.Scheduler

interface IScheduler {
    fun io(): Scheduler
    fun ui(): Scheduler
}