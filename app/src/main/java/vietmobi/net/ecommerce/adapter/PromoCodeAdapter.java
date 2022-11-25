package vietmobi.net.ecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.models.PromoCode;


public class PromoCodeAdapter extends RecyclerView.Adapter<PromoCodeAdapter.ViewHolder>{

    List<PromoCode> listPromoCode;
    Context context;

    public PromoCodeAdapter(List<PromoCode> listPromoCode, Context context) {
        this.listPromoCode = listPromoCode;
        this.context = context;
    }

    public void setData(List<PromoCode> listPromoCode){
        this.listPromoCode = listPromoCode;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.line_promo_code_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PromoCode promoCode = listPromoCode.get(position);
        if (promoCode == null){
            return;
        }

        holder.tvNameCode.setText(promoCode.getCodeName());
        holder.tvValuePromo.setText(String.valueOf(promoCode.getValuePromo()));
        holder.tvCode.setText(promoCode.getCode());
        holder.tvDayRemain.setText(String.valueOf(promoCode.getRemainingDay()));
    }

    @Override
    public int getItemCount() {
        return listPromoCode.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvValuePromo, tvNameCode, tvCode, tvDayRemain, btnApply;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvValuePromo = itemView.findViewById(R.id.tvValuePromo);
            tvNameCode = itemView.findViewById(R.id.tvNameCode);
            tvCode = itemView.findViewById(R.id.tvCode);
            tvDayRemain = itemView.findViewById(R.id.tvDayRemain);
            btnApply = itemView.findViewById(R.id.btnApply);
        }
    }
}
