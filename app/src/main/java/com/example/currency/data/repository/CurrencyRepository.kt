package com.example.currency.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.currency.data.db.CurrencyDatabase
import com.example.currency.data.model.APIResponse
import com.example.currency.data.model.CurrencyInfo
import com.example.currency.data.model.Status
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CurrencyRepository @Inject constructor(private val db: CurrencyDatabase) {
    val currencyList = MutableLiveData<APIResponse<List<CurrencyInfo>>>()
    private val disposable by lazy { CompositeDisposable() }

    private fun updateResponse(
        status: Status,
        data: List<CurrencyInfo>? = null,
        errorMessage: String? = null
    ) {
        val responseLoading = APIResponse(status, data, errorMessage)
        currencyList.postValue(responseLoading)
    }

    fun getCurrencyList() {
        val dbObservable = db.getCurrencyDao().getCurrencyList()

        observeData(dbObservable)
    }

    fun sort() {
        currencyList.value?.data?.let { data ->
            disposable.add(
                Observable.fromIterable(data).toSortedList { currencyInfo, currencyInfo2 ->
                    currencyInfo.name.compareTo(currencyInfo2.name)
                }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        updateResponse(Status.LOADING)
                    }
                    .subscribe(
                        {
                            updateResponse(Status.SUCCESS, it)
                        },
                        {
                            updateResponse(Status.ERROR, null, it.localizedMessage)
                        }

                    ))
        }
    }

    private fun observeData(db: Observable<List<CurrencyInfo>>) {
        disposable.add(
            db.filter { it.isNotEmpty() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    updateResponse(Status.LOADING)
                }
                .subscribe(
                    {
                        updateResponse(Status.SUCCESS, it)
                    },
                    {
                        updateResponse(Status.ERROR, null, it.localizedMessage)
                    }

                ))
    }

    fun clear() = disposable.clear()
}