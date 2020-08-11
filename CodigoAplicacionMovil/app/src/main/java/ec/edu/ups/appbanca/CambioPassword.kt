package ec.edu.ups.appbanca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cambio_password.*
import kotlinx.android.synthetic.main.activity_transferencia.*
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class CambioPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambio_password)

        val aux: Intent = intent
        txtNumeroCuentaPasswordNew.setText(aux.getStringExtra("CuentaBancaria"))

        habilitarButton()
        validarCampos()

        salir()

    }

    public fun salir(){
        btnCancelarCambio.setOnClickListener(){
            finish()
        }
    }
    public fun habilitarButton() {
        if (txtNumeroCuentaPasswordNew.isFocused) {
            validarCampos()
        }
    }

    public fun validarCampos() {
        if (txtNewPassword.text.toString().equals(txtRepeatNew.text.toString())) {
            btnCambiar.isEnabled = true
            btnCambiar.setOnClickListener() {
                cambiarContraseña()
            }
        }else if (!txtNewPassword.text.toString().equals(txtRepeatNew.text.toString())) {
            Toast.makeText(this, "Las contraseñas deben coincidir",
                Toast.LENGTH_SHORT).show()
        }

    }

    public fun cambiarContraseña() {

        val url = "http://35.193.85.73:8081/ProyectoFinal/rs/cliente/contrasenia/"+
                txtOldPassword.text.toString() + "/" + txtNewPassword.text.toString() + "/" +
                txtNumeroCuentaPasswordNew.text.toString()

        println("Posteando en " + "${url.toString()}")

        val objetoJSON = JSONObject()
        objetoJSON.put("passwordOld", txtOldPassword.text.toString())
        objetoJSON.put("passwordNew", txtNewPassword.text.toString())
        objetoJSON.put("numeroCuenta", txtNumeroCuentaPasswordNew.text.toString())



        val queue = Volley.newRequestQueue(this)
        try {
            val jsonObjectRequest = JsonObjectRequest(url, objetoJSON,
                Response.Listener { response ->

                }, Response.ErrorListener { error ->
                    Toast.makeText(this, "Exito al cambiar contraseña", Toast.LENGTH_SHORT).show()
                    finishAffinity()
                })
            queue.add(jsonObjectRequest)
        }catch (e:Exception){
            Toast.makeText(this, "Algo a salido mal, intentalo de nuevo mas tarde",
                Toast.LENGTH_SHORT).show()
        }

    }
    }