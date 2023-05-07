package com.example.shuffs_to_buy;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StuffAdapter extends RecyclerView.Adapter<ExpenseViewHolder> {

    private List<Stuffs> stuffList;
    private ItemClick click;

    public StuffAdapter(List<Stuffs> stuffList, MainActivity mainActivity) {
        this.stuffList = stuffList;
        this.click= mainActivity;
    }


    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stuffs, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Stuffs e = stuffList.get(position);
        holder.bind(e,click);


    }

    @Override
    public int getItemCount() {
        return stuffList.size();
    }

    public void updateList(ArrayList<Stuffs> list) {
        stuffList.clear();
        stuffList.addAll(list);
        notifyDataSetChanged();
    }


}


class ExpenseViewHolder extends RecyclerView.ViewHolder {


    private AppCompatTextView day;
    private AppCompatTextView amount;
    private ImageView img;

    public ExpenseViewHolder(@NonNull View itemView) {
        super(itemView);
        day = itemView.findViewById(R.id.type);
        amount = itemView.findViewById(R.id.amount);
        img = itemView.findViewById(R.id.deleteBtn);
    }

    public void bind(Stuffs expense, ItemClick click) {
        day.setText(expense.id);
        amount.setText(expense.name);
        img.setOnClickListener(view ->{
            click.onclick(expense);
        });

    }

}
interface ItemClick{
    public void onclick(Stuffs expense);

}
