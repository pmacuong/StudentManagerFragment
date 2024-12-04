package com.example.studentmanagerfragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import android.widget.Button
import android.widget.EditText

class EditStudentFragment : Fragment() {
    private val args: EditStudentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameEditText = view.findViewById<EditText>(R.id.edit_text_name)
        val idEditText = view.findViewById<EditText>(R.id.edit_text_id)
        val saveButton = view.findViewById<Button>(R.id.button_save)

        val position = args.position
        val name = args.studentName
        val id = args.studentId

        nameEditText.setText(name)
        idEditText.setText(id)

        saveButton.setOnClickListener {
            val newName = nameEditText.text.toString()
            val newId = idEditText.text.toString()
            if (newName.isNotEmpty() && newId.isNotEmpty()) {
                val action = EditStudentFragmentDirections.actionEditStudentFragmentToStudentListFragment(position, newName, newId)
                findNavController().navigate(action)
            }
        }
    }
}