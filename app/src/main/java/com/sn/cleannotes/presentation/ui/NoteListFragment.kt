package com.sn.cleannotes.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sn.cleannotes.databinding.FragmentNoteListBinding
import com.sn.cleannotes.framework.viewmodel.NoteListViewModel
import com.sn.cleannotes.presentation.adapter.NoteListAdapter
import com.sn.core.data.Note
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteListFragment : Fragment() {
    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModels<NoteListViewModel>()
    private lateinit var noteListAdapter: NoteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        noteListAdapter = NoteListAdapter { note, toBeDelete ->
            if (!toBeDelete)
                navigateToNoteDetails(note.id)
            else
                deleteNote(note)
        }
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
            collectDeletedNoteState()
        }
    }

    private fun getAllNotes(binding: FragmentNoteListBinding) {
        lifecycleScope.launchWhenResumed {
            viewModel.getAllNotesState.collect {
                if (!it.isNullOrEmpty() && it.size > 0){
                    binding.let { view ->
                        view.progressBar.visibility = View.GONE
                        view.tvEmpty.visibility = View.GONE
                        view.rvNoteList.visibility = View.VISIBLE
                    }
                    noteListAdapter.differ.submitList(it.sortedByDescending { sort -> sort.updateTime })
                }else{
                    binding.let { view ->
                        view.progressBar.visibility = View.GONE
                        view.rvNoteList.visibility = View.GONE
                        view.tvEmpty.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun deleteNote(note: Note) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete note")
            .setMessage("Are you sure you want to delete this note?")
            .setPositiveButton("Yes") { _, _ ->
                viewModel.fetchDeleteNote(note)
            }.setNegativeButton("No") { _, _ -> }
            .create()
            .show()
    }

    private fun collectDeletedNoteState() {
        lifecycleScope.launch {
            viewModel.getDeletedNote.collect {
                if (it) {
                    Toast.makeText(
                        requireContext(),
                        "Deleted note is successful",
                        Toast.LENGTH_SHORT
                    ).show()
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