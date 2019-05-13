package uae.finance.shrine_k

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.setContentView
import uae.finance.shrine_k.db.SourceRepository
import uae.finance.shrine_k.model.Source
import uae.finance.shrine_k.ui.SourceActivityUI

class SourceActivity : AppCompatActivity() {

    lateinit var source: Source

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        source = intent.getParcelableExtra("source")
        SourceActivityUI().setContentView(this)
    }

    fun saveSource(id: String, title: String, type: String, currency: String, active: Boolean, repeatable: Boolean, repeatableDate: Int) {
        val newSource = Source(
            id, title, type, currency, active, repeatable, repeatableDate
        )
        SourceRepository(this).create(newSource)
        finish()
    }
}