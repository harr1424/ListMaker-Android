package com.harr1424.listmaker.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailItemDao {
    @Query("SELECT * FROM DetailItem")
    fun getAll(): Flow<List<DetailItem>>

    @Query("SELECT * FROM DetailItem WHERE id = :itemId")
    fun getById(itemId: Int): Flow<DetailItem>

    @Query("SELECT * FROM DetailItem WHERE detail_item_name LIKE :name")
    fun findByName(name: String): Flow<List<DetailItem>>

    @Query("SELECT * FROM DetailItem " +
            "INNER JOIN MainItem ON MainItem.id = DetailItem.main_item_id " +
            "WHERE DetailItem.main_item_id = :mainItemId ")
    fun getDetailFromMain(mainItemId: Int) : Flow<List<DetailItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: DetailItem)

    @Delete
    fun delete(item: DetailItem)

    @Update
    fun update(item: DetailItem)
}