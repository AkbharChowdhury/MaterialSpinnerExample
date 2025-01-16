package com.myapp.materialspinnerexample;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.myapp.materialspinnerexample.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        var binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<String> data = Arrays.stream(getResources().getStringArray(R.array.tags_array)).sorted().collect(Collectors.toList());

        AutoCompleteTextView autoCompleteMeals = binding.autoCompleteMeals;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_item, data);
        autoCompleteMeals.setAdapter(adapter);
//        setDropDownIndex(autoCompleteMeals, 0);
        autoCompleteMeals.setText(
                autoCompleteMeals.getAdapter().getItem(0).toString(), false);
        autoCompleteMeals.setOnItemClickListener((parent, view, position, id) -> {
            String selectedMeal = parent.getAdapter().getItem(position).toString();
            Toast.makeText(this, selectedMeal, Toast.LENGTH_SHORT).show();
            setDropDownIndex(autoCompleteMeals, position);
        });
    }

    private void setDropDownIndex(AutoCompleteTextView autoCompleteTextView, int index) {
        autoCompleteTextView.setText(
                autoCompleteTextView.getAdapter().getItem(index).toString(), false);
    }
}