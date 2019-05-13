package uae.finance.shrine_k.ui

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.Gravity
import androidx.core.content.ContextCompat
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.sdk25.coroutines.onClick
import uae.finance.shrine_k.MainActivity
import uae.finance.shrine_k.R
import uae.finance.shrine_k.db.SourceRepository
import uae.finance.shrine_k.model.Source
import java.util.*

class MainActivityUI : AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {

        //            val textField = editText {
//                hint = "Text for toasts and snackbars"
//            }.lparams(width = matchParent) {
//                margin = dip(12)
//                topMargin = dip(30)
//            }
//
//            imageView(R.drawable.nbd_logo_30) {
//                onClick {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//                        this@imageView.imageTintList =
//                            ColorStateList.valueOf(Color.parseColor("#4CAF50"))
//                }
//            }.lparams (dip(72), dip(72)) {
//                gravity = Gravity.CENTER
//            }

//        val cardContent = listOf("first", "second", "third", "fourth", "first", "second", "third", "fourth")
        var sources = SourceRepository(this.ctx).findAll()

        frameLayout {

            floatingActionButton {
                imageResource = android.R.drawable.ic_input_add

                onClick {
                    alert {
                        title = "source" //ctx.getString(R.string.confirmar_pedido_title)
                        message = "add new" //ctx.getString(R.string.confirmar_pedido, totalListener.getValorTotal())
                        noButton { it.dismiss() }
                        yesButton {
                            //                            longSnackbar(
//                                "new source has been added"//ctx.getString(R.string.pedido_confirmado, totalListener.getValorTotal())
//                            )
                            var newSource = Source(
                                UUID.randomUUID().toString(),
                                "",
                                "",
                                "AED",
                                true,
                                true,
                                1
                            )
                            ui.owner.sourceDialog(ui, newSource)

                        }
                    }.show()
                }
            }.lparams {
                gravity = Gravity.BOTTOM or Gravity.END
                bottomMargin = dip(16)
                rightMargin = dip(16)
            }

            scrollView {
                gridLayout {
                    columnCount = 2
                    rowCount = sources.size / columnCount

                    sources.forEach {
                        cardView {
                            radius = dip(8).toFloat()
                            background.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)

                            var cardColor = when (it.active) {
                                true -> {
                                    ContextCompat.getColor(context, R.color.colorPrimary)
                                }
                                false -> {
                                    ContextCompat.getColor(context, R.color.colorShadow)
                                }
                            }

                            verticalLayout {
                                // title
                                textView {
                                    text = it.title
                                    textColor = cardColor
                                    textSize = 25f
                                }.lparams(width = matchParent) {
                                    rightMargin = dip(5)
                                    leftMargin = dip(5)
                                    topMargin = dip(5)
                                    bottomMargin = dip(5)
                                }
                                // subtitle
                                textView {
                                    text = it.type
                                    textColor = cardColor
                                    textSize = 15f
                                }.lparams(width = matchParent) {
                                    rightMargin = dip(5)
                                    leftMargin = dip(5)
                                    topMargin = dip(5)
                                    bottomMargin = dip(5)
                                }

                            }.lparams(width = 470) {
                                rightMargin = dip(5)
                                leftMargin = dip(5)
                                topMargin = dip(5)
                                bottomMargin = dip(5)
                            }
                        }.lparams {
                            bottomMargin = dip(5)
                            rightMargin = dip(5)
                        }
                    }
                }.lparams(width = matchParent, height = matchParent)
            }
        }

        //                button("Show toast") {
//                    onClick {
//                        toast(textField.text)
//                    }
//                }

//                button("Show snackbar") {
//                    onClick {
//                        longSnackbar(this@frameLayout, textField.text.toString())
//                    }
//                }


    }

}