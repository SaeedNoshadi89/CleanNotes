package com.sn.cleannotes.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sn.cleannotes.databinding.FragmentNoteListBinding
import com.sn.cleannotes.framework.viewmodel.NoteListViewModel
import com.sn.cleannotes.presentation.adapter.NoteListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NoteListFragment : Fragment() {
    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModels<NoteListViewModel>()

    @Inject
    lateinit var noteListAdapter: NoteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        noteListAdapter = NoteListAdapter( {
            navigateToNoteDetails(it)
        })
        viewModel.getAllNotes()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            btnAddNote.setOnClickListener {
                navigateToNoteDetails()
            }
            rvNoteList.apply {
                adapter = noteListAdapter
            }
            getAllNotes(this)
        }
    }

    private fun getAllNotes(binding: FragmentNoteListBinding) {
        lifecycleScope.launch {
            viewModel.getAllNotesState().collect {
                it.let { noteList ->
                    binding.let { view ->
                        view.progressBar.visibility = View.GONE
                        view.rvNoteList.visibility = View.VISIBLE
                    }
                    noteListAdapter.differ.submitList(noteList.sortedByDescending { sort -> sort.updateTime })
                }
            }
        }
    }

    private fun navigateToNoteDetails(id: Long = 0L) {
        findNavController().navigate(
            NoteListFragmentDirections.actionNoteListFragmentToAddNoteFragment(
                id
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}