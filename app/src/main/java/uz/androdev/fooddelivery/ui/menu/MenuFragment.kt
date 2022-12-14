package uz.androdev.fooddelivery.ui.menu

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearSnapHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import uz.androdev.fooddelivery.R
import uz.androdev.fooddelivery.databinding.FragmentMenuBinding
import uz.androdev.fooddelivery.ui.base.BaseFragment

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 2:08 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@AndroidEntryPoint
class MenuFragment : BaseFragment<FragmentMenuBinding>(FragmentMenuBinding::inflate) {
    private val viewModel by viewModels<MenuViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bindContent(
            categoriesState = viewModel.categoriesState,
            foodsState = viewModel.foodsState,
            processAction = viewModel::processAction
        )
    }

    private fun FragmentMenuBinding.bindContent(
        categoriesState: StateFlow<CategoriesState>,
        foodsState: StateFlow<FoodsState>,
        processAction: (Action) -> Unit
    ) {

        bindBanners(
            isVisible = categoriesState.map {
                !it.loadingCategories && !it.failedToLoadCategories
            }
        )

        bindCategories(
            isVisible = categoriesState.map {
                !it.loadingCategories && !it.failedToLoadCategories
            },
            categories = categoriesState.map { it.categories },
            onCategoryClicked = {
                processAction(Action.CategoryClicked(it))
            }
        )

        bindFoods(foodsState = foodsState)

        bindLoading(
            isVisible = categoriesState.map { it.loadingCategories }
        )

        bindError(
            isVisible = categoriesState.map { it.failedToLoadCategories },
            onReload = {
                processAction(Action.LoadCategories)
            }
        )
    }

    private fun FragmentMenuBinding.bindBanners(
        isVisible: Flow<Boolean>
    ) {
        with(rvBanners) {
            adapter = BannerAdapter().also {
                it.submitList(
                    listOf(
                        Banner(R.drawable.img_banner_3),
                        Banner(R.drawable.img_banner_1),
                        Banner(R.drawable.img_banner_2)
                    )
                )
            }
            LinearSnapHelper().attachToRecyclerView(this)
        }

        repeatOnViewLifecycle(Lifecycle.State.STARTED) {
            isVisible.distinctUntilChanged().collect {
                rvBanners.isVisible = it
                btnQrCode.isVisible = it
                txtCity.isVisible = it
            }
        }
    }

    private fun FragmentMenuBinding.bindCategories(
        isVisible: Flow<Boolean>,
        categories: Flow<List<UiCategory>>,
        onCategoryClicked: (UiCategory) -> Unit
    ) {
        val adapter = CategoryAdapter(onCategoryClicked)
        rvCategories.adapter = adapter
        // we don't need item animator since we are not removing any item
        rvCategories.itemAnimator = null

        repeatOnViewLifecycle(Lifecycle.State.STARTED) {
            launch {
                categories.distinctUntilChanged().collect(adapter::submitList)
            }

            launch {
                isVisible.distinctUntilChanged().collect {
                    rvCategories.isVisible = it
                }
            }
        }
    }

    private fun FragmentMenuBinding.bindFoods(
        foodsState: Flow<FoodsState>
    ) {
        val adapter = FoodAdapter()
        rvFoods.adapter = adapter

        repeatOnViewLifecycle(Lifecycle.State.STARTED) {
            foodsState.map { it.foods }.distinctUntilChanged().collect(adapter::submitList)
        }
    }

    private fun FragmentMenuBinding.bindLoading(
        isVisible: Flow<Boolean>
    ) {
        repeatOnViewLifecycle(Lifecycle.State.STARTED) {
            isVisible.distinctUntilChanged().collect {
                progressBar.isVisible = it
            }
        }
    }

    private inline fun FragmentMenuBinding.bindError(
        isVisible: Flow<Boolean>,
        crossinline onReload: () -> Unit
    ) {
        btnReload.setOnClickListener { onReload() }

        repeatOnViewLifecycle(Lifecycle.State.STARTED) {
            isVisible.distinctUntilChanged().collect {
                errorContainer.isVisible = it
            }
        }
    }
}