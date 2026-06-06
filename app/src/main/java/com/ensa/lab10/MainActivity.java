package com.ensa.lab10;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mainDrawer;
    private NavigationView navInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeInterface();
        
        if (savedInstanceState == null) {
            switchDisplay(new HomeFragment());
            navInterface.setCheckedItem(R.id.action_view_home);
        }
    }

    private void initializeInterface() {
        Toolbar mainToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);

        mainDrawer = findViewById(R.id.drawer_layout);
        navInterface = findViewById(R.id.nav_view);
        navInterface.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, mainDrawer, mainToolbar,
                R.string.navigation_drawer_open, 
                R.string.navigation_drawer_close
        );
        mainDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int selectedId = item.getItemId();

        if (selectedId == R.id.action_view_home) {
            switchDisplay(new HomeFragment());
        } else if (selectedId == R.id.action_view_stats) {
            switchDisplay(new DashboardFragment());
        }

        mainDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchDisplay(Fragment targetFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        
        // Ajout d'une animation personnalisée pour l'authenticité
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        
        ft.replace(R.id.fragment_container, targetFragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (mainDrawer.isDrawerOpen(GravityCompat.START)) {
            mainDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}