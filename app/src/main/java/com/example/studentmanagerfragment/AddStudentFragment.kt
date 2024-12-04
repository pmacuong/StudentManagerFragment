package com.example.studentmanagerfragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.widget.Button
import android.widget.EditText

class AddStudentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameEditText = view.findViewById<EditText>(R.id.edit_text_name)
        val idEditText = view.findViewById<EditText>(R.id.edit_text_id)
        val addButton = view.findViewById<Button>(R.id.button_add)

        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()
            if (name.isNotEmpty() && id.isNotEmpty()) {
                val action = AddStudentFragmentDirections.actionAddStudentFragmentToStudentListFragment(name, id)
                findNavController().navigate(action)
            }
        }
    }
}