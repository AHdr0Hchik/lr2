package com.ahdrohchik.pmvu_lr2_v7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {
    private Laptop laptop;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        laptop = (Laptop) getIntent().getSerializableExtra("laptop");
        position = getIntent().getIntExtra("position", -1);

        // Настройка UI элементов
        TextView manufacturerText = findViewById(R.id.manufacturerText);
        TextView screenText = findViewById(R.id.screenText);
        TextView batteryText = findViewById(R.id.batteryText);
        EditText commentEdit = findViewById(R.id.commentEdit);
        CheckBox selectedCheck = findViewById(R.id.selectedCheck);
        Button saveButton = findViewById(R.id.saveButton);

        manufacturerText.setText(laptop.getManufacturer());
        screenText.setText(String.format("Диагональ экрана: %.1f\"", laptop.getScreenSize()));
        batteryText.setText(String.format("Время работы от батареи: %d часов", laptop.getBatteryLife()));
        commentEdit.setText(laptop.getComment());
        selectedCheck.setChecked(laptop.isSelected());

        saveButton.setOnClickListener(v -> {
            laptop.setComment(commentEdit.getText().toString());
            laptop.setSelected(selectedCheck.isChecked());

            Intent resultIntent = new Intent();
            resultIntent.putExtra("laptop", laptop);
            resultIntent.putExtra("position", position);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
