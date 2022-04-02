package ax.ha.it.aa.tipcalculator

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import ax.ha.it.aa.tipcalculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI(binding.root.rootView)
        supportActionBar?.hide()
        binding.tipcalcCalculate.setOnClickListener {
            if (binding.editTipInput.editText?.text.toString().isNotBlank()
                && binding.editPercentInput.editText?.text.toString().isNotBlank()) {
                run {
                    binding.textTipCalcResult.text = String.format("Total %s",
                        Math.round(
                            (binding.editTipInput.editText?.text.toString().toDouble() *
                                    (binding.editPercentInput.editText?.text.toString().toDouble() / 100 + 1)
                            ) * 100
                        ) / 100.0
                    )
                    binding.editTipInput.clearFocus()
                    binding.editPercentInput.clearFocus()
                    hideKeyboard(this, binding.root.rootView)
                }
            }
        }
        binding.editTipInput.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus) {
                hideKeyboard(this, binding.root.rootView)
            }
        }
    }
    private fun setupUI(view: View) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { v, event ->
                // v.performClick()
                hideKeyboard(this, v)
                v.clearFocus()
                false
            }
        }
        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUI(innerView)
            }
        }
    }
    private fun hideKeyboard(context: Context, view: View) {
        val inputMethodManager: InputMethodManager =
            context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }

}
