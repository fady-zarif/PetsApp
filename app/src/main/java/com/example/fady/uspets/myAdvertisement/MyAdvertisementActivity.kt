package com.example.fady.uspets.myAdvertisement

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementAdapter
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementFragment.ADVERTISEMENT_KEY
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementModel
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.IAdvertisement
import com.example.fady.uspets.PersonalAdModule.PersonalAdActivity
import com.example.fady.uspets.R
import com.example.fady.uspets.USPetsMain.UsPetsMainView
import kotlinx.android.synthetic.main.activity_my_advertisement.*
import javax.inject.Inject

class MyAdvertisementActivity : UsPetsMainView(), SwipeRefreshLayout.OnRefreshListener, IMyAdvertisements.Iview, IAdvertisement.IAdvertismentClick {
    private lateinit var recyclerviewAdapter: AdvertisementAdapter
    private lateinit var adsList: ArrayList<AdvertisementModel>

    @Inject
    lateinit var myAdvertisementsPresenter: MyAdvertisementPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_advertisement)


        var controllerComponent = initDaggerController(this, null)
        controllerComponent.inject(this)



        swiperefreshMyAds.setOnRefreshListener(this)

        adsList = ArrayList()
        rvMyAds.layoutManager = LinearLayoutManager(this)
        recyclerviewAdapter = AdvertisementAdapter(adsList, this)
        rvMyAds.adapter = recyclerviewAdapter
        myAdvertisementsPresenter.getMyAdvertisement()


    }


    override fun onAdvertismentClicListner(pos: Int) {
        val intent = Intent(this, PersonalAdActivity::class.java)
        intent.putExtra(ADVERTISEMENT_KEY, adsList[pos])
        startActivity(intent)
    }

    override fun onGetAdvertisementsSuccess(advertismentModel: AdvertisementModel) {
        cancelRefreshing()
        adsList.add(0, advertismentModel)
        recyclerviewAdapter.notifyDataSetChanged()
    }

    override fun onGetAdvertisementsFailed(message: String) {
        cancelRefreshing()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun cancelRefreshing() {
        if (swiperefreshMyAds.isRefreshing)
            swiperefreshMyAds.isRefreshing = false
    }

    override fun onRefresh() {
        swiperefreshMyAds.isRefreshing = true
        adsList.clear()
        recyclerviewAdapter.notifyDataSetChanged()
        myAdvertisementsPresenter.getMyAdvertisement()
    }

}
