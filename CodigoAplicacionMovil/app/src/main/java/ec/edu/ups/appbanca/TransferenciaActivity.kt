package ec.edu.ups.appbanca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_transferencia.*
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class TransferenciaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transferencia)

        val aux: Intent = intent

        val dest = aux.getStringExtra("CuentaOrigen")
        val names = aux.getStringExtra("NombresOrigen") +
                aux.getStringExtra("ApellidosOrigen")

        txtOrigen.setText(dest)
        lblNombreOrigen.text = names

        buscarDestino()
        botonTransfer()
        salir()
    }


    public fun salir() {
        btnSalir.setOnClickListener() {
            finish()
        }
    }

    public fun buscarDestino() {
        btnDatosDestino.setOnClickListener() {
            if (txtDestino.text.isNotEmpty()) {
                getDestino()
                if (lblNombreDestinatario.text.toString().equals("")) {

                    btnTransferir.isEnabled = true
                } else {
                    btnTransferir.isEnabled = true
                }

            } else {
                Toast.makeText(
                    this, "Debe ingresar un numero de cuenta valido",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        if (lblNombreDestinatario.text.toString().equals("")) {
            btnTransferir.isEnabled = false
        } else {
            btnTransferir.isEnabled = true
        }

    }

    public fun transferir() {
        try {
            if (txtMonto.text.isNotEmpty()) {
                val url = "http://35.193.85.73:8081/ProyectoFinal/rs/transaccion/transferencia/" +
                        txtMonto.text.toString() + "/" + txtOrigen.text.toString() + "/" +
                        txtDestino.text.toString()

                println("Buscando en " + "${url.toString()}")
                val queue = Volley.newRequestQueue(this)
                val jsonObjectRequest = JsonObjectRequest(url, null,
                    Response.Listener { response ->
                    }, Response.ErrorListener { error ->
                        Toast.makeText(
                            this,
                            "Transferencia terminada exitosamente.",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    })
                queue.add(jsonObjectRequest)
            }
        } catch (e: Exception) {
            println("Excepcion: " + e)
        }
    }

    public fun botonTransfer() {
        btnTransferir.setOnClickListener() {
            transferir()
        }
    }

    public fun getDestino() {
        if (txtOrigen.text.toString().equals(txtDestino.text.toString())) {
            Toast.makeText(this, "El numero de cuenta debe ser diferente al suyo.",
                Toast.LENGTH_SHORT).show()
            txtDestino.setText("")
        }else{
            val url = "http://35.193.85.73:8081/ProyectoFinal/rs/transaccion/transferencia/"+
                    txtDestino.text.toString()

            println("Buscando en " + "${url.toString()}")
            val queue = Volley.newRequestQueue(this)


            val jsonObjectRequest = JsonObjectRequest(url, null,
                Response.Listener { response ->
                    try {
                        val cli: JSONObject
                        cli = response.getJSONObject("cliente")
                        val per: JSONObject
                        per = cli.getJSONObject("persona")

                        var destino:String
                        destino = per.getString("nombres").toString() + " " +
                                per.getString("apellidos").toString()
                        lblNombreDestinatario.setText(destino)
                    }catch (e: JSONException){
                        println("Excepcion: " + e)
                    }

                }, Response.ErrorListener { error ->
                    error.printStackTrace()
                })
            queue.add(jsonObjectRequest)
        }
        }
    }


