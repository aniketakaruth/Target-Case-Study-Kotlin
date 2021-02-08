package com.target.targetcasestudy.ui.payment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.Validations

/**
 * Dialog that displays a minimal credit card entry form.
 *
 * Your task here is to enable the "submit" button when the credit card number is valid and
 * disable the button when the credit card number is not valid.
 *
 * You do not need to input any of your actual credit card information. See `Validations.kt` for
 * info to help you get fake credit card numbers.
 *
 * You do not need to make any changes to the layout of this screen (though you are welcome to do
 * so if you wish).
 */
class PaymentDialogFragment : DialogFragment() {

    private lateinit var submitButton: Button
    private lateinit var creditCardInput: TextInputEditText
    private lateinit var cardInputLayout: TextInputLayout

    private val validators = Validations()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.dialog_payment, container, false)

        submitButton = root.findViewById(R.id.submit)
        creditCardInput = root.findViewById(R.id.card_number)
        cardInputLayout =root.findViewById(R.id.card_number_container)
        val cancelButton: Button = root.findViewById(R.id.cancel)
        submitButton.isEnabled = false


        //Text Change listener to observe  the text change and enable the submit button

        creditCardInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (validators.checkValidity(s.toString())) {
                    submitButton.isEnabled = true
                    cardInputLayout.isErrorEnabled = false
                    cardInputLayout.error = ""
                } else {
                    submitButton.isEnabled = false
                    cardInputLayout.isErrorEnabled = true
                    cardInputLayout.error = "Please enter valid card number"
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })



        cancelButton.setOnClickListener { dismiss() }
        submitButton.setOnClickListener { dismiss() }


        return root
    }

}