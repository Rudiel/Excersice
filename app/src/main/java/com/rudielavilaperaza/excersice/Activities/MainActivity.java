package com.rudielavilaperaza.excersice.Activities;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.rudielavilaperaza.excersice.Fragments.Fragment_List;
import com.rudielavilaperaza.excersice.Models.Response.Product;
import com.rudielavilaperaza.excersice.R;
import com.rudielavilaperaza.excersice.Utilities.Utils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<Product> productList;
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    private Toolbar toolbar;
    public int position;
    public TextView tvTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Inicializo los view necesarios y le sobrepongo el menu al drawer*/

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitulo = (TextView) toolbar.findViewById(R.id.tvToolbarTitle);

        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.ndPrincipal);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);


        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.app_name, R.string.app_name
        );

        drawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        setFragment(new Fragment_List(), false);

        tvTitulo.setTypeface(Utils.getLato_Regular(this));

    }

    public void setFragment(Fragment fragment, boolean backStack) {

        /*Se sobrepone el fragment que se mande y si es backStack se pone en la pila*/

        if (backStack)
            getSupportFragmentManager().beginTransaction().replace(R.id.flContenedor, fragment).addToBackStack(null).commit();
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.flContenedor, fragment).commit();

    }
}
