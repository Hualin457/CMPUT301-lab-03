package com.example.listycitylab3;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCityFrag.AddCityDialogListener {
    ArrayList<City> cityList;
    ListView cityListView;
    CityArrayAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = new ArrayList<>();
        cityList.add(new City("Edmonton", "AB"));
        cityList.add(new City("Vancouver", "BC"));
        cityList.add(new City("Toronto", "ON"));
        cityList.add(new City("Ottawa", "ON"));
        cityList.add(new City("Saskatoon", "SK"));
        
        cityListView = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, cityList);
        cityListView.setAdapter(cityAdapter);
        cityListView.setOnItemClickListener((parent, view, position, id) -> {
            City city = cityAdapter.getItem(position);
            if (city == null) {
                throw new IllegalStateException("City list does not have a city at position " + position);
            }
            new EditCityFrag(this, city).show(getSupportFragmentManager(), "EditCity");
        });

        FloatingActionButton addCityButton = findViewById(R.id.button_add_city);
        addCityButton.setOnClickListener(
            v -> new AddCityFrag()
                .show(getSupportFragmentManager(), "AddCity")
        );
    }

    @Override
    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }
}
