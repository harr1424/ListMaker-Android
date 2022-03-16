package com.harr1424.listmaker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MainItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "item_name") val itemName: String?
)

@Entity
data class DetailItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "main_item_id") val mainItemId: Int,
    @ColumnInfo(name = "detail_item_name") val detailItemName: String?
)