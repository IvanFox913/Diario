package br.edu.ifsp.dmo.diario.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.dmo.diario.data.repository.DiaryEntryRepository
import br.edu.ifsp.dmo.diario.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.diario.ui.adapter.DiaryEntryAdapter
import br.edu.ifsp.dmo.diario.ui.diaryEntry.DiaryEntryActivity
import br.edu.ifsp.dmo.diario.ui.listeners.DiaryEntryItemClickListener
import br.edu.ifsp.dmo.diario.util.Constant

class MainActivity : AppCompatActivity(), DiaryEntryItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = DiaryEntryAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = MainViewModelFactory(DiaryEntryRepository(applicationContext))
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        setupRecyclerView()
        setupObservers()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkDatabase()
    }

    override fun clickOpenDiaryEntry(position: Int) {

        val diaryEntry = adapter.getDatasetItem(position)
        val mIntent = Intent(this, DiaryEntryActivity::class.java)
        mIntent.putExtra(Constant.DIARY_ENTRY_ID, diaryEntry.id)
        startActivity(mIntent)
    }

    override fun clickDeleteDiaryEntry(position: Int) {
        viewModel.deleteDiaryEntry(position.toLong())
    }

    private fun setupListeners() {
        binding.floatactionbuttonAdd.setOnClickListener{
            val mIntent = Intent(this, DiaryEntryActivity::class.java)
            startActivity(mIntent)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerviewDiaryEntries.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewDiaryEntries.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.diaryEntries.observe(this, Observer {
            adapter.submitDataset(it)
        })
    }
}