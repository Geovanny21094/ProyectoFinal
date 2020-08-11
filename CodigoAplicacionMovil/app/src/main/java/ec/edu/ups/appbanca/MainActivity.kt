package ec.edu.ups.appbanca

import android.app.Person
import android.content.Intent
import android.media.MediaCodec
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(){

    private lateinit var btnLogin:Button
    private lateinit var txtUsuario:EditText
    private lateinit var txtContrasena:EditText

    //private var url = "http://127.0.0.1:8080/ProyectoFinal/rs/cliente/cliente/gabad/tt/"





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin=findViewById(R.id.btnIngresar)
        txtUsuario=findViewById(R.id.txtUsuario)
        txtContrasena=findViewById(R.id.editTextTextPassword)







        verificar()
    }



    private fun verificar(){
        btnLogin.setOnClickListener(){
            if (txtUsuario.text.toString().isNotEmpty() && txtContrasena.text.toString().isNotEmpty()){
                validarUsuario()
                Toast.makeText(this, "CARGANDO", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        /*btnLogin.setOnClickListener(){

        }*/
    }

    private fun validarUsuario(){
        val url = "http://35.193.85.73:8081/ProyectoFinal/rs/cliente/cliente/" +
                txtUsuario.text.toString() + "/" + txtContrasena.text.toString()
        //val url = "https://api.androidhive.info/contacts/"
        val queue = Volley.newRequestQueue(this)

        //imen/123
        val jsonObjectRequest = JsonObjectRequest(url, null,
            Response.Listener { response ->
                try {
                    val cli:JSONObject
                    cli = response.getJSONObject("cliente")
                    println("Este es el Cliente: " + "${cli.getInt("id_cliente")}")
                    val per:JSONObject
                    per = cli.getJSONObject("persona")
                    println("Este es el nombre: " + "${per.getString("nombres")}")


                    val i:Intent = Intent(this, MenuPrincipal::class.java)
                    //persona
                    i.putExtra("Cedula", "${per.getString("cedula")}")
                    i.putExtra("Nombres", "${per.getString("nombres")}")
                    i.putExtra("Apellidos", "${per.getString("apellidos")}")
                    i.putExtra("Genero", "${per.getString("genero")}")
                    i.putExtra("Correo", "${per.getString("correo")}")
                    i.putExtra("Direccion", "${per.getString("direccion")}")
                    //cliente
                    i.putExtra("Usuario", "${cli.getString("usuario")}")
                    i.putExtra("Contrasenia", "${cli.getString("contrasenia")}")
                    //cuenta
                    i.putExtra("Saldo", "${response.getString("saldo")}")
                    i.putExtra("NumeroCuenta", "${response.getString("numeroCuenta")}")

                startActivity(i)




                }catch (e:JSONException){
                    println("Excepcion: " + e)
                }

            }, Response.ErrorListener { error ->
                Toast.makeText(this, "Usuario/Clave incorrecto, revise los datos por favor"
                        , Toast.LENGTH_SHORT).show()
            })

        queue.add(jsonObjectRequest)


    }
}