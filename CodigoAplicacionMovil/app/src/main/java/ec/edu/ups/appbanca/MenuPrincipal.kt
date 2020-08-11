package ec.edu.ups.appbanca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_menu_principal.*
import org.json.JSONException
import org.json.JSONObject

class MenuPrincipal : AppCompatActivity() {

    private lateinit var cedula:String
    private lateinit var nombres:String
    private lateinit var apellidos:String
    private lateinit var genero:String
    private lateinit var correo:String
    private lateinit var direccion:String
    private lateinit var usuario:String
    private lateinit var contrasena:String
    private lateinit var saldo:String
    private lateinit var numCuenta:String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)


        val aux: Intent=intent
        val user= aux.getStringExtra("Nombres") + " " +aux.getStringExtra("Apellidos")
        val cuent= aux.getStringExtra("NumeroCuenta")
        val monto= aux.getStringExtra("Saldo")

        lblUsuario.text = user
        lblCuenta.text = cuent
        lblSaldo.text = monto


        usuario = aux.getStringExtra("Usuario").toString()

        contrasena = aux.getStringExtra("Contrasenia").toString()

        nombres = aux.getStringExtra("Nombres").toString()
        apellidos = aux.getStringExtra("Apellidos").toString()
        saldo = aux.getStringExtra("Saldo").toString()

        botonUpdate()
        transferencias()
        botonCambioPassword()
        cerrarSesion()

        externas()
    }



    public fun transferencias(){
        btnTransferencia.setOnClickListener(){
            val i:Intent = Intent(this, TransferenciaActivity::class.java)
            i.putExtra("CuentaOrigen", lblCuenta.text.toString())
            i.putExtra("NombresOrigen", nombres)
            i.putExtra( "ApellidosOrigen", apellidos)
            startActivity(i)
        }
    }

    public fun botonUpdate(){
        btnUpdate.setOnClickListener(){
            Toast.makeText(this, "CARGANDO", Toast.LENGTH_SHORT).show()
            validarUsuario()
            lblSaldo.text = saldo.toString()
        }
    }


    private fun validarUsuario(){
        val url = "http://35.193.85.73:8081/ProyectoFinal/rs/cliente/cliente/" +
                "${usuario.toString()}" + "/" + "${contrasena.toString()}"
        println("Buscando en " + "${url.toString()}")
        val queue = Volley.newRequestQueue(this)

        //imen/123
        val jsonObjectRequest = JsonObjectRequest(url, null,
            Response.Listener { response ->
                try {
                    val cli: JSONObject
                    cli = response.getJSONObject("cliente")
                    println("Este es el Cliente: " + "${cli.getInt("id_cliente")}")
                    val per: JSONObject
                    per = cli.getJSONObject("persona")
                    println("Este es el nombre: " + "${per.getString("nombres")}")

                    cedula = "${per.getString("cedula")}"
                    nombres =  "${per.getString("nombres")}"
                    apellidos = "${per.getString("apellidos")}"
                    genero = "${per.getString("genero")}"
                    correo = "${per.getString("correo")}"
                    direccion =  "${per.getString("direccion")}"
                    //cliente
                    usuario =  "${cli.getString("usuario")}"
                    contrasena =  "${cli.getString("contrasenia")}"
                    //cuenta
                    saldo =  "${response.getString("saldo")}"
                    numCuenta = "${response.getString("numeroCuenta")}"

                }catch (e: JSONException){
                    Toast.makeText(this, "Usuario/Clave incorrecto", Toast.LENGTH_SHORT).show()
                    println("Excepcion: " + e)
                }

            }, Response.ErrorListener { error ->
                error.printStackTrace()
            })

        queue.add(jsonObjectRequest)


    }


    public fun botonCambioPassword(){
        btnMenuCambiarPassword.setOnClickListener(){
            val i:Intent = Intent(this, CambioPassword::class.java)
            i.putExtra("CuentaBancaria", lblCuenta.text.toString())
            startActivity(i)
        }
    }

    public fun externas(){
        btnExternas.setOnClickListener(){
            val i:Intent = Intent(this, TransferenciaExterna::class.java)
            i.putExtra("CuentaBancaria", lblCuenta.text.toString())
            startActivity(i)
        }
    }

    public fun cerrarSesion(){
        btnTerminar.setOnClickListener(){
            finish()
        }
    }
}