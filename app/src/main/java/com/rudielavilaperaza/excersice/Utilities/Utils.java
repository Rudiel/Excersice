package com.rudielavilaperaza.excersice.Utilities;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by rudielavilaperaza on 3/1/17.
 */

public class Utils {

    /*Creo una clase utils donde pondre las variables que ocupare en toda la aplicacion
    como fuentes, url,preferencias, token (en este caso)*/

    public static final String URL_GLOBAL = "https://soliduxample.herokuapp.com";

    public static final String TOKEN = "ddcd14bbe6699211f8e157ca9b6812d9c699617c9a72caf7";


    public static Typeface getLatoLight(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Lato_Light.ttf");
    }

    public static Typeface getLato_Regular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Lato_Regular.ttf");
    }


}
