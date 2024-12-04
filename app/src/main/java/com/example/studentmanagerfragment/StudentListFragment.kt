package com.example.studentmanagerfragment

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class StudentListFragment : Fragment() {

    private lateinit var studentAdapter: CustomAdapter
    private var students = mutableListOf(
        StudentModel("Nguyễn Văn An", "SV001"),
        StudentModel("Trần Thị Bảo", "SV002"),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_students)
        studentAdapter = CustomAdapter(students) { position ->
            val action = StudentListFragmentDirections.actionStudentListFragmentToEditStudentFragment(position, students[position].studentName, students[position].studentId)
            findNavController().navigate(action)
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = studentAdapter

        registerForContextMenu(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_new -> {
                findNavController().navigate(R.id.action_studentListFragment_to_addStudentFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        Log.d("ContextMenu", "onCreateContextMenu called")
        requireActivity().menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menuInfo = item.menuInfo
        if (menuInfo is AdapterView.AdapterContextMenuInfo) {
            val info = menuInfo as AdapterView.AdapterContextMenuInfo
            return when (item.itemId) {
                R.id.edit -> {
                    Log.d("ContextMenu", "Edit action selected")
                    val action = StudentListFragmentDirections.actionStudentListFragmentToEditStudentFragment(
                        info.position,
                        students[info.position].studentName,
                        students[info.position].studentId
                    )
                    findNavController().navigate(action)
                    true
                }
                R.id.remove -> {
                    val student = students.removeAt(info.position)
                    studentAdapter.notifyItemRemoved(info.position)
                    Snackbar.make(requireView(), "Student deleted", Snackbar.LENGTH_LONG)
                        .setAction("Undo") {
                            students.add(info.position, student)
                            studentAdapter.notifyItemInserted(info.position)
                        }
                        .show()
                    true
                }
                else -> super.onContextItemSelected(item)
            }
        } else {
            return super.onContextItemSelected(item)
        }
    }
}