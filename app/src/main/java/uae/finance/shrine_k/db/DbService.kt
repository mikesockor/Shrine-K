package uae.finance.shrine_k.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import uae.finance.shrine_k.SOURCE_DB_NAME

class DbService private constructor(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MyDatabase3", null, 3) {

    init {
        instance = this
    }

    companion object {
        private var instance: DbService? = null

        @Synchronized
        fun getInstance(ctx: Context) = instance
            ?: DbService(ctx.applicationContext)
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(
            SOURCE_DB_NAME, true,
            "id" to TEXT + PRIMARY_KEY + UNIQUE,
            "title" to TEXT,
            "type" to TEXT,
            "currency" to TEXT,
            "active" to TEXT,
            "repeatable" to TEXT,
            "repeatableDate" to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(SOURCE_DB_NAME, true)
    }
}
