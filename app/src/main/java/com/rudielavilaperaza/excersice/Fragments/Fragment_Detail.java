package com.rudielavilaperaza.excersice.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.rudielavilaperaza.excersice.Activities.MainActivity;
import com.rudielavilaperaza.excersice.Adapters.Adapter_ExpandibleList;
import com.rudielavilaperaza.excersice.Models.Response.Product;
import com.rudielavilaperaza.excersice.R;
import com.rudielavilaperaza.excersice.Utilities.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rudielavilaperaza on 3/1/17.
 */

public class Fragment_Detail extends Fragment implements ExpandableListView.OnGroupExpandListener, ExpandableListView.OnGroupCollapseListener {

    private Product product;
    private Adapter_ExpandibleList expAdapter;
    private List<String> listCategorias;
    private HashMap<String, List<String>> listSubCategorias;
    private ExpandableListView expList;
    private ScrollView scrollView;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        scrollView = (ScrollView) getActivity().findViewById(R.id.svContainer);

        ((MainActivity) getActivity()).tvTitulo.setText(getResources().getString(R.string.detail_title));

        product = (((MainActivity) getActivity()).productList.get(((MainActivity) getActivity()).position));

        /*Tipografia a los titulos*/
        ((TextView) getActivity().findViewById(R.id.tvProductDetailDescriptionTitle)).setTypeface(Utils.getLato_Regular(getActivity()));
        ((TextView) getActivity().findViewById(R.id.tvProductDetailTitle)).setTypeface(Utils.getLato_Regular(getActivity()));
        ((TextView) getActivity().findViewById(R.id.tvProductDetailPriceTitle)).setTypeface(Utils.getLato_Regular(getActivity()));
        ((TextView) getActivity().findViewById(R.id.tvProductDetailDateTitle)).setTypeface(Utils.getLato_Regular(getActivity()));
        ((TextView) getActivity().findViewById(R.id.tvProductDetailShippingTitle)).setTypeface(Utils.getLato_Regular(getActivity()));

        /*Inicializo los views que ocupare*/

        TextView tvDetail = (TextView) getActivity().findViewById(R.id.tvProductDetailTitle);
        TextView tvPrice = (TextView) getActivity().findViewById(R.id.tvProductDetailPrice);
        TextView tvShipping = (TextView) getActivity().findViewById(R.id.tvProductDetailShipping);
        TextView tvDate = (TextView) getActivity().findViewById(R.id.tvProductDetailDate);
        TextView tvDescription = (TextView) getActivity().findViewById(R.id.tvProductDetailDescrption);

        tvDetail.setText(product.getName());
        tvDescription.setText(product.getDescription());
        tvPrice.setText(product.getDisplay_price());
        tvShipping.setText(product.getShipping_category_id());

        /*Se parsea la fecha*/
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(product.getAvailable_on());
            String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
            tvDate.setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*Se inicializa la lista expandible con los datos harcode (no econtre categorias en el json)*/
        expList = (ExpandableListView) getActivity().findViewById(R.id.explistQuestions);


        prepareListData();

        expAdapter = new Adapter_ExpandibleList(getActivity(), listCategorias, listSubCategorias);

        expList.setAdapter(expAdapter);
        expList.refreshDrawableState();
        expList.setOnGroupExpandListener(this);
        expList.setOnGroupCollapseListener(this);

        scrollView.refreshDrawableState();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_detail, container, false);
    }

    private void prepareListData() {
        listCategorias = new ArrayList<String>();
        listSubCategorias = new HashMap<String, List<String>>();

        // Adding child data
        listCategorias.add("Category 1");
        listCategorias.add("Category 2");
        listCategorias.add("Category 3");
        listCategorias.add("Category 4");
        listCategorias.add("Category 5");
        listCategorias.add("Category 6");
        listCategorias.add("Category 7");


        // Adding child data
        List<String> categoria1 = new ArrayList<>();
        categoria1.add("SubCategory 1");

        List<String> categoria2 = new ArrayList<>();
        categoria2.add("SubCategory 2");

        List<String> categoria3 = new ArrayList<>();
        categoria3.add("SubCategory 3");

        List<String> categoria4 = new ArrayList<>();
        categoria4.add("SubCategory 4");

        List<String> categoria5 = new ArrayList<>();
        categoria5.add("SubCategory 5");

        List<String> categoria6 = new ArrayList<>();
        categoria6.add("SubCate6gory 6");

        List<String> categoria7 = new ArrayList<>();
        categoria7.add("SubCategory 7");


        listSubCategorias.put(listCategorias.get(0), categoria1); // Header, Child data
        listSubCategorias.put(listCategorias.get(1), categoria2);
        listSubCategorias.put(listCategorias.get(2), categoria3);
        listSubCategorias.put(listCategorias.get(3), categoria4);
        listSubCategorias.put(listCategorias.get(4), categoria5);
        listSubCategorias.put(listCategorias.get(5), categoria6);
        listSubCategorias.put(listCategorias.get(6), categoria7);

    }

    @Override
    public void onGroupCollapse(int groupPosition) {
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) expList.getLayoutParams();
        param.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        expList.setLayoutParams(param);
        expList.refreshDrawableState();
        scrollView.refreshDrawableState();

    }

    @Override
    public void onGroupExpand(int groupPosition) {
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) expList.getLayoutParams();
        int childCount = listSubCategorias.get(listCategorias.get(groupPosition)).size();
        param.height = (childCount * expList.getHeight());
        expList.setLayoutParams(param);
        expList.refreshDrawableState();
        scrollView.refreshDrawableState();
    }
}
