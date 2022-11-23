package vietmobi.net.ecommerce.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.activity.AddAddressActivity;
import vietmobi.net.ecommerce.database.AddressDatabase;
import vietmobi.net.ecommerce.models.Address;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.ViewHolder> {
    public static final int MY_REQUEST_CODE = 10;

    List<Address> listAddress;
    Context context;

    public AddressesAdapter(List<Address> listAddress, Context context) {
        this.listAddress = listAddress;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.line_shipping_address, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Address address = listAddress.get(position);
        if (address == null) {
            return;
        }

        holder.tvNameUser.setText(address.getFullName());
        holder.tvPlace.setText(address.getAddress());
        holder.tvDistrict.setText(address.getDistrict());
        holder.tvCity.setText(address.getProvince());
        holder.tvCountry.setText(address.getCountry());
        holder.tvPhoneNumber.setText(address.getNumberPhone());

        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddAddressActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Address", address);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                ((Activity) context).startActivityForResult(intent, MY_REQUEST_CODE);
            }
        });
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressDatabase.getInstance(context).AddressDAO().deleteAddress(address);
                listAddress = AddressDatabase.getInstance(context).AddressDAO().getListAddress();
                setData(listAddress);
            }
        });
    }
    public void setData(List<Address> listAddress) {
        this.listAddress = listAddress;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listAddress.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameUser, tvPlace, tvEdit, tvPhoneNumber, tvDistrict, tvCity, tvCountry, tvDelete;
        CheckBox cbxUse;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameUser = itemView.findViewById(R.id.tvNameUser);
            tvDelete = itemView.findViewById(R.id.tvDelete);
            tvEdit = itemView.findViewById(R.id.tvEdit);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
            tvDistrict = itemView.findViewById(R.id.tvDistrict);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvCountry = itemView.findViewById(R.id.tvCountry);
            cbxUse = itemView.findViewById(R.id.cbxUse);
        }
    }
}
