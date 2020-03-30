package com.example.fady.uspets.MainScreenModule.CreateAdModule;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fady.uspets.ControllerDI.ControllerComponent;
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementModel;
import com.example.fady.uspets.MainScreenModule.MainScreenActivity;
import com.example.fady.uspets.R;
import com.example.imagepickercomp.PickMediaView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.example.fady.uspets.MainScreenModule.CreateAdModule.CreateAdConstant.PET_PIC_RESULT_CODE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAdFragment extends Fragment implements ICreateAd.IView {

    @BindView(R.id.etPetName)
    EditText etPetName;
    @BindView(R.id.etPetAge)
    EditText etPetAge;
    @BindView(R.id.etPetPrice)
    EditText etPetPrice;
    @BindView(R.id.etPetDesc)
    EditText etPetDesc;

    @BindView(R.id.spPetType)
    Spinner spPetType;

    @BindView(R.id.spPetGender)
    Spinner spPetGender;

    @BindView(R.id.imgPetPic)
    ImageView imgPetPic;

    @BindView(R.id.btnImgSelect)
    ImageButton btnImgSelect;

    @BindView(R.id.btnShareAd)
    Button btnShareAd;
    @BindView(R.id.pickMediaView)
    PickMediaView pickMediaView;

    @Inject
    CreateAdPresenter iPresenter;

    Bitmap bitmap = null;
    //    ArrayList<String> imageName = "";
    ICreateAdwithActivityHolder iCreateAdwithActivityHolder;
    ControllerComponent controllerComponent;

    public CreateAdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_ad, container, false);
        ButterKnife.bind(this, view);

        controllerComponent = ((MainScreenActivity) getActivity()).initDaggerController(null, this);
        controllerComponent.inject(this);
        iCreateAdwithActivityHolder = ((MainScreenActivity) getActivity());

        spPetType.setAdapter(new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.pet_type)));
        spPetGender.setAdapter(new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.pet_gender)));

        pickMediaView.handlePickMedia( () -> {

            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, PickMediaView.PICK_IMAGE_CODE);

        });
        return view;
    }

    @OnClick(R.id.btnImgSelect)
    public void selectImageBtnClick() {
        if (iPresenter.isStoragePermissionGranted(getActivity())) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PET_PIC_RESULT_CODE);
        }
    }

    @OnClick(R.id.btnShareAd)
    public void shareAdBtnClick() {
        AdvertisementModel advertisementModel = new AdvertisementModel(
                etPetName.getText().toString(),
                spPetType.getSelectedItem().toString(),
                spPetGender.getSelectedItem().toString(),
                etPetDesc.getText().toString(),
                etPetAge.getText().toString(),
                etPetPrice.getText().toString(),
                pickMediaView.getImagesArrayList(),
                null);
        iPresenter.onShareAdClicked(advertisementModel);

    }

    @Override
    public void showPetNameErrorMessage(String message) {
        etPetName.setError(message);
    }

    @Override
    public void showPetPriceErrorMessage(String message) {
        etPetPrice.setError(message);

    }

    @Override
    public void showProgressView() {
        ((MainScreenActivity) getActivity()).showProgressView();
    }

    @Override
    public void dismissProgressView() {
        ((MainScreenActivity) getActivity()).dismisProgressView();
    }

    @Override
    public void showPetAgeErrorMessage(String message) {
        etPetAge.setError(message);
    }

    @Override
    public void showPetDescErrorMessage(String message) {
        etPetDesc.setError(message);
    }

    @Override
    public void showPicErrorMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShareAdSuccess() {
        dismissProgressView();
        resetCreateAdScreen();
//        Toast.makeText(getActivity(), "Heey", Toast.LENGTH_SHORT).show();
        iCreateAdwithActivityHolder.onCreateAdSuccess();
    }

    @Override
    public void onShareAdFailed(String message) {
        dismissProgressView();
//        Toast.makeText(getActivity(), "Failur", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void requestStoragePermission() {
        requestPermissions(new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
      if (requestCode == PickMediaView.PICK_IMAGE_CODE) {
                pickMediaView.setDataFromOnResult(String.valueOf(data.getData()));
            }
        }
    }

    private void resetCreateAdScreen() {
        etPetDesc.setText("");
        etPetAge.setText("");
        etPetPrice.setText("");
        etPetName.setText("");
        imgPetPic.setImageBitmap(null);
        bitmap = null;
        spPetType.setSelection(0);
        spPetGender.setSelection(0);

    }


}
