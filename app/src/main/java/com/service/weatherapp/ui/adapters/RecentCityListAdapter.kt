package com.service.weatherapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.service.weatherapp.R
import com.service.weatherapp.room.recentcity.CityEntity
import com.service.weatherapp.ui.adapters.RecentCityListAdapter.CityItemViewHolder
import com.service.weatherapp.ui.interfaces.RecentCityItemClickListener

internal class RecentCityListAdapter(
    context: Context?,
    list: List<CityEntity>?,
    itemClickListener: RecentCityItemClickListener?
) :
    RecyclerView.Adapter<CityItemViewHolder>() {

    private var mCityList: List<CityEntity>? = null
    private val mInflater: LayoutInflater
    private var recentCityItemClickListener: RecentCityItemClickListener? = null

    init {
        mInflater = LayoutInflater.from(context)
        mCityList = list
        recentCityItemClickListener = itemClickListener
    }

    fun setData(list: List<CityEntity>?) {
        mCityList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityItemViewHolder {
        val view = mInflater.inflate(R.layout.city_item_view, parent, false)
        return CityItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityItemViewHolder, position: Int) {
        val city = mCityList?.get(position)
        holder.cityTV.text = city?.city_name
        holder.cityRoot.setOnClickListener {
            recentCityItemClickListener?.onItemClick(mCityList?.get(position))
        }
    }

    override fun getItemCount(): Int {
        return mCityList?.size ?: 0
    }

    inner class CityItemViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var cityTV: TextView
        var cityRoot: ConstraintLayout

        init {
            cityTV = itemView.findViewById(R.id.city_name)
            cityRoot = itemView.findViewById(R.id.item_view)
        }
    }

    fun getItem(id: Int): CityEntity? {
        return mCityList?.get(id)
    }

}