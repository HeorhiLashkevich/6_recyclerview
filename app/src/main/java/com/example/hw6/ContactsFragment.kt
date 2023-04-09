package com.example.hw6

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.hw6.databinding.FragmentContactsBinding
import java.util.*
import kotlin.collections.ArrayList


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
            initAdapter(Container.contactList)
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
        binding?.searchBar?.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterFirsOfSecondtName(newText)
                return true
            }

        })
    }

    private fun filterFirsOfSecondtName(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<Contact>()
            for (i in Container.contactList) {
                if (i.firstName.contains(query, ignoreCase = true) || i.secondName.contains(
                        query,
                        ignoreCase = true
                    )
                ) {
                    filteredList.add(i)
                }
            }
            if (filteredList.isNotEmpty())
                initAdapter(filteredList)
        }
    }


    private fun initAdapter(list: java.util.ArrayList<Contact>) {
        val adapter = ContactsAdapter({
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, InformationFragment(it))
                .addToBackStack("")
                .commit()
        }){
            list.removeAt(it-1)
            initAdapter(list)

        }


        binding?.recyclerContacts?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.recyclerContacts?.adapter = adapter
        (binding?.recyclerContacts?.adapter as? ContactsAdapter)?.setList(list)
    }


}
