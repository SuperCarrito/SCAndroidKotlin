package mx.supercarrito.scandroid

import org.jetbrains.anko.*
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

class MainActivity : Activity() {

    override protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            textView("A continuacion tendra que escanear el QR del carrito")
            button("Siguiente") {
                onClick { pairCarrito() }
            }
        }
    }

    private fun pairCarrito() {
        val pairer : IntentIntegrator = IntentIntegrator(this)
        pairer.initiateScan()
    }

    override public fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        val scanResult : IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
    }
}
