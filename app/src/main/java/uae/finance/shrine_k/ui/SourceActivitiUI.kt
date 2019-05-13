package uae.finance.shrine_k.ui

import android.text.InputType.TYPE_CLASS_NUMBER
import android.text.InputType.TYPE_CLASS_TEXT
import android.widget.Button
import android.widget.EditText
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import uae.finance.shrine_k.SourceActivity

class SourceActivityUI : AnkoComponent<SourceActivity> {

    private val customStyle = { v: Any ->
        when (v) {
            is Button -> v.textSize = 26f
            is EditText -> v.textSize = 24f
        }
    }

    override fun createView(ui: AnkoContext<SourceActivity>) = with(ui) {

        verticalLayout {
            padding = dip(32)

            val title = editText {
                hint = "title"
                inputType = TYPE_CLASS_TEXT
            }
            val type = editText {
                hint = "type"
                inputType = TYPE_CLASS_TEXT
            }
            val currency = editText {
                hint = "currency"
                inputType = TYPE_CLASS_TEXT
            }
            val repeatableDate = editText {
                hint = "day of month"
                inputType = TYPE_CLASS_NUMBER
            }
            val active = toggleButton {
                hint = "active"
            }
            val repeatable = toggleButton {
                hint = "repeatable"
            }

            button("Cancel") {
                onClick {
                    ui.owner.finish()
                }
            }
            button("Save") {
                onClick {
                    ui.owner.saveSource(
                        ui.owner.source.id,
                        title.text.toString(),
                        type.text.toString(),
                        currency.text.toString(),
                        active.isChecked,
                        repeatable.isChecked,
                        Integer.parseInt(repeatableDate.text.toString())
                    )
                }
            }

        }.applyRecursively(customStyle)
    }
}