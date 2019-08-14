package com.example.bionintelligence.presentation.culture;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bionintelligence.R;
import com.example.bionintelligence.data.model.CultureModel;
import com.example.bionintelligence.databinding.CardCultureBinding;

import java.util.List;

public class CultureRvAdapter extends RecyclerView.Adapter<CultureRvAdapter.ViewHolder> {
    private List<CultureModel> cultureList;
    private ClickListener clickListener;
    private Context context;
    private int selectCultureId;

    CultureRvAdapter(List<CultureModel> cultureList, int selectCultureId) {
        this.cultureList = cultureList;
        this.selectCultureId = selectCultureId - 1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardCultureBinding binding = DataBindingUtil.inflate(inflater, R.layout.card_culture, parent, false);

        context = parent.getContext();
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setCultureModel(cultureList.get(position));
        if (selectCultureId == position) {
            holder.binding.containerCulture.setBackground(context.getResources().getDrawable(R.drawable.bg_values_light_green_selected));
            holder.binding.tvCultureName.setTextColor(Color.WHITE);
        } else {
            holder.binding.containerCulture.setBackground(context.getResources().getDrawable(R.drawable.bg_values_light_green));
            holder.binding.tvCultureName.setTextColor(Color.GRAY);
        }
    }

    @Override
    public int getItemCount() {
        return cultureList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardCultureBinding binding;

        ViewHolder(CardCultureBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.containerCulture.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
