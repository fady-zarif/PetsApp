package com.example.fady.uspets.PetDetailsModule

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementFragment
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementModel
import com.example.fady.uspets.Owner
import com.example.fady.uspets.PetDetailsModule.SendMessageDialogView.ISendMessage
import com.example.fady.uspets.R
import com.example.fady.uspets.SliderAdapterExample
import com.example.fady.uspets.USPetsMain.PetUiManager
import com.example.fady.uspets.USPetsMain.UsPetsMainView
import com.squareup.picasso.Picasso
import  kotlinx.android.synthetic.main.activity_pet_details_activiy.*
import javax.inject.Inject

class PetDetailsActiviy : UsPetsMainView(), IpetDetails.Iview {

    @Inject
    lateinit var ipresenter: PetDetailsPresenter
    private lateinit var sendMessageDialogView: SendMessageDialogView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_details_activiy)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.app_title)


        // initializing dagger
        val controllerComponent = initDaggerController(this, null)
        controllerComponent.inject(this)

        // get current pet from intent extra
        val currentPet = intent.getParcelableExtra<AdvertisementModel>(AdvertisementFragment.ADVERTISEMENT_KEY)
        if (currentPet is AdvertisementModel)
            setPetItem(currentPet)
        sendMessageDialogView = SendMessageDialogView(this, object : ISendMessage {
            override fun onSendMessage(message: String) {
                ipresenter.onSendMessageClick(message, currentPet!!.ownerUid)
            }
        })


//        floatingMenu.setOnMenuButtonClickListener {
//            floatingMenu.open(true)
//        }
    }
/*
    sendMessageDialogView = SendMessageDialogView(this, object : ISendMessage {
            override fun onSendMessage(message: String) {

            }
        })
*/

    @SuppressLint("SetTextI18n")
    fun setPetItem(currentPet: AdvertisementModel) {
        val ageTitle: String = when (currentPet.age.toIntOrNull()) {
            1 -> " Month "
            else -> " Months "
        }
        ipresenter.getPetOwnerInfo(currentPet.ownerUid)
        tvPetName.text = currentPet.name
        tvPetAge.text = currentPet.age + ageTitle
        tvPetDesc.text = currentPet.description
        tvPetPrice.text = currentPet.price + PetUiManager.getInstance().currencySymbol
        tvPetGender.text = currentPet.gender
        menu_item_message.setOnClickListener() {
            sendMessageDialogView.showDialog()
            menu_call_message.close(true)
        }
        menu_item_call.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + ipresenter.ownerPhoneNumber)
            startActivity(intent);
            menu_call_message.close(true)
        }
        val sliderAdapter = SliderAdapterExample(this, currentPet.petImageArrayList)
        imgPetPicSlider.setSliderAdapter(sliderAdapter)
    }

    override fun onshowOwnerInfo(owner: Owner?) {
        tvOwnerName.text = owner?.getoName()
        rbOwnerRate.rating = owner?.getoRating()?.toFloat() ?: 5.0F
    }

    override fun onshowError(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        sendMessageDialogView.dismiss()
    }

    override fun showOwnerPhoto(url: String) {
        PetUiManager.getInstance().setPicassoImage(url, cimgOwner)
    }

    override fun userHasNoNumber() {
        menu_item_call.visibility = View.GONE
    }

}
