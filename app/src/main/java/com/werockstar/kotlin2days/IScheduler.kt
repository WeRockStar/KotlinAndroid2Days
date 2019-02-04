package com.werockstar.kotlin2days

interface Scheduler {
    fun io(): Scheduler
    fun ui(): Scheduler
}