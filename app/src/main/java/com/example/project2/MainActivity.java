package com.example.project2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.Openable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.navfragment);
        NavigationUI.setupActionBarWithNavController(this, navController);

    }

    public void Navigate(View view) {
        Navigation.findNavController(view).navigate(MainFragmentDirections.actionMainFragmentToDetailsFragment2());
    }

    @Override
    public boolean onSupportNavigateUp() {//function return true or false
        return NavigationUI.navigateUp(navController, (Openable) null);
    }
}