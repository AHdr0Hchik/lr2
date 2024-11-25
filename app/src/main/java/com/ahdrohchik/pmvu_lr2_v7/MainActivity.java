package com.ahdrohchik.pmvu_lr2_v7;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Laptop> laptops;
    private LaptopAdapter adapter;
    private static final int DETAIL_REQUEST_CODE = 1;
    private ActivityResultLauncher<Intent> detailActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация списка ноутбуков
        initializeLaptopList();

        // Настройка RecyclerView
        setupRecyclerView();
    }

    private void initializeLaptopList() {
        laptops = new ArrayList<>();
        laptops.add(new Laptop("Dell XPS 13", 13.3, 12));
        laptops.add(new Laptop("MacBook Pro", 14.2, 17));
        laptops.add(new Laptop("Lenovo ThinkPad", 15.6, 10));
        laptops.add(new Laptop("HP Spectre", 13.5, 13));
        laptops.add(new Laptop("ASUS ROG", 17.3, 8));
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new LaptopAdapter(laptops, position -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("laptop", laptops.get(position));
            intent.putExtra("position", position);
            startActivityForResult(intent, DETAIL_REQUEST_CODE);
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DETAIL_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            int position = data.getIntExtra("position", -1);
            Laptop updatedLaptop = (Laptop) data.getSerializableExtra("laptop");
            if (position != -1 && updatedLaptop != null) {
                laptops.set(position, updatedLaptop);
                adapter.notifyItemChanged(position);
            }
        }
    }
}
