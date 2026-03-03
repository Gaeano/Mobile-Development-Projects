package com.example.studentloginsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class CategoryActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<productModel> productsCategory = new ArrayList<>();
    ArrayList<productModel> currentDisplayedList = new ArrayList<>();

    Spinner spinner, spinnerSize, spinnerColor;
    String[] filter = {"Lowest Price", "Highest Price", "A - Z", "Z - A"};
    String[] colors = {"All", "White", "Black", "Navy", "Silver", "Red", "Green", "Multi", "Blue", "Gray"};
    String[] sizes = {"All", "Small", "Medium", "Large", "Standard"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);
       listView = findViewById(R.id.list);
       spinner = findViewById(R.id.spinner);
       spinnerSize = findViewById(R.id.spinnerSize);
       spinnerColor = findViewById(R.id.spinnerColor);



        productsCategory.add(new productModel("Desk Lamps", 15.99, "White", "Medium"));
        productsCategory.add(new productModel("Calculators", 12.50, "Black", "Small"));
        productsCategory.add(new productModel("Jackets", 45.00, "Navy", "Large"));
        productsCategory.add(new productModel("Flash Drives", 8.99, "Silver", "Small"));
        productsCategory.add(new productModel("T-Shirts", 2.00, "Red", "Medium"));
        productsCategory.add(new productModel("Energy Drinks", 3.49, "Green", "Standard"));
        productsCategory.add(new productModel("Index Cards", 1.99, "White", "Small"));
        productsCategory.add(new productModel("Art Supplies", 18.75, "Multi", "Large"));
        productsCategory.add(new productModel("Gym Bags", 24.99, "Blue", "Large"));
        productsCategory.add(new productModel("Textbooks", 89.99, "Gray", "Standard"));

        currentDisplayedList.addAll(productsCategory); // Initialize with all products

        ArrayAdapter<productModel> arrayProducts;
       ArrayAdapter<String> arrayCategory;
       ArrayAdapter<String> arraySize;
       ArrayAdapter<String> arrayColor;
        
        arrayProducts = new ArrayAdapter<productModel>(this, android.R.layout.simple_list_item_2, android.R.id.text1, new ArrayList<productModel>());
        arrayCategory = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, android.R.id.text1, filter);
        arraySize = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, android.R.id.text1, sizes);
        arrayColor = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, android.R.id.text1, colors);

        spinnerSize.setAdapter(arraySize);
        spinnerColor.setAdapter(arrayColor);
        listView.setAdapter(arrayProducts);
        spinner.setAdapter(arrayCategory);
        
        //sort products on startUp
        List<productModel> FilteredList = sortOnStart(0);
        arrayProducts.clear();
        arrayProducts.addAll(FilteredList);
        arrayProducts.notifyDataSetChanged();

        filterByColorListener(spinnerColor, arrayProducts);
        filterBySizeListener(spinnerSize, arrayProducts);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(CategoryActivity.this, "Selected: "+selectedItem, Toast.LENGTH_LONG).show();

                List<productModel> FilteredList = filterByCategory(position);
                arrayProducts.clear();
                arrayProducts.addAll(FilteredList);
                arrayProducts.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }

        });

    }
    public List<productModel> filterByCategory(int pos){
        // Sort the currently displayed list, not the master list
        List<productModel> sortedList = new ArrayList<>(currentDisplayedList);

            switch (pos){
                case 0:
                    Collections.sort(sortedList, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
                    break;

                case 1:
                    Collections.sort(sortedList, (p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
                    break;

                case 2:
                    Collections.sort(sortedList, (p1, p2) -> p1.getName().compareTo(p2.getName()));
                    break;

                case 3:
                    Collections.sort(sortedList, (p1, p2) -> p2.getName().compareTo(p1.getName()));
                    break;

                default:
                    Toast.makeText(CategoryActivity.this, "No Category Selected", Toast.LENGTH_LONG).show();
                    break;
            }
            currentDisplayedList = new ArrayList<>(sortedList); // Update current display
            return sortedList;
    }

    public List<productModel> sortOnStart(int pos){
        List<productModel> sortedList = new ArrayList<>(productsCategory);

        Collections.sort(sortedList, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        Toast.makeText(CategoryActivity.this, "Sorted by Price (Ascending)", Toast.LENGTH_LONG).show();

        currentDisplayedList = new ArrayList<>(sortedList); // Update current display

        return sortedList;
    }

    public void filterByColorListener(Spinner spinColor, ArrayAdapter arrayProducts){
        spinColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            
            @Override 
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<productModel> FilteredList = filterColor(position);
                arrayProducts.clear();
                arrayProducts.addAll(FilteredList);
                arrayProducts.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){
                
            }      
        });
    }
    
    public List<productModel> filterColor(int pos){
        List<productModel> filteredList = new ArrayList<>();
        String selectedColor = colors[pos];

        Map<String, Predicate<productModel>> colorFilters = mapColorFilters();
        Predicate<productModel> filter = colorFilters.getOrDefault(selectedColor, product -> false);

        for (productModel product : productsCategory) {
            if (filter.test(product)) {
                filteredList.add(product);
            }
        }

        currentDisplayedList = new ArrayList<>(filteredList); // Update current display
        return filteredList;
    }

    public Map mapColorFilters(){
        Map<String, Predicate<productModel>> colorFilters = new HashMap<>();
        colorFilters.put("All", product -> true);
        colorFilters.put("White", product -> product.getColor().equals("White"));
        colorFilters.put("Black", product -> product.getColor().equals("Black"));
        colorFilters.put("Navy", product -> product.getColor().equals("Navy"));
        colorFilters.put("Silver", product -> product.getColor().equals("Silver"));
        colorFilters.put("Red", product -> product.getColor().equals("Red"));
        colorFilters.put("Green", product -> product.getColor().equals("Green"));
        colorFilters.put("Multi", product -> product.getColor().equals("Multi"));
        colorFilters.put("Blue", product -> product.getColor().equals("Blue"));
        colorFilters.put("Gray", product -> product.getColor().equals("Gray"));


        return colorFilters;
    }

    public void filterBySizeListener(Spinner spinSize, ArrayAdapter arrayProducts) {
        spinSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<productModel> FilteredList = filterBySize(position);
                arrayProducts.clear();
                arrayProducts.addAll(FilteredList);
                arrayProducts.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public List<productModel> filterBySize(int pos){
        List<productModel> filteredList = new ArrayList<>();
        String selectedSize = sizes[pos];

        Map<String, Predicate<productModel>> sizeFilters = mapSizeFilters();
        Predicate<productModel> filter = sizeFilters.getOrDefault(selectedSize, product -> false);

        for (productModel product : productsCategory) {
            if (filter.test(product)) {
                filteredList.add(product);
            }
        }

        currentDisplayedList = new ArrayList<>(filteredList); // Update current display
        return filteredList;
    }

    public Map mapSizeFilters(){
        Map<String, Predicate<productModel>> sizeFilters = new HashMap<>();
        sizeFilters.put("All", product -> true);
        sizeFilters.put("Small", product -> product.getSize().equals("Small"));
        sizeFilters.put("Medium", product -> product.getSize().equals("Medium"));
        sizeFilters.put("Large", product -> product.getSize().equals("Large"));
        sizeFilters.put("Standard", product -> product.getSize().equals("Standard"));

        return sizeFilters;
    }
}