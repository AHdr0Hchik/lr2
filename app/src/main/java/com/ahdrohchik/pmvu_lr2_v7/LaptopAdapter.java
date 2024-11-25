package com.ahdrohchik.pmvu_lr2_v7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder> {
    private List<Laptop> laptops;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public LaptopAdapter(List<Laptop> laptops, OnItemClickListener listener) {
        this.laptops = laptops;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LaptopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.laptop_item, parent, false);
        return new LaptopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopViewHolder holder, int position) {
        Laptop laptop = laptops.get(position);

        // Установка основной информации
        holder.manufacturerText.setText(laptop.getManufacturer());
        holder.detailsText.setText(String.format("Диагональ: %.1f\", Батарея: %dч",
                laptop.getScreenSize(), laptop.getBatteryLife()));

        // Отображение статуса выбора
        String statusText = laptop.isSelected() ? "Выбрано" : "";
        holder.statusText.setText(statusText);

        // Отображение комментария
        if (!laptop.getComment().isEmpty()) {
            holder.commentText.setText("Комментарий: " + laptop.getComment());
            holder.commentText.setVisibility(View.VISIBLE);
        } else {
            holder.commentText.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return laptops.size();
    }

    class LaptopViewHolder extends RecyclerView.ViewHolder {
        TextView manufacturerText, detailsText, commentText, statusText;

        public LaptopViewHolder(@NonNull View itemView) {
            super(itemView);
            manufacturerText = itemView.findViewById(R.id.manufacturerText);
            detailsText = itemView.findViewById(R.id.detailsText);
            commentText = itemView.findViewById(R.id.commentText);
            statusText = itemView.findViewById(R.id.statusText);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            });
        }
    }
}
