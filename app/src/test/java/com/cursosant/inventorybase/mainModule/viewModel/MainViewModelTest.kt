package com.cursosant.inventorybase.mainModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.AdditionalMatchers.not
import org.robolectric.annotation.Config

@Config(maxSdk = 30) //TODO Hara todas las pruebas hasta la API 30
//@Config(sdk = [21,22,30]) todo solo se probaran  las APIS en las versiones del array
@RunWith(AndroidJUnit4::class)
class MainViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
//    TODO En resumen, @get:Rule se utiliza para aplicar la regla
//     InstantTaskExecutorRule a una prueba particular. La regla InstantTaskExecutorRule
//     garantiza que las tareas
//     programadas en LiveData se ejecuten de inmediato en el hilo principal durante las pruebas


    @Test
    fun checkWelcomeTest() {
        //Create intance of ViewModel
        val mainViewModel = MainViewModel(ApplicationProvider.getApplicationContext())

//        viewModel.isWelcome().observe(this, { isWelcome ->
//        TODO Para simular que tipo de datos estamos mockeando y que hace lo mismo del codigo de arriba es:
        val observer = Observer<Boolean> {}
        try {
            mainViewModel.isWelcome().observeForever(observer)
            val result = mainViewModel.isWelcome().value
            assertThat(result, Matchers.`is`(true))
//            assertThat(result, not(nullValue()))// opt
        } finally {
//            Todo siempre debemos de remover el observador
            mainViewModel.isWelcome().removeObserver(observer)
        }
    }

}