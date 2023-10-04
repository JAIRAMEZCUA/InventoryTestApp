package com.cursosant.inventorybase.addModule.view

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cursosant.inventorybase.R
import com.cursosant.inventorybase.addModule.viewModel.AddViewModel
import com.cursosant.inventorybase.entities.Product
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/****
 * Project: Inventory
 * From: com.cursosant.inventory.addModule.view
 * Created by Alain Nicolás Tello on 23/05/23 at 18:29
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


/*TODO ventajas de usar roboelectrics es que podemos testear la app para acceder al ciclo de vida , contextos , viewModels*/
@RunWith(AndroidJUnit4::class)
class AddProductFragmentTest {
    @Test
    fun createDialog_notNullTest() {
        //TODO creamos los escenarios para testear un fragment o una activity
        val scenario =
            launchFragment<AddProductFragment>(themeResId = R.style.Base_Theme_InventoryBase)
        //Es importante que el AlertDialog Cuente con un tema


        //TODO Para que la activity/fragment se mueva de estado en el ciclo de vida
        scenario.moveToState(Lifecycle.State.RESUMED)


        scenario.onFragment { fragment ->
            MatcherAssert.assertThat(fragment.dialog, Matchers.`is`(Matchers.notNullValue()))
        }
    }

    @Test
    fun cancelDialog_isNullTest() {
        val scenario = launchFragment<AddProductFragment>(themeResId = R.style.Theme_InventoryBase)
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onFragment { fragment ->
            //En caso que el AlertDIalog no sea nulo ->
            (fragment.dialog as? AlertDialog)?.let {
                //Vamos a dar click en el boton negativo
                it.getButton(DialogInterface.BUTTON_NEGATIVE).performClick()
                //Al clickear el boton se hara el dismiss y seraa nulo el fragmento
                MatcherAssert.assertThat(fragment.dialog, Matchers.`is`(Matchers.nullValue()))
            }
        }
    }

    @Test
    fun positiveButtonDialog_validateProduct() {
        val addViewModel = AddViewModel()
        val scenario = launchFragment<AddProductFragment>(themeResId = R.style.Theme_InventoryBase)
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onFragment { fragment ->
            (fragment.dialog as? AlertDialog)?.let {
                it.getButton(DialogInterface.BUTTON_POSITIVE).performClick()
                MatcherAssert.assertThat(fragment.dialog, Matchers.`is`(Matchers.notNullValue()))


                val product = Product(
                    117, "Xbox", 20, "https://upload.wikimedia.org/" +
                            "wikipedia/commons/thumb/5/54/Xbox_Series_S_with_controller.jpg/480px-Xbox_Series_S_with_controller.jpg",
                    4.8, 56
                )

                val observer = Observer<Boolean> {}
                try {
                    addViewModel.getResult().observeForever(observer)
                    addViewModel.addProduct(product)
                    val result = addViewModel.getResult().value
                    MatcherAssert.assertThat(result, Matchers.`is`(true))
                } finally {
                    addViewModel.getResult().removeObserver(observer)
                }

            }
        }
    }
}