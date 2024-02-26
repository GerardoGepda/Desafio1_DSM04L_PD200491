package com.example.desafio1_dsm04l_pd200491.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.desafio1_dsm04l_pd200491.databinding.FragmentHomeBinding

lateinit var nombre: EditText;
lateinit var n1: EditText;
lateinit var n2: EditText;
lateinit var n3: EditText;
lateinit var n4: EditText;
lateinit var n5: EditText;
lateinit var btnPromedio: Button;
lateinit var tvEstudiante: TextView;
lateinit var tvPromedio: TextView;

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        //calculo de promedio
        nombre = binding.eTxtName;
        n1 = binding.eTxtN1;
        n2 = binding.eTxtN2;
        n3 = binding.eTxtN3;
        n4 = binding.eTxtN4;
        n5 = binding.eTxtN5;
        tvEstudiante = binding.txtEstudiante;
        tvPromedio = binding.txtPromedio;
        btnPromedio = binding.btnCalcular;

        btnPromedio.setOnClickListener {
            val eTextList = listOf<EditText>(nombre, n1, n2, n3, n4, n5);
            if (!areAllEditTextsFilled(eTextList)) {
                Toast.makeText(requireContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }
            var suma = n1.text.toString().toDouble() + n2.text.toString().toDouble() + n3.text.toString().toDouble() + n4.text.toString().toDouble() + n5.text.toString().toDouble();
            var promedio: Double = suma/5;

            tvPromedio.setText("Promedio: ${promedio}");
            tvEstudiante.setText("Estudiante: ${nombre.text.toString()}")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun areAllEditTextsFilled(editTexts: List<EditText>): Boolean {
        for (editText in editTexts) {
            if (editText.text.toString().isEmpty()) {
                return false
            }
        }
        return true
    }

}