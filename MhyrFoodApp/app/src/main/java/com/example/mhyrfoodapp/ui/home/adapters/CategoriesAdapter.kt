package com.example.mhyrfoodapp.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mhyrfoodapp.R
import com.example.mhyrfoodapp.data.models.home.ResponseCategoryList
import com.example.mhyrfoodapp.data.models.home.ResponseCategoryList.*
import com.example.mhyrfoodapp.databinding.CategoryItemBinding
import javax.inject.Inject

class CategoriesAdapter @Inject constructor() :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    //Binding
    private lateinit var bind: CategoryItemBinding

    //Data to bind
    private var foodList = emptyList<Category>()

    //variable to get selected item position
    private var selectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        bind = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(foodList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    inner class ViewHolder() : RecyclerView.ViewHolder(bind.root) {
        fun bindData(item: Category) {
            //Init views
            bind.apply {
                txtCategoryName.text = item.strCategory
                imgCategory.load(item.strCategoryThumb) {
                    crossfade(true)
                    crossfade(300)
                }
                //When item clicked
                root.setOnClickListener {
                    selectedItem = adapterPosition
                    notifyDataSetChanged()
                    onItemClickListener?.let {
                        it(item)
                    }
                }
                //Change item color when selected
                if(selectedItem == adapterPosition) {
                    root.setBackgroundResource(R.drawable.circle_bg_border_red)
                }
                else {
                    root.setBackgroundResource(R.drawable.circle_bg_white)
                }
            }
        }
    }

    private var onItemClickListener: ((Category) -> Unit)? = null

    fun setOnItemClickListener(listener: (Category) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<Category>) {
        val categoriesDiffUtils = CategoriesDiffUtils(foodList, data)
        val diffUtils = DiffUtil.calculateDiff(categoriesDiffUtils)
        foodList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class CategoriesDiffUtils(
        private var oldItem: List<Category>,
        private var newItem: List<Category>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

    }
}