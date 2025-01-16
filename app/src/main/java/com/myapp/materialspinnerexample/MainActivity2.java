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

import com.myapp.materialspinnerexample.databinding.ActivityMain2Binding;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        var binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        List<String> data = Arrays.stream(getResources().getStringArray(R.array.tags_array)).sorted().collect(Collectors.toList());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, data);
        AutoCompleteTextView autoCompleteTextViewMeal = binding.autoCompleteTextViewMeal;

        autoCompleteTextViewMeal.setAdapter(arrayAdapter);
        autoCompleteTextViewMeal.setText(autoCompleteTextViewMeal.getAdapter().getItem(0).toString(), false);
        autoCompleteTextViewMeal.setOnItemClickListener((parent, view, position, id) -> {
            String selectedMeal = parent.getAdapter().getItem(position).toString();
            Toast.makeText(this, selectedMeal, Toast.LENGTH_SHORT).show();
        });
    }

}