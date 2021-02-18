package com.sn.cleannotes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sn.cleannotes.databinding.FragmentNoteListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListFragment : Fragment() {
    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            btnAddNote.setOnClickListener {
                navigateToNoteDetails()
            }
        }
    }
    private fun navigateToNoteDetails(id: Long = 0L){
        findNavController().navigate(NoteListFragmentDirections.actionNoteListFragmentToAddNoteFragment(id))
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}