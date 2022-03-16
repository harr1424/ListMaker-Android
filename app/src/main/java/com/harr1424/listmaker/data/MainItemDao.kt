package com.harr1424.listmaker.data

import androidx.room.*
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

    @Delete
    fun delete(item: MainItem)

    @Update
    fun update(item: MainItem)
}