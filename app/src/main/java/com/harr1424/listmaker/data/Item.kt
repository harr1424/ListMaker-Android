package com.harr1424.listmaker.data

import java.io.Serializable

data class Item(val itemName: String, var detailItems: MutableList<String>) : Serializable