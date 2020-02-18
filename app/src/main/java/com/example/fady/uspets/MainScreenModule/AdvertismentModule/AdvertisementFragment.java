package com.example.fady.uspets.MainScreenModule.AdvertismentModule;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fady.uspets.ControllerDI.ControllerComponent;
import com.example.fady.uspets.ControllerDI.ControllerModule;
import com.example.fady.uspets.ControllerDI.DaggerControllerComponent;
import com.example.fady.uspets.MainScreenModule.MainScreenActivity;
import com.example.fady.uspets.R;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdvertisementFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IAdvertisement.IAdvertismentView, IAdvertisement.IAdvertismentClick {
    @BindView(R.id.MyRcyclerview)
    RecyclerView rvAdvertisement;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout srRefresh;

    ControllerComponent controllerComponent;
    ArrayList<AdvertisementModel> advertisementModelArrayList;
    AdvertisementAdapter advertisementAdapter;

    @Inject
    AdvertisementPresenter iAdvertismentPresenter;

    public AdvertisementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_advertisment, container, false);
        ButterKnife.bind(this, view);
        srRefresh.setOnRefreshListener(this);
        initDagger();
        initAdRecyclerview();
        iAdvertismentPresenter.getAdvertisment();
        return view;
    }

    private void initAdRecyclerview() {
        advertisementModelArrayList = new ArrayList<>();
        rvAdvertisement.setLayoutManager(new LinearLayoutManager(getActivity()));
        advertisementAdapter = new AdvertisementAdapter(advertisementModelArrayList, this);
        rvAdvertisement.setAdapter(advertisementAdapter);

    }

    private void initDagger() {
        controllerComponent = DaggerControllerComponent.builder().applicationComponent(((MainScreenActivity) getActivity())
                .getApplicationComponent()).controllerModule(new ControllerModule(this)).build();
        controllerComponent.inject(this);
    }

    @Override
    public void onRefresh() {
        srRefresh.post(new Runnable() {
            @Override
            public void run() {
                srRefresh.setRefreshing(true);
                iAdvertismentPresenter.getAdvertisment();
                advertisementModelArrayList.clear();
                advertisementAdapter.notifyDataSetChanged();

            }
        });


    }

    @Override
    public void onRetriveAdvertismentsSuccess(AdvertisementModel advertisementModel) {
//        // TODO: 2020-01-07 remove this two lines .... it should work if new item got added just add this item not the whole items again
//        advertisementModelArrayList.clear();
//        advertisementAdapter.notifyDataSetChanged();


        advertisementModelArrayList.add(advertisementModel);
        advertisementAdapter.notifyDataSetChanged();
        srRefresh.setRefreshing(false);

    }

    @Override
    public void onRetriveAdvertismentsFailed(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
        srRefresh.setRefreshing(false);
    }


    @Override
    public void onAdvertismentClicListner(int pos) {

    }
}
