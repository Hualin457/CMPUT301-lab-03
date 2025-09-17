package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EditCityFrag extends AddCityFrag {
    private final MainActivity ma;
    private final City city;

    public EditCityFrag(MainActivity ma, City city) {
        this.ma = ma;
        this.city = city;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.frag_add_city, null);
        EditText cityNameInput = view.findViewById(R.id.city_name_input);
        cityNameInput.setText(city.getName());
        EditText provinceNameInput = view.findViewById(R.id.province_name_input);
        provinceNameInput.setText(city.getProvince());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder.setView(view)
            .setTitle("Edit city")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Edit", (dialog, which) -> {
                String cityName = cityNameInput.getText().toString().strip();
                if (cityName.isEmpty()) {
                    Toast.makeText(getContext(), "City name is empty", Toast.LENGTH_SHORT).show();
                } else {
                    String provinceName = provinceNameInput.getText().toString().strip();
                    city.setName(cityName);
                    city.setProvince(provinceName);
                    ma.cityAdapter.notifyDataSetChanged();
                }
            })
            .create();
    }
}
