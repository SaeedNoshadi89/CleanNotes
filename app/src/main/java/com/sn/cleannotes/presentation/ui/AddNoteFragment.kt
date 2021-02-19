package com.sn.cleannotes.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sn.cleannotes.databinding.FragmentAddNoteBinding
import com.sn.cleannotes.framework.viewmodel.NoteViewModel
import com.sn.core.data.Note
import com.sn.core.util.Extension.checkStringNotNullOrEmpty
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModels<NoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.apply {
            btnCheck.setOnClickListener {
                if (!etTitle.text.toString().checkStringNotNullOrEmpty() or !etContent.text.toString().checkStringNotNullOrEmpty()){
                    saveNewNote()
                }else{
                    findNavController().popBackStack()
                }
            }
            collectSavedState()
        }
    }

    private fun collectSavedState() {
        lifecycleScope.launch {
            viewModel.getSavedState().collect {
                if (it){
                    Toast.makeText(requireContext(), "Done", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                }else{
                    Toast.makeText(requireContext(), "Something went wrong, Please try again", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun FragmentAddNoteBinding.saveNewNote() {
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
            viewModel.saveNote(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}