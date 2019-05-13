package uae.finance.shrine_k

import android.content.Context
import uae.finance.shrine_k.db.DbService

val Context.database: DbService
    get() = DbService.getInstance(applicationContext)