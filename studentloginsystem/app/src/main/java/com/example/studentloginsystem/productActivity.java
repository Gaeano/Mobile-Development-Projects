    package com.example.studentloginsystem;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    import com.google.android.material.snackbar.Snackbar;
    import com.google.gson.Gson;

    public class productActivity extends AppCompatActivity {


        String user, pass;

        Button btnAddToCart;
        Product[] products;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_product);

            Button[] buttonAdd = {
                    findViewById(R.id.btn_add_flight),
                    findViewById(R.id.btn_plus_funshine),
                    findViewById(R.id.btn_plus_wingstop),
                    findViewById(R.id.btn_plus_monitor)
            };

            Button[] buttonMinus = {
                    findViewById(R.id.btn_minus_flight),
                    findViewById(R.id.btn_minus_funshine),
                    findViewById(R.id.btn_minus_wingstop),
                    findViewById(R.id.btn_minus_monitor)
            };

            TextView[] result = {
                    findViewById(R.id.result_flight),
                    findViewById(R.id.result_funshine),
                    findViewById(R.id.result_wingstop),
                    findViewById(R.id.result_monitor)
            };

            TextView[] productTitles = {
                    findViewById(R.id.shoeTitle),
                    findViewById(R.id.funshineTitle),
                    findViewById(R.id.wingstopTitle),
                    findViewById(R.id.monitorTitle)
            };

            ImageView[] images = {
                    findViewById(R.id.shoe_image),
                    findViewById(R.id.funshine_image),
                    findViewById(R.id.wingstop_image),
                    findViewById(R.id.monitor_image),
            };

            TextView[] productPrices = {
                    findViewById(R.id.shoePrice),
                    findViewById(R.id.funshinePrice),
                    findViewById(R.id.wingstopPrice),
                    findViewById(R.id.monitorPrice)
            };

            btnAddToCart = findViewById(R.id.btn_add_to_cart);

            user = getIntent().getStringExtra("user");
            pass = getIntent().getStringExtra("pass");
            String jsonProducts = getIntent().getStringExtra("products");
            Gson gson = new Gson();

            products = gson.fromJson(jsonProducts, Product[].class);
            if (products != null) {
                for (int i = 0; i < products.length; i++){

                    setTitles(products[i], productTitles[i]);
                    setQuantity(products[i], result[i]);
                    setImage(products[i], images[i]);
                    setPrice(products[i], productPrices[i]);
                }
            } else {

                products = new Product[4];

                products[0] = new Product("Flight Court", 0, R.drawable.flightcourt, 20);
                setTitles(products[0], productTitles[0]);
                setQuantity(products[0], result[0]);
                setImage(products[0], images[0]);
                setPrice(products[0], productPrices[0]);

                products[1] = new Product("Funshine", 0, R.drawable.funshine, 10);
                setTitles(products[1], productTitles[1]);
                setQuantity(products[1], result[1]);
                setImage(products[1], images[1]);
                setPrice(products[1], productPrices[1]);


                products[2] = new Product("Wingstop", 0, R.drawable.wingstop, 50);
                setTitles(products[2], productTitles[2]);
                setQuantity(products[2], result[2]);
                setImage(products[2], images[2]);
                setPrice(products[2], productPrices[2]);


                products[3] = new Product("Monitor", 0, R.drawable.monitor, 60);
                setTitles(products[3], productTitles[3]);
                setQuantity(products[3], result[3]);
                setImage(products[3], images[3]);
                setPrice(products[3], productPrices[3]);



            }




            for (int i = 0; i < buttonAdd.length; i++){
                setAddOnclickListeners(buttonAdd[i], products[i], result[i]);
                setMinusOnClickListeners(buttonMinus[i], products[i], result[i]);
            }

            btnAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String jsonArray = gson.toJson(products);
                    Intent intent = new Intent(productActivity.this, Profile.class);
                    intent.putExtra("user", user);
                    intent.putExtra("pass", pass);

                    intent.putExtra("products", jsonArray);
                    startActivity(intent);
                }
            });


        }


        void setAddOnclickListeners(Button addBtn, Product product, TextView result) {
            addBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int num = product.getQuantity();
                    num++;
                    product.setQuantity(num);
                    result.setText(String.valueOf(num));
                    Snackbar.make(v, "Added to Cart", Snackbar.LENGTH_SHORT).show();
                }
            });


        }

        void setMinusOnClickListeners(Button minusBtn, Product product, TextView result){

            minusBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int num = product.getQuantity();
                    if(num > 0){
                        num--;
                        product.setQuantity(num);
                        result.setText(String.valueOf(num));
                        Snackbar.make(v, "Removed from Cart", Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        }


        void setTitles (Product product, TextView names){
            names.setText(product.getName());
        }

        void setQuantity (Product product, TextView quantity){
            quantity.setText(String.valueOf(product.getQuantity()));
        }

        void setImage (Product product, ImageView image){
            image.setImageResource(product.getImageURL());
        }

        void setPrice(Product product, TextView prices){
            String price = Integer.toString(product.getPrice());
            prices.setText("₱"+ price);
        }
    }