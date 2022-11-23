package vietmobi.net.ecommerce.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vietmobi.net.ecommerce.models.Card;

@Dao
public interface CardDAO {
    @Insert
    void insertCard(Card card);

    @Query("select * from all_card")
    List<Card> getListCard();

    @Query("select * from all_card where id = :id")
    Card getCard(int id);

    @Query("select count(id) from all_card")
    int getCountCard();

    @Update
    void updateCard(Card card);

    @Delete
    void deleteCard(Card card);
}
