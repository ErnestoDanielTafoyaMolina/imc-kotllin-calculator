package com.example.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.imc.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {

    //iniciamos mas tarde las variables model y binding para usarlas despues durante la ejecucion
    private lateinit var model:MainActivityViewModel
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //creamos el inflate para la vista y la ponemos en el content view
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        //asignamos el valor a nuestro model
        model = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        //el teclado va a ser numerico
        binding.edtUserHeight.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        binding.edtUserWeight.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

        binding.tvUserIMC.text=""
        //observamos lo que haya en el text vire de IMC


        //observamos  lo que haya en el text view de categoria
        model.userCategory.observe(this,){
            val category = it.toString()
            val username = model.getUsernameValue()
            binding.tvUserCategory.text="$username tienes: $category"
        }
        model.userIMC.observe(this,){
            val username = model.getUsernameValue()
            val IMC = it.toString()
            binding.tvUserIMC.text="$username tu IMC es de $IMC"
            if(model.getUserIMCValue()==0.0f){
                binding.tvUserIMC.text = ""
                binding.tvUserCategory.text = ""
            }
        }

        //ejecutamos el calculo del IMC
        binding.btnCalculateIMC?.setOnClickListener {
            calculateIMC()
        }
        binding.btnReset?.setOnClickListener {
            model.setUserIMCValue(0.0f)
            model.setUserCategoryValue("")
            model.setUsernameValue("")
            binding.tvUserIMC.text=""
            binding.tvUserCategory.text=""
            binding.edtUserName.setText("")
            binding.edtUserHeight.setText("")
            binding.edtUserWeight.setText("")
        }


    }

    private fun calculateIMC(){
        //obtenemos los valores de los input y los convertimos a float
        var username = binding.edtUserName.text.toString()
        val height = binding.edtUserHeight.text.toString().toFloatOrNull()
        val weight = binding.edtUserWeight.text.toString().toFloatOrNull()

        // verificamos que no vengan vacios, si vienen vacios pedimos que sean llenados
        if (height == null || weight == null || username == null) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
        } else {

            //si estan llenados, se guardan los datos para cuando volteamos el dispositivo
            model.setUsernameValue(username)
            model.setUserHeightValue(height)
            model.setUserWeightValue(weight)

            //calculamos el indice de masa corporal
            val imc = weight  / (height * height)
            //guardamos ese dato para no perderlo
            model.setUserIMCValue(imc)

            //con base al indice de masa corporal, regresamos el estado de la persona
            val weightCategory = when {
                imc <= 15.0 -> "muy severo bajo peso"
                imc <= 16.0 -> "severo bajo peso"
                imc <= 18.5 -> "bajo peso"
                imc <= 25.0 -> "peso saludable (normal)"
                imc <= 30.0 -> "sobrepeso"
                imc <= 35.0 -> "obeso clase I (moderadamente obeso)"
                imc <= 40.0 -> "obeso clase II (severamente obeso)"
                else -> "obeso clase III (obesidad mórbida)"
            }
            //guardamos ese dato de la persona
            model.setUserCategoryValue(weightCategory)

        }
    }

}