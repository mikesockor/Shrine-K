package uae.finance.shrine_k

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.activityUiThreadWithContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.setContentView
import uae.finance.shrine_k.model.Source
import uae.finance.shrine_k.ui.MainActivityUI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
    }

    fun sourceDialog(ui: AnkoContext<MainActivity>, source: Source?) {
        ui.doAsync {
            activityUiThreadWithContext {
                val intent = Intent(this@MainActivity,SourceActivity::class.java)
                intent.putExtra("source", source)
                startActivity(intent)
            }
        }
    }

}
