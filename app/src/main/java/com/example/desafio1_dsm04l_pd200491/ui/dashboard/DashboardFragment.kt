package com.example.desafio1_dsm04l_pd200491.ui.dashboard

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
import com.example.desafio1_dsm04l_pd200491.databinding.FragmentDashboardBinding

lateinit var nombre: EditText;
lateinit var salario: EditText;
lateinit var btnSalary: Button;

lateinit var txtSalario: TextView;
lateinit var txtIsss: TextView;
lateinit var txtAfp: TextView;
lateinit var txtRenta: TextView;
lateinit var txtSalarioN: TextView;

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        nombre = binding.eTxtNombre;
        salario = binding.eTxtSalary;
        btnSalary = binding.btnSalary;

        txtSalario = binding.txtSalary;
        txtAfp = binding.txtAfp;
        txtIsss = binding.txtIsss;
        txtRenta = binding.txtRenta
        txtSalarioN = binding.txtSalaryN;

        btnSalary.setOnClickListener {
            val eTxtList = listOf<EditText>(nombre, salario);
            if (!areAllEditTextsFilled(eTxtList)) {
                Toast.makeText(requireContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }

            var isss = salario.text.toString().toDouble() * 0.03;
            var afp = salario.text.toString().toDouble() * 0.04;
            var renta = salario.text.toString().toDouble() * 0.05;
            var neto = salario.text.toString().toDouble() - isss - afp - renta;

            txtSalario.setText("Salario base: ${salario.text.toString()}");
            txtIsss.setText("ISSS (3%): ${isss}");
            txtAfp.setText("AFP (4%): ${afp}");
            txtRenta.setText("Renta (5%): ${renta}");
            txtSalarioN.setText("Salario Neto: ${neto}");

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