package vietmobi.net.ecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.List;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.database.AddressDatabase;
import vietmobi.net.ecommerce.database.CardDatabase;
import vietmobi.net.ecommerce.models.Card;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    List<Card> listCard;
    Context context;

    public CardAdapter(List<Card> listCard, Context context) {
        this.listCard = listCard;
        this.context = context;
    }

    public void setData(List<Card> listCard){
        this.listCard = listCard;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.line_payment_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card  card = listCard.get(position);
        if (card == null){
            return;
        }
        holder.tvCardName.setText(card.getCardName());
        holder.tvExpiryDate.setText(card.getExpiryDate());

//        char [] data = card.getNumberCard().toCharArray();
//        String text4CardNumber = String.copyValueOf(data, 12, 4);

        holder.tv4Number.setText(card.getNumberCard());

        if (card.isMasterCard() == true){
            holder.imgMasterCard.setVisibility(View.VISIBLE);
            holder.imgVisaCard.setVisibility(View.GONE);
        } else {
            holder.imgVisaCard.setVisibility(View.VISIBLE);
            holder.imgVisaCard.setVisibility(View.GONE);
        }

        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardDatabase.getInstance(view.getContext()).cardDAO().deleteCard(card);
                listCard = CardDatabase.getInstance(context).cardDAO().getListCard();
                setData(listCard);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listCard.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvCardName, tvExpiryDate, tvDelete, tv4Number;
        ImageView imgVisaCard, imgMasterCard;
        CheckBox cbxUse;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCardName = itemView.findViewById(R.id.tvCardName);
            tvExpiryDate = itemView.findViewById(R.id.tvExpiryDate);
            tvDelete = itemView.findViewById(R.id.tvDelete);
            tv4Number = itemView.findViewById(R.id.tv4Number);
            imgVisaCard = itemView.findViewById(R.id.imgVisaCard);
            imgMasterCard = itemView.findViewById(R.id.imgMasterCard);
            cbxUse = itemView.findViewById(R.id.cbxUse);
        }
    }
}
