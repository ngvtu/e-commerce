package vietmobi.net.ecommerce.activity.main;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.models.Items;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    private List<Items> listItems;
    Context context;

    public ItemsAdapter(List<Items> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.line_item_new_list_in_home, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Items items = listItems.get(position);
        holder.imvItem.setImageResource(items.getImgItems());
        holder.tvNameItem.setText(items.getItemName());
        holder.tvBranchName.setText(items.getOfBranch());
        holder.tvPrice.setText(String.valueOf(items.getPrice()));
        holder.tvOldPrice.setText(String.valueOf(items.getOldPrice()));
        holder.tvNewPrice.setText(String.valueOf(items.getNewPrice()));
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvItem, btnAddFavorite;
        TextView tvBranchName,tvNameItem, tvPrice, tvIsNew, tvOldPrice, tvNewPrice, tvIsSale ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imvItem = itemView.findViewById(R.id.imgItem);
            this.btnAddFavorite = itemView.findViewById(R.id.btnAddFavorite);
            this.tvBranchName = itemView.findViewById(R.id.tvBranchName);
            this.tvNameItem = itemView.findViewById(R.id.tvNameItem);
            this.tvPrice = itemView.findViewById(R.id.tvPrice);
            this.tvIsNew = itemView.findViewById(R.id.tvIsNew);
            this.tvOldPrice = itemView.findViewById(R.id.tvPriceOld);
            this.tvNewPrice = itemView.findViewById(R.id.tvPriceNew);
            this.tvIsSale = itemView.findViewById(R.id.tvIsSale);
        }
    }
}
