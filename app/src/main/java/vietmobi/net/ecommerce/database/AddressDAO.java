package vietmobi.net.ecommerce.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vietmobi.net.ecommerce.models.Address;

@Dao
public interface AddressDAO {
    @Insert
    void insertAddress(Address address);

    @Query("select * from all_address")
    List<Address> getListAddress();

    @Query("select * from all_address where id = :id")
    Address getAddress(int id);

    @Query("select count(id) from all_address")
    int getCountAddress();

    @Update
    void updateAddress(Address address);

    @Delete
    void deleteAddress(Address address);
}
