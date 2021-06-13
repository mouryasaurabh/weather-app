package com.service.weatherapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.service.weatherapp.R
import com.service.weatherapp.ui.adapters.RecentCityListAdapter
import com.service.weatherapp.ui.interfaces.RecentCityItemClickListener
import com.service.weatherapp.ui.viewmodels.RecentFavouriteViewModel

class RecentFavouriteFragment : Fragment() {
    private var noSavedCityTV: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var recentCityListAdapter: RecentCityListAdapter? = null

    var recentFavouriteViewModel: RecentFavouriteViewModel? = null
    private var recentCityItemClickListener: RecentCityItemClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recentFavouriteViewModel = ViewModelProvider(this).get(RecentFavouriteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recent_favourite, container, false)
    }

    override fun onResume() {
        super.onResume()
        recentFavouriteViewModel?.fetchFavouriteCities()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(view)
        observeLivedata()
    }

    private fun initUI(view: View) {
        noSavedCityTV = view.findViewById(R.id.no_favourite_cities)
        recyclerView = view.findViewById(R.id.recent_cities_rv)
        recentCityListAdapter =
            RecentCityListAdapter(requireContext(), null, recentCityItemClickListener)
        recyclerView?.adapter = recentCityListAdapter
    }

    private fun observeLivedata() {
        recentFavouriteViewModel?.getCityLiveData()
            ?.observe(viewLifecycleOwner, Observer { cityList ->
                if (cityList.isNullOrEmpty()) {
                    recyclerView?.visibility = View.GONE
                    noSavedCityTV?.visibility = View.VISIBLE
                } else {
                    recyclerView?.visibility = View.VISIBLE
                    noSavedCityTV?.visibility = View.GONE
                    recentCityListAdapter?.setData(cityList)
                }
            })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        recentCityItemClickListener = context as? RecentCityItemClickListener
    }

    companion object {
        const val TAG: String = "RecentFavouriteFragment"

        @JvmStatic
        fun newInstance() = RecentFavouriteFragment()
    }
}