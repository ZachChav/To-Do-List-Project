package com.gamecodeschool.todolistapp2

import DatabaseHelper
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var editTextTask: EditText
    private lateinit var listViewTasks: ListView
    private lateinit var taskListAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)
        editTextTask = findViewById(R.id.editTextTask)
        listViewTasks = findViewById(R.id.listViewTasks)

        loadTasks()
    }

    private fun loadTasks() {
        val tasks = dbHelper.getAllTasks()
        taskListAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tasks)
        listViewTasks.adapter = taskListAdapter
    }

    fun addTask(view: android.view.View) {
        val task = editTextTask.text.toString().trim()
        if (task.isNotEmpty()) {
            if (dbHelper.insertTask(task)) {
                editTextTask.text.clear()
                loadTasks()
            } else {
                Toast.makeText(this, "Failed to insert task", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Task can't be empty", Toast.LENGTH_SHORT).show()
        }
    }
}
