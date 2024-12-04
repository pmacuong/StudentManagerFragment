package com.example.studentmanagerfragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    private val students: MutableList<StudentModel>,
    private val itemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]
        holder.textStudentName.text = student.studentName
        holder.textStudentId.text = student.studentId

        holder.itemView.setOnClickListener {
            itemClickListener(position)
        }

    }

    override fun getItemCount(): Int = students.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
        val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
    }
}