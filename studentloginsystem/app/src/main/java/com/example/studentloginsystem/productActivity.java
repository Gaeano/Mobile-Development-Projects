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
    import androidx.recyclerview.widget.GridLayoutManager;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.google.android.material.snackbar.Snackbar;
    import com.google.gson.Gson;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.List;

    public class productActivity extends AppCompatActivity implements CartItemListener {


        String user, pass;

        RecyclerView productRecycler;
        shoppingAdapter productAdapter;

        private List<Product> productList = new ArrayList<>();

        Button btnAddToCart;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_product);

            user = getIntent().getStringExtra("user");
            pass = getIntent().getStringExtra("pass");

            productList = (ArrayList <Product>) getIntent().getSerializableExtra("products");

            if (productList == null) {
                productList = new ArrayList<>();
                populateList();
            }


            GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
            productRecycler = findViewById(R.id.product_recycler_view);
            productRecycler.setLayoutManager(layoutManager);

            ((androidx.recyclerview.widget.SimpleItemAnimator) productRecycler.getItemAnimator()).setSupportsChangeAnimations(false);

            productAdapter = new shoppingAdapter(productList, this);
            productRecycler.setAdapter(productAdapter);





            btnAddToCart = findViewById(R.id.btn_add_to_cart);




            btnAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(productActivity.this, Profile.class);
                    intent.putExtra("user", user);
                    intent.putExtra("pass", pass);


                        intent.putExtra("products", (Serializable) productList);
                    startActivity(intent);
                }
            });


        }

        @Override
        public void AddOnClick(Product item, int position) {
            int currentQty = item.getQuantity();
            item.setQuantity(currentQty + 1);

            productAdapter.notifyItemChanged(position);

        }

        @Override
        public void MinusOnClick(Product item, int position) {
            int currentQty = item.getQuantity();
            if(currentQty > 0){
                item.setQuantity(currentQty - 1);
                productAdapter.notifyItemChanged(position);
            }
        }

        void populateList(){
            productList.add(new Product("Flight Court", 0, R.drawable.flightcourt, 20));
            productList.add(new Product("Funshine", 0, R.drawable.funshine, 10));
            productList.add(new Product("Wingstop", 0, R.drawable.wingstop, 50));
            productList.add(new Product("Monitor", 0, R.drawable.monitor, 60));
        }


    }


