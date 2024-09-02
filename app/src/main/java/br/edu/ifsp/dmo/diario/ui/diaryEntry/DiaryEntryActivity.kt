package br.edu.ifsp.dmo.diario.ui.diaryEntry

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.diario.data.repository.DiaryEntryRepository
import br.edu.ifsp.dmo.diario.databinding.ActivityDiaryEntryBinding
import br.edu.ifsp.dmo.diario.util.Constant
import br.edu.ifsp.dmo.diario.util.DateConversor
import java.util.Calendar
import java.util.Date

class DiaryEntryActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityDiaryEntryBinding
    private lateinit var viewModel: DiaryEntryViewModel

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDiaryEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = DiaryEntryViewModelFactory(DiaryEntryRepository(applicationContext))
        viewModel = ViewModelProvider(this, factory).get(DiaryEntryViewModel::class.java)

        handleBundle()
        setupUi()
        setupListeners()
        setupObservers()
    }

    private fun handleBundle(){
        if (intent.hasExtra(Constant.DIARY_ENTRY_ID)){
            val id = intent.getLongExtra(Constant.DIARY_ENTRY_ID, -1)
            viewModel.showEvent(id)
        }
    }

    private fun setupUi(){
        binding.textviewDate.text = DateConversor.dateFormatToDateString(Date())
        binding.textviewTime.text = DateConversor.dateFormatToTimeString(Date())
    }

    private fun setupListeners() {

        binding.buttonSetDate.setOnClickListener{
            DatePickerDialog(
                this,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
            ).show()
        }

        binding.buttonSetTime.setOnClickListener{
            TimePickerDialog(
                this,
                this,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
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

        binding.buttonCancel.setOnClickListener{
            finish()
        }
    }

    private fun setupObservers() {
        viewModel.saved.observe(this, Observer {
            if (it){
                Toast.makeText(this, "Entry successfully added.", Toast.LENGTH_LONG).show()
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
            binding.edittextEntry.setText(it)
        })
    }

    override fun onDateSet(datepicker: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year, month, dayOfMonth)
        binding.textviewDate.text = DateConversor.dateFormatToDateString(calendar.time)
    }

    override fun onTimeSet(timepicker: TimePicker, hourOfDay: Int, minute: Int) {
        calendar.apply {
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
        }
        binding.textviewTime.text = DateConversor.dateFormatToTimeString(calendar.time)
    }
}