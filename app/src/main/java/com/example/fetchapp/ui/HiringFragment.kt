package com.example.fetchapp.ui
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.fetchapp.R
import com.example.fetchapp.databinding.FragmentHringBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiringFragment : BaseFragment<FragmentHringBinding>(R.layout.fragment_hring) {

    private val viewModel : HiringViewModel by activityViewModels()

    @Inject
    lateinit var adapter: HiringAdapter

    override fun setupFragementUI(view: View?) {
        setupRecyclerView()
        configureObservers()
    }

    private fun setupRecyclerView() {
        binding.items.adapter = adapter
    }

    private fun configureObservers() {
        viewModel.itemList.observe(viewLifecycleOwner) {
            adapter.setList(it)
            binding.progressBarCircular.isVisible = false
            binding.items.isVisible = true
        }
    }


}