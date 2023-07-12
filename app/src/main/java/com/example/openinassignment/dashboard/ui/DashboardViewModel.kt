package com.example.openinassignment.dashboard.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openinassignment.dashboard.domain.use_case.DashboardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val useCases: DashboardUseCases
) : ViewModel() {

    private val _uiState: MutableLiveData<DashboardUiState> = MutableLiveData(
        DashboardUiState(
            dashboardData = null,
            selectedTab = DashboardUiState.TAB_TOP_LINKS,
            loading = false,
            message = null
        )
    )
    val uiState: LiveData<DashboardUiState> = _uiState

    init {
        getDashboardData()
    }

    private fun getDashboardData() = viewModelScope.launch {
        setLoadingState(true)
        val res = useCases.getDashboardPageData()
        if (res.data != null) {
            _uiState.postValue(_uiState.value!!.copy(dashboardData = res.data, loading = false))
        } else {
            _uiState.postValue(_uiState.value!!.copy(message = res.message, loading = false))
        }
    }

    private fun setLoadingState(loading: Boolean) {
        _uiState.postValue(
            _uiState.value!!.copy(loading = loading)
        )
    }

    fun selectTopLinksTab() {
        _uiState.postValue(_uiState.value!!.copy(selectedTab = DashboardUiState.TAB_TOP_LINKS))
    }

    fun selectRecentLinksTab() {
        _uiState.postValue(_uiState.value!!.copy(selectedTab = DashboardUiState.TAB_RECENT_LINKS))
    }

}
