package vietmobi.net.ecommerce.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import vietmobi.net.ecommerce.models.Address;

@Database(entities = {Address.class}, version = 1)
public abstract class AddressDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "address.db";
    private static AddressDatabase instance;

    public static synchronized AddressDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AddressDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract AddressDAO AddressDAO();
}
