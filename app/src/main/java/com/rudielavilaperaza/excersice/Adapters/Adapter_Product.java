package com.rudielavilaperaza.excersice.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rudielavilaperaza.excersice.Fragments.IProductClick;
import com.rudielavilaperaza.excersice.Models.Response.Product;
import com.rudielavilaperaza.excersice.R;
import com.rudielavilaperaza.excersice.Utilities.Utils;

import java.util.List;

/**
 * Created by rudielavilaperaza on 3/1/17.
 */

public class Adapter_Product extends RecyclerView.Adapter<Adapter_Product.ViewHolder> {

    /*Para optimizacion de la lista ocupo recyclerview*/

    private List<Product> productList;
    private Context context;
    private IProductClick productClick;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvProductName;
        TextView tvPrice;
        TextView tvStock;
        ImageView ivProduct;

        ViewHolder(View itemView) {
            super(itemView);

            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvStock = (TextView) itemView.findViewById(R.id.tvStock);
            ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        /*Se crea una vista nueva si es necesaria y al darle clic a detalles mando el view contenedor para sacar la posicion de la lista*/

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);

        (view.findViewById(R.id.btDetails)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productClick.clickProduct(view);
            }
        });

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        /*Muestro los valores de la posicion*/

        holder.tvProductName.setTypeface(Utils.getLato_Regular(context));
        holder.tvProductName.setText(productList.get(position).getName());

        holder.tvPrice.setTypeface(Utils.getLatoLight(context));
        holder.tvPrice.setText("Price: " + productList.get(position).getDisplay_price());

        holder.tvStock.setTypeface(Utils.getLatoLight(context));
        holder.tvStock.setText("Stock: " + productList.get(position).getTotal_on_hand());

        /*Ocupo glide para la carga rapida de las imagenes y por si se tienen que cambiar mas adelante*/

        Glide.with(context).load(R.drawable.ic_launcher).centerCrop().into(holder.ivProduct);
    }


    @Override
    public int getItemCount() {

        /*El numero de elementos en la lista*/
        return productList.size();
    }


    public Adapter_Product(List<Product> productList, Context context, IProductClick productClick) {

        /*Se crea un constructor con la lista de productos, el contexto y la interfaz del clic*/
        this.productList = productList;
        this.context = context;
        this.productClick = productClick;
    }
}
