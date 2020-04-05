package com.example.fady.uspets.PersonalAdModule

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementFragment.ADVERTISEMENT_KEY
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementModel
import com.example.fady.uspets.MainScreenModule.MainScreenActivity
import com.example.fady.uspets.R
import com.example.fady.uspets.USPetsMain.UsPetsMainView

import com.example.imagepickercomp.IPickMedia
import com.example.imagepickercomp.PickMediaView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_personal_ad.*
import kotlinx.android.synthetic.main.fragment_create_ad.*
import kotlinx.android.synthetic.main.fragment_create_ad.pickMediaView
import java.util.ArrayList
import javax.inject.Inject

class PersonalAdActivity : UsPetsMainView(), IPersonalAd.Iview {
    //    private lateinit var binding: ActivityPersonalAdBinding
    public var advertismentModel: AdvertisementModel? = null
    @Inject
    lateinit var personalAdPresenter: PersonalAdPresenter
    var myImagesList = arrayListOf<String>();

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityPersonalAdBinding.inflate(layoutInflater)

        // injecting dagger
        var controllerComponent = initDaggerController(this, null)
        controllerComponent.inject(this)

        advertismentModel = intent.getParcelableExtra(ADVERTISEMENT_KEY)
        setContentView(R.layout.activity_personal_ad)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // set presenter Ad Model from the Activity extra
        personalAdPresenter.setAdvetismentModel(advertismentModel)


        pickMediaView.handlePickMedia(
        ) {
            var intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PickMediaView.PICK_IMAGE_CODE)
        }
        etmyPetName.setText(advertismentModel?.name)

        for (i in advertismentModel?.petImageArrayList!!.withIndex()) {
            myImagesList.add(i.value)
            
        }
        pickMediaView.imagesArrayList = myImagesList



        etPetGender.setText(advertismentModel?.gender)
        etmyPetPrice.setText(advertismentModel?.price)
        etmyPetDesc.setText(advertismentModel?.description)
        updateAd.setOnClickListener() {
            showProgressView()
            var modifedAdvertisment = AdvertisementModel()
            modifedAdvertisment.petImageArrayList = pickMediaView.imagesArrayList
            modifedAdvertisment.description = etmyPetDesc.text.toString()
            modifedAdvertisment.price = etmyPetPrice.text.toString()
            personalAdPresenter.onUpdateClick(modifedAdvertisment)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PickMediaView.PICK_IMAGE_CODE -> {
                    pickMediaView.setDataFromOnResult(data?.data.toString())
                }
            }
        }
    }

    override fun onImageError(error: String?, originalImageList: ArrayList<String>?) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        dismisProgressView()
        pickMediaView.imagesArrayList = originalImageList
    }

    override fun onPriceError(error: String?, originalPrice: String?) {
        dismisProgressView()
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        etmyPetPrice.setText(originalPrice)
    }

    override fun onUpdateSuccess() {
        dismisProgressView()
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
        val intent = Intent(this, MainScreenActivity::class.java)
        startActivity(intent)
    }

    override fun onUpdateFailed(message: String?) {
        dismisProgressView()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDescError(descError: String?, originalDesc: String?) {
        dismisProgressView()
        Toast.makeText(this, descError, Toast.LENGTH_LONG).show()
        etmyPetDesc.setText(originalDesc)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
