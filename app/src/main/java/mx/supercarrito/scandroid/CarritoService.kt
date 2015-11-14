package mx.supercarrito.scandroid

import retrofit.*
import retrofit.http.*

class CarritoService {
    public interface CarritoInterface {
        @GET("v1/carrito/{carrito}")
        public fun getCarrito(@Path("carrito") cartId: String) : Call<Carrito>
    }

    public class Carrito() {
        public var totalPrice = 0
        public  var products = listOf<Product>()
    }

    public class Product() {
        public var barCode = "0"
        public var price = 0
        public var numberBought = 0

    }
}