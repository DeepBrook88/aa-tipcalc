package ax.ha.it.aa.tipcalculator

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import ax.ha.it.aa.tipcalculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tipcalcCalculate.setOnClickListener {
            if (binding.editTipInput.text.toString().isNotBlank()
                && binding.editPercentInput.text.toString().isNotBlank()) {
                run {
                    binding.textTipCalcResult.text = String.format("Total: %s",(binding.editTipInput.text.toString().toDouble() *
                            (binding.editPercentInput.text.toString().toDouble() / 100 + 1)))
                    binding.editTipInput.clearFocus()
                    binding.editPercentInput.clearFocus()
                    hideKeyboard(this, binding.root.rootView)
                }
            }
        }
    }
    private fun hideKeyboard(context: Context, view: View) {
        val inputMethodManager: InputMethodManager =
            context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
}