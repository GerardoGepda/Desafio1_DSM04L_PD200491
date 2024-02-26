package com.example.desafio1_dsm04l_pd200491.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.desafio1_dsm04l_pd200491.databinding.FragmentNotificationsBinding

lateinit var num1: EditText;
lateinit var num2: EditText;
lateinit var btnResult: Button;
lateinit var rgroup: RadioGroup;
lateinit var txtResult: TextView;

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        //calculando
        num1 = binding.eTxtNum1;
        num2 = binding.eTxtNum2;
        rgroup = binding.radioGroup;
        txtResult = binding.txtResult;
        btnResult = binding.btnResult;

        btnResult.setOnClickListener {
            val eTextList = listOf<EditText>(num1, num2);
            if (!areAllEditTextsFilled(eTextList) || rgroup.checkedRadioButtonId == -1) {
                Toast.makeText(requireContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }

            val rbtn: RadioButton = binding.root.findViewById(rgroup.checkedRadioButtonId);

            when (rbtn.text) {
                "Sumar" -> txtResult.setText("Resultado: ${num1.text.toString().toDouble() + num2.text.toString().toDouble()}");
                "Restar" -> txtResult.setText("Resultado: ${num1.text.toString().toDouble() - num2.text.toString().toDouble()}");
                "Multiplicar" -> txtResult.setText("Resultado: ${num1.text.toString().toDouble() * num2.text.toString().toDouble()}");
                "Dividir" -> {
                    if (num2.text.toString().toDouble() == 0.0 ) {
                        txtResult.setText("Imposible dividir entre cero.");
                        return@setOnClickListener;
                    }
                    txtResult.setText("Resultado: ${(num1.text.toString().toDouble() / num2.text.toString().toDouble())}");
                };
            }
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