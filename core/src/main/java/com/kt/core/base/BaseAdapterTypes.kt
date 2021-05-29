package com.kt.core.base
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView




const val PRIMARYV = 0
const val SECONDARY = 1
const val typeRText = 0
const val typeLText = 1
const val typeRImage = 2
const val typeLImage = 3
const val typeRFile = 4
const val typeLFile = 5

abstract class BaseAdapterTypes<T>(
    @LayoutRes private val typeRTextLayout: Int,
    @LayoutRes private val typeLTextLayout: Int=0,
    @LayoutRes private val typeRImageLayout: Int=0,
    @LayoutRes private val typeLImageLayout: Int=0,
    @LayoutRes private val typeRFileLayout: Int=0,
    @LayoutRes private val typeLFileLayout: Int=0
) : RecyclerView.Adapter<BaseAdapterTypes<T>.ViewHolder>(), Filterable {

  protected var items: List<T> = listOf()
  protected var mutableItems = items.toMutableList()
  private var filteredList: MutableList<T> = mutableListOf()
  private var clickListener: ((clickedView: View, item: T, position: Int) -> Unit)? = null

  override fun getItemCount() = mutableItems.size
  //override fun getItemCount() = 10
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
      when (viewType) {
       // SECONDARY -> ViewHolder(parent.inflate(secondaryLayout))
        typeRText -> ViewHolder( LayoutInflater.from(parent.context).inflate(
          typeRTextLayout,
              parent,
              false
          ))
        typeLText -> ViewHolder( LayoutInflater.from(parent.context).inflate(
          typeLTextLayout,
          parent,
          false
        ))
        typeRImage -> ViewHolder( LayoutInflater.from(parent.context).inflate(
          typeRImageLayout,
          parent,
          false
        ))
        typeLImage -> ViewHolder( LayoutInflater.from(parent.context).inflate(
          typeLImageLayout,
          parent,
          false
        ))
        typeRFile -> ViewHolder( LayoutInflater.from(parent.context).inflate(
          typeRFileLayout,
          parent,
          false
        ))
        else -> ViewHolder( LayoutInflater.from(parent.context).inflate(
          typeLFileLayout,
            parent,
            false
        ))
      }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.setContent(mutableItems[position])
  }

  @Suppress("UNCHECKED_CAST")
  override fun getFilter() = object : Filter() {
    override fun performFiltering(constraint: CharSequence): FilterResults {
      val charString = constraint.toString()
      mutableItems = if (charString.isEmpty()) items.toMutableList() else filteredList

      val filterResults = FilterResults()
      filterResults.values = mutableItems
      return filterResults
    }

    override fun publishResults(constraint: CharSequence, results: FilterResults) {
      mutableItems = results.values as MutableList<T>
      notifyDataSetChanged()
    }
  }

  /**
   * Set on view click listener
   * @param clickListener (clickedView, clickedItem, clickedPosition)
   */
  fun setOnClickListener(clickListener: (clickedView: View, item: T, position: Int) -> Unit) {
    this.clickListener = clickListener
  }

  /**
   * Set filtered data
   * @param filteredList
   */
  fun submitFilteredList(filteredList: MutableList<T>) {
    this.filteredList = filteredList
  }

  /**
   * Get items
   */
  fun getCurrentItems() = items
   fun getCurrentData()=mutableItems

  /**
   * Get item by position
   * @param position
   */
  fun getItem(position: Int) = mutableItems[position]

  /**
   * Replace current items with new items
   * @param items New items to fill
   */
  fun fill(items: List<T>) {
    this.items = items
    mutableItems.clear()
    mutableItems.addAll(items)
    notifyDataSetChanged()
  }

  /**
   * Add items to end of list
   * @param items New items to add
   */
  fun addItems(items: List<T>) {
    this.items = items
    mutableItems.addAll(items)
    notifyDataSetChanged()
  }
  fun addItemsToTop(items: List<T>) {
    this.items = items
    mutableItems.addAll(0,items)
    notifyDataSetChanged()
  }

  /**
   * Add item to end of list
   * @param item New item to add
   */
  fun addItem(item: T) {
    mutableItems.add(item)
    notifyItemInserted(mutableItems.size - 1)
  }

  /**
   * Add item to certain position in list
   * @param position Where to add item
   * @param item New item to add
   */
  fun addItem(position: Int, item: T) {
    mutableItems.add(position, item)
    notifyItemInserted(mutableItems.size - 1)
  }
  fun updateItem(position: Int, item: T) {
    mutableItems.set(position, item)
  // notifyItemChanged(mutableItems.size - 1)
  }


  /**
   * Remove item from list
   * @param position Item position
   * @return List size
   */
  fun removeItem(position: Int): Int {
    mutableItems.removeAt(position)
    notifyItemRemoved(position)
    return mutableItems.size
  }


  /**
   * Remove all items from list
   */
  fun clear() {
    items = listOf()
    mutableItems.clear()
    notifyDataSetChanged()
  }

  /**
   * Set view content
   * @param itemView View
   * @param item Item object
   * @param position Item position
   */
  protected abstract fun setContent(itemView: View, item: T, position: Int)

  /**
   * On view clicked
   * @param view Clicked view
   * @param item Clicked item
   * @param position Clicked position
   */

  protected fun onViewClicked(view: View, item: T, position: Int) {
    clickListener?.invoke(view, item, position)
  }

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setContent(item: T) =
        this@BaseAdapterTypes.setContent(itemView, item,adapterPosition)
  }
}