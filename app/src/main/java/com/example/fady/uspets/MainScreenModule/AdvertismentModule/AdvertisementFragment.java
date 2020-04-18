package com.example.fady.uspets.MainScreenModule.AdvertismentModule;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fady.uspets.ControllerDI.ControllerComponent;
import com.example.fady.uspets.ControllerDI.ControllerModule;
import com.example.fady.uspets.ControllerDI.DaggerControllerComponent;
import com.example.fady.uspets.MainScreenModule.MainScreenActivity;
import com.example.fady.uspets.PersonalAdModule.PersonalAdActivity;
import com.example.fady.uspets.PetDetailsModule.PetDetailsActiviy;
import com.example.fady.uspets.R;
import com.example.fady.uspets.USPetsMain.PetUiManager;
import com.example.fady.uspets.databinding.AdvertismentViewLayoutBinding;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdvertisementFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IAdvertisement.IAdvertismentView, IAdvertisement.IAdvertismentClick {
    public static final String ADVERTISEMENT_KEY = "currentAd";

    @BindView(R.id.MyRcyclerview)
    RecyclerView rvAdvertisement;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout srRefresh;

    private ArrayList<AdvertisementModel> advertisementModelArrayList;
    private AdvertisementAdapter advertisementAdapter;

    @Inject
    AdvertisementPresenter iAdvertismentPresenter;

    public AdvertisementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentadvertisement, container, false);

        ButterKnife.bind(this, view);
        srRefresh.setOnRefreshListener(this);
        initDagger();
        initAdRecyclerview();
        iAdvertismentPresenter.listenToUserChannels();
        iAdvertismentPresenter.getAdvertisement();
        return view;
    }

    private void initAdRecyclerview() {
        advertisementModelArrayList = new ArrayList<>();
        rvAdvertisement.setLayoutManager(new LinearLayoutManager(getActivity()));
        advertisementAdapter = new AdvertisementAdapter(advertisementModelArrayList, this);
        rvAdvertisement.setAdapter(advertisementAdapter);

    }

    private void initDagger() {
        ControllerComponent controllerComponent = DaggerControllerComponent.builder().applicationComponent(((MainScreenActivity) getActivity())
                .getApplicationComponent()).controllerModule(new ControllerModule(this)).build();
        controllerComponent.inject(this);
    }

    @Override
    public void onRefresh() {
        srRefresh.post(() -> {
            srRefresh.setRefreshing(true);

            advertisementModelArrayList.clear();
            advertisementAdapter.notifyDataSetChanged();
            iAdvertismentPresenter.onRefreshGetAdvertisement();
        });
    }

    @Override
    public void onRetrieveAdvertisementsSuccess(AdvertisementModel advertisementModel) {
        advertisementModelArrayList.add(0, advertisementModel);
        advertisementAdapter.notifyDataSetChanged();
        if (srRefresh.isRefreshing())
            srRefresh.setRefreshing(false);

    }

//    @Override
//    public void onRetriveAdvertismentsOnceSuccess(AdvertisementModel advertisementModel) {
//        advertisementModelArrayList.add(0, advertisementModel);
//        advertisementAdapter.notifyDataSetChanged();
//
//}

    @Override
    public void onRetrieveAdvertisementsFailed(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
        srRefresh.setRefreshing(false);
    }


    @Override
    public void onAdvertisementClickListener(int pos) {
        Intent intent;
        if (advertisementModelArrayList.get(pos).getOwnerUid().equals(PetUiManager.getInstance().getCurrentUser().getoUid()))
            intent = new Intent(getActivity(), PersonalAdActivity.class);
        else
            intent = new Intent(getActivity(), PetDetailsActiviy.class);
        intent.putExtra(ADVERTISEMENT_KEY, advertisementModelArrayList.get(pos));
        startActivity(intent);
    }
}
