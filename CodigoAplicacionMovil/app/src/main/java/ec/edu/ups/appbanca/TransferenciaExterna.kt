package ec.edu.ups.appbanca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cambio_password.*
import kotlinx.android.synthetic.main.activity_transferencia.*
import kotlinx.android.synthetic.main.activity_transferencia_externa.*
import org.json.JSONException
import org.json.JSONObject

class TransferenciaExterna : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transferencia_externa)

        val aux: Intent = intent

        txtOrigenExterna.setText(aux.getStringExtra("CuentaBancaria"))

        txtCedulaBeneficiari.isEnabled = false


        llenarDatos()
        enviarTransferencia()
        validarButtons()
        salir()
    }

    public fun salir(){
        btnCancelarExterna.setOnClickListener(){
            finish()
        }
    }

    public fun validarButtons(){
        rbtCorriente.setOnClickListener(){
            if (rbtAhorro.isSelected){
                rbtCorriente.isSelected=false
                rbtAhorro.isSelected=true
            }else{
                rbtCorriente.isSelected=true
                rbtAhorro.isSelected=false
            }
        }
        rbtAhorro.setOnClickListener(){
            if (rbtAhorro.isSelected){
                rbtCorriente.isSelected=false
                rbtAhorro.isSelected=true
            }else{
                rbtCorriente.isSelected=true
                rbtAhorro.isSelected=false
            }
        }
    }

    public fun enviarTransferencia(){
        btnConfirmarExterna.setOnClickListener(){

            if (cboBanco.visibility==View.VISIBLE){

                if (txtCedulaBeneficiari.text.toString().isNotEmpty() &&
                    txtCorreo.text.toString().isNotEmpty() && txtCorreo.text.toString().isNotEmpty() &&
                    txtNombreExterno.text.toString().isNotEmpty()){

                    val json = JSONObject()
                    if (rbtAhorro.isSelected){
                        json.put("tipoCuenta", "Cuenta de Ahorros")
                    }else if (rbtCorriente.isSelected){
                        json.put("tipoCuenta", "Cuenta Corriente")
                    }
                    json.put("institucionFinanciera", cboBanco.selectedItem.toString())
                    json.put("numeroCuentaBeneficiario", txtCuentaExterna.text.toString())
                    json.put("cedulaBeneficiario", txtCedulaBeneficiari.text.toString())
                    json.put("nombreBeneficiario", txtNombreExterno.text.toString())
                    json.put("correoBeneficiario", txtCorreo.text.toString())

                    val url = "http://35.193.85.73:8081/ProyectoFinal/rs/cliente/agregarCuentaDestino/"+
                            txtOrigenExterna.text.toString()

                    println("Buscando en " + "${url.toString()}")
                    val queue = Volley.newRequestQueue(this)


                    val jsonObjectRequest = JsonObjectRequest(url, json,
                        Response.Listener { response ->

                        }, Response.ErrorListener { error ->

                        })
                    queue.add(jsonObjectRequest)
                    Toast.makeText(this, "Exito, listo para realizar transferencia, " +
                            "vuelva a ingresar por favor.",
                        Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this, "Llene todos los campos correctamente",
                        Toast.LENGTH_SHORT).show()
                }



            }else if (lblBanco.visibility==View.VISIBLE){
                val objetoJSON = JSONObject()
                objetoJSON.put("numeroCuentaOrigen", txtOrigenExterna.text.toString())
                objetoJSON.put("numeroCuentaExterna", txtCuentaExterna.text.toString())
                objetoJSON.put("monto", txtMontoExterno.text.toString())

                val url = "http://35.193.85.73:8081/ProyectoFinal/rs/cliente/tranferenciaExterna/"+
                        txtOrigenExterna.text.toString() + "/" + txtCuentaExterna.text.toString()+
                        "/" + txtMontoExterno.text

                println("Enviando en " + "${url.toString()}")
                val queue = Volley.newRequestQueue(this)
                val jsonObjectRequest = JsonObjectRequest(url, objetoJSON,
                    Response.Listener { response ->

                    }, Response.ErrorListener { error ->
                        Toast.makeText(this, "Transferencia realizada", Toast.LENGTH_SHORT).show()
                        finish()
                    })
                queue.add(jsonObjectRequest)
            }

        }
    }

    public fun llenarDatos(){
        btnBuscarDestino.setOnClickListener(){
            cuentaExterna()
        }
    }

    public fun cuentaExterna(){
        val url = "http://35.193.85.73:8081/ProyectoFinal/rs/cliente/cuentaDestino/"+
                txtCuentaExterna.text.toString()

        println("Buscando en " + "${url.toString()}")
        val queue = Volley.newRequestQueue(this)

        try {
        val jsonObjectRequest = JsonObjectRequest(url, null,
            Response.Listener { response ->

                txtCedulaBeneficiari.setText(response.getString("cedulaBeneficiario"))
                txtNombreExterno.setText(response.getString("nombreBeneficiario"))
                txtCorreo.setText(response.getString("correoBeneficiario"))
                val radio:String
                radio = response.getString("tipoCuenta")
                if (radio.equals("Cuenta Ahorros")){
                    rbtAhorro.isSelected = true
                    rbtCorriente.isSelected = false
                }else{
                    rbtAhorro.isSelected = false
                    rbtCorriente.isSelected = true
                }
                lblBanco.visibility = View.VISIBLE
                cboBanco.visibility = View.INVISIBLE
                lblBanco.setText(response.getString("institucionFinanciera"))
            }, Response.ErrorListener { error ->
                txtMontoExterno.visibility = View.INVISIBLE
                Toast.makeText(this, "Cuenta no registrada, llene los datos a continuacion", Toast.LENGTH_SHORT).show()
                txtCedulaBeneficiari.isEnabled = true
                txtCorreo.isEnabled = true
                txtNombreExterno.isEnabled=true
                txtCedulaBeneficiari.isEnabled=true
                rbtAhorro.isSelected = true
                rbtCorriente.isSelected = false
                lblBanco.visibility = View.INVISIBLE
                cboBanco.visibility = View.VISIBLE
            })
            queue.add(jsonObjectRequest)
        }catch (e: JSONException){

            println("Excepcion: " + e)
        }

    }




}