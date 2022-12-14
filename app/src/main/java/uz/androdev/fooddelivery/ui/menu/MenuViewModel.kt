package uz.androdev.fooddelivery.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.androdev.fooddelivery.domain.GetCategoriesUseCase
import uz.androdev.fooddelivery.domain.GetFoodsByCategoryUseCase
import uz.androdev.fooddelivery.model.Category
import uz.androdev.fooddelivery.model.Food
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 2:56 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getFoodsByCategoryUseCase: GetFoodsByCategoryUseCase
) : ViewModel() {
    private val selectedCategoryState = MutableStateFlow<Category?>(null)
    private val categoryListState = MutableStateFlow<List<Category>>(emptyList())
    private val loadingCategoriesState = MutableStateFlow(false)
    private val failedToLoadCategoriesState = MutableStateFlow(false)

    val categoriesState: StateFlow<CategoriesState> = combine(
        categoryListState,
        loadingCategoriesState,
        failedToLoadCategoriesState,
        selectedCategoryState
    ) { categoryList, loadingCategories, failedToLoadCategories, selectedCategory ->
        CategoriesState(
            loadingCategories = loadingCategories,
            failedToLoadCategories = failedToLoadCategories,
            categories = withContext(Dispatchers.IO) {
                categoryList.map {
                    UiCategory(category = it, isSelected = it == selectedCategory)
                }
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = CategoriesState()
    )

    private val loadingFoodsState = MutableStateFlow(false)
    private val failedToLoadFoodsState = MutableStateFlow(false)
    private val foodsListState = MutableStateFlow<List<Food>>(emptyList())
    val foodsState: StateFlow<FoodsState> = combine(
        loadingFoodsState,
        failedToLoadFoodsState,
        foodsListState
    ) { loadingFoods, failedToLoadFoods, foodsList ->
        FoodsState(
            loadingFoods = loadingFoods,
            failedToLoadFoods = failedToLoadFoods,
            foods = if (loadingFoods || failedToLoadFoods) emptyList() else foodsList
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = FoodsState()
    )

    init {
        processAction(Action.LoadCategories)

        viewModelScope.launch {
            var foodsLoadJob: Job? = null

            selectedCategoryState.filterNotNull().collect {
                foodsLoadJob?.cancel()
                foodsLoadJob = loadFoods(it)
            }
        }
    }

    fun processAction(action: Action) {
        viewModelScope.launch {
            when (action) {
                Action.LoadCategories -> loadCategories()
                is Action.CategoryClicked -> {
                    selectedCategoryState.emit(action.uiCategory.category)
                }
            }
        }
    }

    private suspend fun loadFoods(category: Category) = viewModelScope.launch {
        loadingFoodsState.emit(true)
        failedToLoadFoodsState.emit(false)

        val foods = getFoodsByCategoryUseCase(category)
        if (foods.isEmpty()) {
            failedToLoadFoodsState.emit(true)
        } else {
            foodsListState.emit(foods)
        }

        loadingFoodsState.emit(false)
    }

    private suspend fun loadCategories() {
        if (loadingCategoriesState.value) return

        loadingCategoriesState.emit(true)
        failedToLoadCategoriesState.emit(false)

        val categories = getCategoriesUseCase()

        if (categories.isNotEmpty()) {
            if (selectedCategoryState.value == null) {
                selectedCategoryState.emit(categories.first())
            }
            categoryListState.emit(categories)
        } else {
            failedToLoadCategoriesState.emit(true)
        }

        loadingCategoriesState.emit(false)
    }
}

sealed interface Action {
    object LoadCategories : Action
    data class CategoryClicked(val uiCategory: UiCategory) : Action
}

data class CategoriesState(
    val categories: List<UiCategory> = emptyList(),
    val loadingCategories: Boolean = true,
    val failedToLoadCategories: Boolean = false
)

data class FoodsState(
    val foods: List<Food> = emptyList(),
    val loadingFoods: Boolean = false,
    val failedToLoadFoods: Boolean = false
)

data class UiCategory(
    val category: Category,
    val isSelected: Boolean = false
) {
    companion object {
        val DIFF_UTIL = object : ItemCallback<UiCategory>() {
            override fun areItemsTheSame(oldItem: UiCategory, newItem: UiCategory): Boolean {
                return Category.DIFF_UTIL.areItemsTheSame(oldItem.category, newItem.category)
            }

            override fun areContentsTheSame(oldItem: UiCategory, newItem: UiCategory): Boolean {
                return oldItem == newItem
            }
        }
    }
}