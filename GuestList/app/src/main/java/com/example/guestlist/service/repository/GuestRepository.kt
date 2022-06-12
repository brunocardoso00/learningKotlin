package com.example.guestlist.service.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.guestlist.service.constants.DataBaseConstants
import com.example.guestlist.service.model.GuestModel
import java.lang.Exception

class GuestRepository private constructor(context: Context) {

    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)

    //SINGLETON

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized)
                repository = GuestRepository(context)

            return repository
        }

    }


    @SuppressLint("Range")
    fun get(id: Int): GuestModel?
    {
        var guest : GuestModel? = null
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val value = ContentValues()
            value.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest?.name);
            value.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest?.presence)
            val selection =  DataBaseConstants.GUEST.COLUMNS.ID+ " = ?"
            val args = arrayOf(guest?.id.toString())
            var projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.NAME ,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE)

            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME,projection,selection,args,null,null,null)

            if(cursor != null && cursor.count > 0)
            {
                cursor.moveToFirst()
                var name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                var presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1
                guest = GuestModel(id,name, presence)
            }
            cursor?.close()

            guest
        }
        catch(exception: Exception){
            guest
        }
    }

    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getPresents(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getAbsents(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun save(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val value = ContentValues()
            value.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name);
            value.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, value)
            true
        }
        catch(exception: Exception){
            false
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val value = ContentValues()
            value.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name);
            value.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)
            val selection =  DataBaseConstants.GUEST.COLUMNS.ID+ " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, value, selection, args)
            true
        }
        catch(exception: Exception){
            false
        }
    }

    fun delete(guest: GuestModel) :Boolean{
        return try
        {
            val db = mGuestDataBaseHelper.writableDatabase
            val selection =  DataBaseConstants.GUEST.COLUMNS.ID+ " = ?"
            val args = arrayOf(guest.id.toString())
            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        }catch(exception: Exception)
        {
            false
        }
    }
}