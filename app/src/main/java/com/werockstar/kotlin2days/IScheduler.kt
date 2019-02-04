package com.werockstar.kotlin2days

import io.reactivex.Scheduler

interface IScheduler {
    fun io(): Scheduler
    fun ui(): Scheduler
}