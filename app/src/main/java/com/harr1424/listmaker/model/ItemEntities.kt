package com.harr1424.listmaker.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MainItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "item_name") val itemName: String?
)

@Entity(primaryKeys = ["main_item_id", "detail_item_name"])
data class DetailItem(
    @NonNull @ColumnInfo(name = "main_item_id") val mainItemId: Int,
    @NonNull @ColumnInfo(name = "detail_item_name") val detailItemName: String
)