package com.cursosant.inventorybase.addModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cursosant.inventorybase.entities.Product
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith

/****
 * Project: Inventory
 * From: com.cursosant.inventory.addModule.viewModel
 * Created by Alain Nicolás Tello on 23/05/23 at 18:26
 * All rights reserved 2023.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 */
@RunWith(AndroidJUnit4::class)
class AddViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    //    TODO simulateLiveData
    @Test
    fun addProductTest() {
        //Create intance of ViewModel
        val addViewModel = AddViewModel()
        val product = Product(
            117, "Xbox", 20, "https://upload.wikimedia.org/" +
                    "wikipedia/commons/thumb/5/54/Xbox_Series_S_with_controller.jpg/480px-Xbox_Series_S_with_controller.jpg",
            4.8, 56
        )

//        viewModel.getResult().observe(viewLifecycleOwner, { result ->
//        TODO Para simular que tipo de datos estamos mockeando y que hace lo mismo del codigo de arriba es:
        val observer = Observer<Boolean> {}
        try {
            addViewModel.getResult().observeForever(observer)
            addViewModel.addProduct(product)
            val result = addViewModel.getResult().value
            MatcherAssert.assertThat(result, Matchers.`is`(true))
            //assertThat(result, not(nullValue()))// opt
        } finally {
//            Todo siempre debemos de remover el observador
            addViewModel.getResult().removeObserver(observer)
        }
    }
}