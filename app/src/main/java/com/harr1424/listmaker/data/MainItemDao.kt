package com.harr1424.listmaker.data

import androidx.room.*
import com.harr1424.listmaker.model.MainItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MainItemDao {
    @Query("SELECT * FROM MainItem ORDER BY id ASC")
    fun getAll(): Flow<List<MainItem>>

    @Query("SELECT * FROM MainItem WHERE id = :itemId")
    fun getById(itemId: Int): Flow<MainItem>

    @Query("SELECT * FROM MainItem WHERE item_name LIKE :name")
    fun findByName(name: String): Flow<List<MainItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: MainItem)

    @Query(" DELETE FROM detailitem where main_item_id = :mainItemId")
    fun deleteDetailsFromMain(mainItemId: Int)

    @Delete
    fun delete(item: MainItem)

    @Query("UPDATE MainItem SET item_name = :name WHERE id = :itemId")
    fun update(itemId: Int, name: String)
}