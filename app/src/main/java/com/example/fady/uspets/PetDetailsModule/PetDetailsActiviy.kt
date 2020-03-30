package com.example.fady.uspets.PetDetailsModule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementFragment
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementModel
import com.example.fady.uspets.Owner
import com.example.fady.uspets.R
import com.example.fady.uspets.SliderAdapterExample
import com.example.fady.uspets.USPetsMain.UsPetsMainView
import com.example.imagepickercomp.IPickMedia
import com.example.imagepickercomp.PickMediaView
import com.squareup.picasso.Picasso
import  kotlinx.android.synthetic.main.activity_pet_details_activiy.*
import javax.inject.Inject

class PetDetailsActiviy : UsPetsMainView(), IpetDetails.Iview {

    @Inject
    lateinit var ipresenter: PetDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_details_activiy)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.app_title)



        // initializing dagger
        var controllerComponent = initDaggerController(this, null)
        controllerComponent.inject(this)

        // get current pet from intent extra
        var currentPet = intent.getParcelableExtra<AdvertisementModel>(AdvertisementFragment.ADVERTISEMENT_KEY)
        if (currentPet is AdvertisementModel)
            phpetbackground(currentPet)


    }

    fun phpetbackground(currentPet: AdvertisementModel) {
        var ageTitle: String = when (currentPet.age.toIntOrNull()) {
            1 -> " Month "
            else -> " Months "
        }
        ipresenter.getPetOwnerInfo(currentPet.ownerUid)
        tvPetName.setText(currentPet.name + " ")
        tvPetAge.setText(currentPet.age + ageTitle)
        tvPetDesc.setText(currentPet.description + " ")
        tvPetPrice.setText(currentPet.price + "$ ")
        tvPetGender.setText(currentPet.gender + " ")


        val sliderAdapter = SliderAdapterExample(this, currentPet.petImageArrayList)
        imgPetPicSlider.setSliderAdapter(sliderAdapter)
    }

    override fun onshowOwnerInfo(owner: Owner?) {
        tvOwnerName.setText(owner?.getoName() + " ")
        rbOwnerRate.rating = owner?.getoRating()?.toFloat() ?: 5.0F
    }

}
