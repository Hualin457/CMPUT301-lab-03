package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFrag extends DialogFragment {
    @FunctionalInterface
    public interface AddCityDialogListener {
        void addCity(City city);
    }

    protected AddCityDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implements AddCityDialogListener!");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.frag_add_city, null);
        EditText cityNameInput = view.findViewById(R.id.city_name_input);
        EditText provinceNameInput = view.findViewById(R.id.province_name_input);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder.setView(view)
            .setTitle("Add a city")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Add", (dialog, which) -> {
                String cityName = cityNameInput.getText().toString().strip();
                if (cityName.isEmpty()) {
                    Toast.makeText(getContext(), "City name is empty", Toast.LENGTH_SHORT).show();
                } else {
                    String provinceName = provinceNameInput.getText().toString().strip();
                    listener.addCity(new City(cityName, provinceName));
                }
            })
            .create();
    }
}
