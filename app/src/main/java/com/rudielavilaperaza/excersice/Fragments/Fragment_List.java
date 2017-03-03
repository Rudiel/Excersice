package com.rudielavilaperaza.excersice.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.rudielavilaperaza.excersice.Activities.MainActivity;
import com.rudielavilaperaza.excersice.Adapters.Adapter_Product;
import com.rudielavilaperaza.excersice.Models.Response.Products_Response;
import com.rudielavilaperaza.excersice.R;
import com.rudielavilaperaza.excersice.Services.Interfaces.IExcersiceServices;
import com.rudielavilaperaza.excersice.Utilities.Utils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rudielavilaperaza on 3/1/17.
 */

public class Fragment_List extends Fragment implements IProductClick {

    private RecyclerView rvProducts;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((MainActivity) getActivity()).tvTitulo.setText("PRODUCTS");

        swipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.srlRefresh);


        rvProducts = (RecyclerView) getActivity().findViewById(R.id.rvProducts);
        rvProducts.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        rvProducts.setLayoutManager(mLayoutManager);

        /*si la lista no es null solo actuakizo el adapter si es null hago una peticion al web service*/

        if (((MainActivity) getActivity()).productList == null) {
            swipeRefreshLayout.setRefreshing(true);
            getProducts();
        } else {
            mAdapter = new Adapter_Product(((MainActivity) getActivity()).productList, getActivity(), Fragment_List.this);
            rvProducts.setAdapter(mAdapter);
        }


        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.color_green), getResources().getColor(R.color.color_title));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                getProducts();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_list, container, false);

    }


    private void getProducts() {

        /*Hago la peticion al web service*/


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL_GLOBAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IExcersiceServices service = retrofit.create(IExcersiceServices.class);

        /*Concateno el token por si fuera necesario cambiarlo mas adelante*/

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", Utils.TOKEN);

        Call<Products_Response> callProducts = service.getProducts(tokenMap);

        callProducts.enqueue(new Callback<Products_Response>() {
            @Override
            public void onResponse(Call<Products_Response> call, Response<Products_Response> response) {
                if (response != null) {
                    ((MainActivity) getActivity()).productList = response.body().getProducts();
                    mAdapter = new Adapter_Product(((MainActivity) getActivity()).productList, getActivity(), Fragment_List.this);
                    rvProducts.setAdapter(mAdapter);
                    swipeRefreshLayout.setRefreshing(false);

                }
            }

            @Override
            public void onFailure(Call<Products_Response> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });


    }

    @Override
    public void clickProduct(View v) {

        /*Obtengo la posicion de la vista y guardo el id para usarlo en el fargment de detalle y sobrepongo el fragment*/

        int position = rvProducts.getChildLayoutPosition(v);
        ((MainActivity) getActivity()).position = position;
        ((MainActivity) getActivity()).setFragment(new Fragment_Detail(), true);

    }
}
