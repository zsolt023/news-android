package com.zsolt.news.internal.extensions

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//Observable
fun <T> Observable<T>.subscribeOnIo() = subscribeOn(Schedulers.io())

fun <T> Observable<T>.observeOnIo() = observeOn(Schedulers.io())

fun <T> Observable<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

fun <T> Observable<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun <T> Observable<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

//Flowable
fun <T> Flowable<T>.subscribeOnIo() = subscribeOn(Schedulers.io())

fun <T> Flowable<T>.observeOnIo() = observeOn(Schedulers.io())

fun <T> Flowable<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

fun <T> Flowable<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun <T> Flowable<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())


//Single
fun <T> Single<T>.subscribeOnIo() = subscribeOn(Schedulers.io())

fun <T> Single<T>.observeOnIo() = observeOn(Schedulers.io())

fun <T> Single<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

fun <T> Single<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun <T> Single<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())


//Maybe
fun <T> Maybe<T>.subscribeOnIo() = subscribeOn(Schedulers.io())

fun <T> Maybe<T>.observeOnIo() = observeOn(Schedulers.io())

fun <T> Maybe<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

fun <T> Maybe<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun <T> Maybe<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())


//Completable
fun Completable.subscribeOnIo() = subscribeOn(Schedulers.io())

fun Completable.observeOnIo() = observeOn(Schedulers.io())

fun Completable.applyIoScheduler() = applyScheduler(Schedulers.io())

fun Completable.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun Completable.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())