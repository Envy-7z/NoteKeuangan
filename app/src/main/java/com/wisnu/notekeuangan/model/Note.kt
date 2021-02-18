package com.wisnu.notekeuangan.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

//Entity annotation to specify the table's name
@Entity(tableName = "notes")
//Parcelable annotation to make parcelable object
@Parcelize
data class Note(
    //PrimaryKey annotation to declare primary key
    //ColumnInfo annotation to specify the column's name
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "body") var body: String = "",
    @ColumnInfo(name = "jumlah") var jumlah : Int =0,
    @ColumnInfo(name = "tanggal") var tanggal : String ="",
    @ColumnInfo(name = "nomor") var nomor : Int =0

) : Parcelable {
}