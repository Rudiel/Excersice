package com.rudielavilaperaza.excersice.Services.Interfaces;

import com.rudielavilaperaza.excersice.Models.Response.Product;
import com.rudielavilaperaza.excersice.Models.Response.Products_Response;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by rudielavilaperaza on 3/1/17.
 */

public interface IExcersiceServices {

    /*Creo una interfaz para los servicios con retrofit */

    @Headers({"Content-Type: application/json"})
    @GET("api/products.json")
    Call<Products_Response> getProducts(@QueryMap Map<String, String> Token);
}
