package br.edu.ifsp.dmo.diario.ui.diaryEntry

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.diario.data.repository.DiaryEntryRepository
import br.edu.ifsp.dmo.diario.databinding.ActivityDiaryEntryBinding

class DiaryEntryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiaryEntryBinding
    private lateinit var viewModel: DiaryEntryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDiaryEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = DiaryEntryViewModelFactory(DiaryEntryRepository(applicationContext))
        viewModel = ViewModelProvider(this, factory).get(DiaryEntryViewModel::class.java)

        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {

        binding.buttonSetDate.setOnClickListener{
            //initDatePickerDialog()
        }

        binding.buttonSetTime.setOnClickListener{
            //initDatePickerDialog()
        }

        binding.buttonSave.setOnClickListener {

            val title = binding.edittextTitle.text.toString()
            val location = binding.edittextLocation.text.toString()
            val date = binding.textviewDate.text.toString()
            val time = binding.textviewTime.text.toString()
            val text = binding.edittextEntry.text.toString()

            if (title.isEmpty() || title.isBlank()){
                Toast.makeText(this, "Please insert a title", Toast.LENGTH_SHORT).show()
            } else if (text.isEmpty() || text.isBlank()){
                Toast.makeText(this, "Please insert a entry", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insertDiaryEntry(title, location, date, time, text)
            }
        }
    }


    private fun setupObservers() {
        viewModel.saved.observe(this, Observer {
            if (it){
                Toast.makeText(this, "Entry successfully added.",
                    Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Entry could not be added.", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.isUpdate.observe(this, Observer {
            if (it){
                binding.buttonSave.text = "update"
            }
        })

        viewModel.title.observe(this, Observer {
            binding.edittextTitle.setText(it)})

        viewModel.location.observe(this, Observer {
            binding.edittextLocation.setText(it)
        })

        viewModel.date.observe(this, Observer {
            binding.textviewDate.setText(it)
        })

        viewModel.time.observe(this, Observer {
            binding.textviewTime.setText(it)
        })

        viewModel.text.observe(this, Observer {
            binding.textviewEntry.setText(it)
        })

    }
}