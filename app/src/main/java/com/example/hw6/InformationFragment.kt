package com.example.hw6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.hw6.Const.CONST_ID
import com.example.hw6.databinding.FragmentContactsBinding
import com.example.hw6.databinding.FragmentInformationBinding


class InformationFragment(intId: Int) : Fragment(R.layout.fragment_information) {
    private var binding: FragmentInformationBinding? = null
    private var idFromContact = intId
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            firstName.text = Container.contactList[idFromContact - 1].firstName
            secondName.text = Container.contactList[idFromContact - 1].secondName
            phoneNumber.text = Container.contactList[idFromContact - 1].phoneHumber
            saveChangesButton.setOnClickListener {
                if (editFirstName.text.toString().isNotEmpty())
                    Container.contactList[idFromContact - 1].firstName =
                        editFirstName.text.toString()
                if (editSecondName.text.toString().isNotEmpty())
                    Container.contactList[idFromContact - 1].secondName =
                        editSecondName.text.toString()
                if (editPhoneNumber.text.toString().isNotEmpty())
                    Container.contactList[idFromContact - 1].phoneHumber =
                        editPhoneNumber.text.toString()

                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ContactsFragment())
                    .addToBackStack("")
                    .commit()

            }


        }
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ContactsFragment())
                    .addToBackStack("")
                    .commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


}
