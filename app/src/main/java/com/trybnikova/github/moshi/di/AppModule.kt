package com.trybnikova.github.moshi.di


import com.trybnikova.github.moshi.ui.list.ListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    viewModel {
        ListViewModel(
            taskRepository = get()
        )
    }

}
