package com.example.hw6

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw6.Const.CONST_ID
import com.example.hw6.Const.CONST_INTENT_KEY

import com.example.hw6.databinding.FragmentContactsBinding


class ContactsFragment : Fragment(R.layout.fragment_contacts) {
    private var binding: FragmentContactsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactsBinding.inflate(inflater, container, false)
        binding?.run {
            initAdapter()
        }
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        return binding!!.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentContactsBinding.inflate(layoutInflater)
        initAdapter()
    }

    private fun initAdapter() {
        val adapter = ContactsAdapter() {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, InformationFragment(it))
                .addToBackStack("")
                .commit()
        }
        binding?.recyclerContacts?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.recyclerContacts?.adapter = adapter
        (binding?.recyclerContacts?.adapter as? ContactsAdapter)?.setList(Container.contactList)
    }


}
