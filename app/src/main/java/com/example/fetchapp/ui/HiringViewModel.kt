package com.example.fetchapp.ui
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchapp.data.data.Item
import com.example.fetchapp.data.repository.HiringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.SortedMap
import javax.inject.Inject

@HiltViewModel
class HiringViewModel @Inject constructor(
    private val repository: HiringRepository
): ViewModel() {
    init {
        getItems()
    }
    private val _itemList = MutableLiveData<List<Item>>()
    val itemList:LiveData<List<Item>> get() = _itemList

   private fun getItems() {
       viewModelScope.launch (Dispatchers.IO){
          val items  =  repository.getItems()
           _itemList.postValue(getFilteredList(items))
       }
   }


    private fun getFilteredList( rawList:List<Item>):List<Item> {
        val filteredList = rawList.filter { !it.name.isNullOrEmpty() } //Remove null and empty name

        /* Sort names by considering lexicographical Eg: item 12, item 112.
       Also considered differences in name Eg: item 12, itea 12*/
        val sortlist = filteredList.sortedWith(::compareByCustom)

         /*Group by listid */
        val groupedAndSorted: SortedMap<Int, List<Item>> = sortlist.groupBy { it.listId }.toSortedMap(compareBy { it })
        /*Flatten the map to list */
        return flattenMapValues(groupedAndSorted)
    }

    private fun compareByCustom(item1: Item?, item2: Item?): Int {
        val (textPart1, numberPart1) = extractInt(item1?.name)
        val (textPart2, numberPart2) = extractInt(item2?.name)

        return if (textPart1 == textPart2) {
            numberPart1 - numberPart2
        } else if (textPart1 > textPart2) {
            -1
        } else 1
    }

    private fun extractInt(s: String?): Pair<String, Int> {
        val regex = "(\\D+)(\\d+)".toRegex()
        val matchResult = s?.let { regex.find(it) }
        if (matchResult != null) {
            val (text, number) = matchResult.destructured
            return Pair(text, number.toInt())
        } else {
            return Pair("", -1)
        }
    }

    private fun flattenMapValues(map: SortedMap<Int, List<Item>>): List<Item> {
        return map.values.flatten()
    }

}


