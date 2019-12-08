package com.example.rxjavaretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxjavaretrofit.adapter.CryptoAdapter
import com.example.rxjavaretrofit.data.ApiInterface
import com.example.rxjavaretrofit.data.model.Crypto
import com.example.rxjavaretrofit.utils.Contstants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), CryptoAdapter.ClickListener {

    private var mCompositeDisposable: CompositeDisposable? = null
    private var cryptoAdapter: CryptoAdapter? = null
    private var cryptoList: List<Crypto> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mCompositeDisposable = CompositeDisposable()
        rv_cryptoList.layoutManager = LinearLayoutManager(this)
        loadData()
    }

    private fun loadData() {
        val requestInterface = Retrofit.Builder()
            .baseUrl(Contstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiInterface::class.java)

        mCompositeDisposable?.add(requestInterface.getData(Contstants.API_KEY)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse))
    }

    private fun handleResponse(cryptoList: List<Crypto>) {
        this.cryptoList = cryptoList
        rv_cryptoList.setHasFixedSize(true)
        cryptoAdapter = CryptoAdapter(this)
        cryptoAdapter?.setCryptoList(this.cryptoList)
        cryptoAdapter?.setClickListener(this)
        rv_cryptoList.adapter = cryptoAdapter
    }

    override fun onClick(position: Int) {
        Contstants.showToast(this, position)
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }
}
