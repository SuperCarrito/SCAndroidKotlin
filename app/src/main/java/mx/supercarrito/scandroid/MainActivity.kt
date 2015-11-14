package mx.supercarrito.scandroid

import org.jetbrains.anko.*
import android.os.Bundle
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import mx.supercarrito.scandroid.CarritoService.*
import retrofit.Retrofit

class MainActivity : AppCompatActivity() {
    private val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("http://10.49.86.154")
            .build();
    private val service : CarritoService.CarritoInterface = retrofit.create(CarritoService.CarritoInterface::class.java)
    private var carrito : Carrito? = null;

    override protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pairCarrito()

        verticalLayout {
            textView("A continuacion tendra que escanear el QR del carrito")
            button("Siguiente") {

            }
        }
    }

    private fun pairCarrito() {
        try {
            val pairer : IntentIntegrator = IntentIntegrator()
            pairer.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            pairer.setPrompt("Escanea el código QR del carrito");
            pairer.initiateScan()
        } catch (ex: Exception) {
            println(ex.toString())
        }
    }

    override public fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        val scanResult : IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        val content : String? = scanResult?.contents
        if(content != null) {
            val response = service.getCarrito(content).execute()
            carrito = response.body()
            print(carrito.toString())
        }
    }
}
