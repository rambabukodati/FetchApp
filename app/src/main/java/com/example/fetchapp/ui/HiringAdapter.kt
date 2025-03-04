package com.example.fetchapp.ui
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchapp.R
import com.example.fetchapp.data.data.Item
import com.example.fetchapp.databinding.ListItemBinding
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class HiringAdapter @Inject constructor() : RecyclerView.Adapter<HiringAdapter.HiringViewHolder>() {

    companion object {
        private val callback = object:DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

        }
    }

    private val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HiringViewHolder =
        HiringViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
           R.layout.list_item, parent, false))


    fun setList(list: List<Item>) {
        differ.submitList(list)
    }
    override fun onBindViewHolder(holder: HiringViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() :Int = differ.currentList.size


    class HiringViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Item) {
            binding.apply {
                id.text = item.id.toString()
                name.text = item.name
                listId.text = item.listId.toString()
            }
        }
    }

}

