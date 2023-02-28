package com.example.mhyrfoodapp.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mhyrfoodapp.data.models.home.ResponseFoodsList.*
import com.example.mhyrfoodapp.databinding.RandomFoodItemBinding
import javax.inject.Inject

class FoodsAdapter @Inject constructor() :
    RecyclerView.Adapter<FoodsAdapter.ViewHolder>() {

    //Binding
    private lateinit var bind: RandomFoodItemBinding

    //Data to bind
    private var foodList = emptyList<Meal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        bind = RandomFoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(foodList[position])
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    inner class ViewHolder() : RecyclerView.ViewHolder(bind.root) {
        fun bindData(item: Meal) {
            //Init views
            bind.apply {
                txtFoodName.text = item.strMeal
                txtNationality.text = item.strArea
                imgFood.load(item.strMealThumb) {
                    crossfade(true)
                    crossfade(300)
                }
            }
        }
    }

    private var onItemClickListener: ((Meal) -> Unit)? = null

    fun setOnItemClickListener(listener: (Meal) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<Meal>) {
        val foodsDiffUtils = CategoriesDiffUtils(foodList, data)
        val diffUtils = DiffUtil.calculateDiff(foodsDiffUtils)
        foodList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class CategoriesDiffUtils(
        private var oldItem: List<Meal>,
        private var newItem: List<Meal>
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