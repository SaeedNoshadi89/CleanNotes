package com.sn.cleannotes.presentation.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sn.cleannotes.R
import com.sn.cleannotes.databinding.FragmentAddNoteBinding
import com.sn.cleannotes.framework.viewmodel.AddNoteViewModel
import com.sn.core.data.Note
import com.sn.core.util.Extension.checkStringNotNullOrEmpty
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModels<AddNoteViewModel>()
    private val args by navArgs<AddNoteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            setHasOptionsMenu(true)
            btnCheck.setOnClickListener {
                if (!etTitle.text.toString()
                        .checkStringNotNullOrEmpty() or !etContent.text.toString()
                        .checkStringNotNullOrEmpty()
                ) {
                    saveNote()
                } else {
                    findNavController().popBackStack()
                }
            }
            fetchCurrentNote(args.noteId)
            collectCurrentState(this)
            collectDeletedNoteState()
        }
    }

    private fun FragmentAddNoteBinding.saveNote() {
        Note(title = "", content = "", creationTime = 0L, updateTime = 0L).also {
            it.title = etTitle.text.toString()
            it.content = etContent.text.toString()
            System.currentTimeMillis().apply {
                it.updateTime = this
                if (it.id == 0L) {
                    it.creationTime = this
                }
            }
        }.run {
            lifecycleScope.launch {
                viewModel.saveNote(this@run).collect {
                    if (it) {
                        Toast.makeText(requireContext(), "Done", Toast.LENGTH_LONG)
                            .show()
                        findNavController().popBackStack()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Something went wrong, Please try again",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> {
                if (context != null && args.noteId != 0L) {
                    deleteNote(args.noteId)
                }
            }
        }
        return true
    }

    private fun collectCurrentState(binding: FragmentAddNoteBinding) {
        lifecycleScope.launchWhenResumed {
            viewModel.getCurrentNote.collect {
                binding.apply {
                    etTitle.setText(it.title)
                    etContent.setText(it.content)
                }
            }
        }
    }

    private fun fetchCurrentNote(noteId: Long) {
        if (noteId != 0L)
            viewModel.fetchCurrentNote(noteId)
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
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun deleteNote(id: Long) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete note")
            .setMessage("Are you sure you want to delete this note?")
            .setPositiveButton("Yes") { _, _ ->
                viewModel.fetchDeleteNote(id)
            }.setNegativeButton("No") { _, _ -> }
            .create()
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}