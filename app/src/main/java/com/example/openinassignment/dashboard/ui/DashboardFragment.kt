package com.example.openinassignment.dashboard.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openinassignment.R
import com.example.openinassignment.dashboard.domain.model.DashboardDataModel
import com.example.openinassignment.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val dashboardViewModel by viewModels<DashboardViewModel>()
    private val linksListAdapter = LinksAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        setUpViews()
        attachOnClickListeners()

        return binding.root
    }

    private fun setUpViews() {
        setDefaultValues()
        dashboardViewModel.uiState.observe(this) {
            if (it.dashboardData != null) setUpDashboardData(it.dashboardData, it.selectedTab)
        }
    }

    private fun setDefaultValues() {
        binding.apply {
            todayClicks.apply {
                avatar.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.avatar_today_clicks
                    )
                )
                performanceTitle.text = resources.getString(R.string.title_today_clicks)
            }
            topLocation.apply {
                avatar.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.avatar_top_location
                    )
                )
                performanceTitle.text = resources.getString(R.string.title_top_location)
            }
            topSource.apply {
                avatar.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.avatar_top_source
                    )
                )
                performanceTitle.text = resources.getString(R.string.title_top_source)
            }

            linksList.apply {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                adapter = linksListAdapter
            }
        }
    }

    private fun setUpDashboardData(data: DashboardDataModel, selectedTab: String) {
        binding.apply {
            greetings.text = data.greetings

            // showing the performance matrix
            todayClicks.performanceValue.text = data.linksPerformanceData.todayClicks.toString()
            topLocation.performanceValue.text = data.linksPerformanceData.topLocation
            topSource.performanceValue.text = data.linksPerformanceData.topSource

            // showing the chart
            linksChart.adapter = ChartsAdapter(data.chartData)

            // showing the links list
            if (selectedTab == DashboardUiState.TAB_TOP_LINKS) {
                linksListAdapter.submitList(data.linksData.topLinks)
                tabTopLinks.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_primary))
                tabTopLinksText.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                tabRecentLinks.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                tabRecentLinksText.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_light))
            } else {
                linksListAdapter.submitList(data.linksData.recentLinks)
                tabRecentLinks.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_primary))
                tabRecentLinksText.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                tabTopLinks.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                tabTopLinksText.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_light))
            }
        }
    }

    private fun attachOnClickListeners() {
        binding.apply {
            tabTopLinks.setOnClickListener { dashboardViewModel.selectTopLinksTab() }
            tabRecentLinks.setOnClickListener { dashboardViewModel.selectRecentLinksTab() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
