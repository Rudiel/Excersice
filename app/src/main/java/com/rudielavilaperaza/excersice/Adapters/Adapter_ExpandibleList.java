package com.rudielavilaperaza.excersice.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import com.rudielavilaperaza.excersice.R;
import com.rudielavilaperaza.excersice.Utilities.Utils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by rudielavilaperaza on 3/1/17.
 */

public class Adapter_ExpandibleList extends BaseExpandableListAdapter {

    /*Creo una lista expandible para las categorias*/

    private Context context;
    private List<String> listCategorias;
    private HashMap<String, List<String>> listaSubcategorias;

    public Adapter_ExpandibleList(Context context, List<String> listCategorias, HashMap<String, List<String>> listaSubcategorias) {
        this.context = context;
        this.listCategorias = listCategorias;
        this.listaSubcategorias = listaSubcategorias;
    }

    @Override
    public int getGroupCount() {
        return listCategorias.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listaSubcategorias.get(listCategorias.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listCategorias.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listaSubcategorias.get(listCategorias.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String categoria = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_fragment_questionsanswers_group, null);
        }
        TextView tvPregunta = (TextView) convertView.findViewById(R.id.lblListHeader);
        tvPregunta.setTypeface(null, Typeface.BOLD);
        tvPregunta.setText(categoria);
        tvPregunta.setTypeface(Utils.getLato_Regular(context));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String subcategoria = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_fragment_questionsanswers_item, null);
        }

        TextView tvRespuesta = (TextView) convertView.findViewById(R.id.lblListItem);
        tvRespuesta.setText(subcategoria);
        tvRespuesta.setTypeface(Utils.getLato_Regular(context));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
