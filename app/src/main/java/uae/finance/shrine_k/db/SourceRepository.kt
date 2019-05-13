package uae.finance.shrine_k.db

import android.content.Context
import org.jetbrains.anko.db.*
import uae.finance.shrine_k.SOURCE_DB_NAME
import uae.finance.shrine_k.database
import uae.finance.shrine_k.model.Source

class SourceRepository(val context: Context) {

    fun findAll(): ArrayList<Source> = context.database.use {

        val sources = ArrayList<Source>()

        select(SOURCE_DB_NAME, "id", "title", "type", "currency", "active", "repeatable", "repeatableDate")
            .parseList(object : MapRowParser<List<Source>> {
                override fun parseRow(columns: Map<String, Any?>): List<Source> {
                    val id = columns.getValue("id")
                    val title = columns.getValue("title")
                    val type = columns.getValue("type")
                    val currency = columns.getValue("currency")
                    val active = columns.getValue("active")
                    val repeatable = columns.getValue("repeatable")
                    val repeatableDate = columns.getValue("repeatableDate")

                    val note = Source(
                        id.toString(),
                        title.toString(),
                        type.toString(),
                        currency.toString(),
                        active.toString().toBoolean(),
                        repeatable.toString().toBoolean(),
                        repeatableDate.toString().toInt()
                    )
                    sources.add(note)
                    return sources
                }
            })

        sources
    }

    fun create(source: Source) = context.database.use {
        insert(
            SOURCE_DB_NAME,
            "id" to source.id,
            "title" to source.title,
            "type" to source.type,
            "currency" to source.currency,
            "active" to source.active,
            "repeatable" to source.repeatable,
            "repeatableDate" to source.repeatableDate
        )
    }

    fun update(source: Source) = context.database.use {
        update(
            SOURCE_DB_NAME,
            "title" to source.title,
            "type" to source.type,
            "currency" to source.currency,
            "active" to source.active,
            "repeatable" to source.repeatable,
            "repeatableDate" to source.repeatableDate
        )
            .whereArgs("id = {sourceId}", "sourceId" to source.id)
            .exec()

    }

    fun delete(source: Source) = context.database.use {
        delete(SOURCE_DB_NAME, "_id = {sourceId}", "sourceId" to source.id)
    }
}