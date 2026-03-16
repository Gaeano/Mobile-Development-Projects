package com.example.studentloginsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    Button logoutBtn, backBtn, sortFilterBtn;
    TextView userNameResult, passResult, emptyCartText;
    LinearLayout cartContainer;

    ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView[] productNames = {
                findViewById(R.id.shoeName),
                findViewById(R.id.funshineName),
                findViewById(R.id.wingstopName),
                findViewById(R.id.monitorName),
        };

        TextView[] productQuantity = {
                findViewById(R.id.shoeQuantity),
                findViewById(R.id.funshineQuantity),
                findViewById(R.id.wingstopQuantity),
                findViewById(R.id.monitorQuantity),
        };

        ImageView[] images = {
                findViewById(R.id.shoeImage),
                findViewById(R.id.funshineImage),
                findViewById(R.id.wingstopImage),
                findViewById(R.id.monitorImage),
        };

        TextView[] productPrices = {
                findViewById(R.id.shoeTotalPrice),
                findViewById(R.id.funshineTotalPrice),
                findViewById(R.id.wingstopTotalPrice),
                findViewById(R.id.monitorTotalPrice)
        };

        userNameResult = findViewById(R.id.username);
        passResult = findViewById(R.id.password);

        cartContainer = findViewById(R.id.cartItemsContainer);
        emptyCartText = findViewById(R.id.cartEmpty);

        logoutBtn = findViewById(R.id.logoutBtn);
        backBtn = findViewById(R.id.backToShop);
        sortFilterBtn = findViewById(R.id.btnSortFilter);

        String user = getIntent().getStringExtra("user");
        String pass = getIntent().getStringExtra("pass");

        userNameResult.setText("Welcome " + user);
        passResult.setText("Your current password is: " + pass);

        // 1. Retrieve the ArrayList directly using Serializable
        productList = (ArrayList<Product>) getIntent().getSerializableExtra("products");

        // Safety check just in case the intent didn't contain the list
        if (productList == null) {
            productList = new ArrayList<>();
        }

        if (productList.isEmpty() ||
                (productList.get(0).getQuantity() == 0 && productList.get(1).getQuantity() == 0 &&
                        productList.get(2).getQuantity() == 0 && productList.get(3).getQuantity() == 0)) {

            Snackbar.make(findViewById(android.R.id.content), "Cart is Empty", Snackbar.LENGTH_SHORT).show();
            cartContainer.setVisibility(View.GONE);
            emptyCartText.setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < productList.size(); i++) {
                setTitles(productList.get(i), productNames[i]);
                setQuantity(productList.get(i), productQuantity[i]);
                setImage(productList.get(i), images[i]);
                setPrice(productList.get(i), productPrices[i]);
            }
        }

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, login.class);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, productActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("pass", pass);

                // 2. Pass the ArrayList directly back to the productActivity
                intent.putExtra("products", productList);

                startActivity(intent);
            }
        });

        sortFilterBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, CategoryActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("pass", pass);
                startActivity(intent);
            }
        });
    }

    void setTitles(Product product, TextView names) {
        names.setText(product.getName());
    }

    void setQuantity(Product product, TextView quantity) {
        quantity.setText("Quantity: " + product.getQuantity());
    }

    void setImage(Product product, ImageView image) {
        image.setImageResource(product.getImageURL());
    }

    void setPrice(Product product, TextView prices) {
        int totalPrice = product.getPrice() * product.getQuantity();
        String price = Integer.toString(totalPrice);
        prices.setText("Total Price: ₱" + price);
    }
}