package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.colan.kindercare.adapter.BaseRecyclerViewAdapter
import com.colan.kindercare.adapter.OnDataBindCallback
import com.colan.kindercare.ui.base.BaseActivity
import com.colan.kindercare.ui.base.BaseNavigator
import com.example.myapplication.data.remote.data.Status
import com.example.myapplication.data.remote.data.response.UserProfileResponse
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.CoincapListItemsBinding
import com.example.myapplication.utils.navigateTo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityVM>(),BaseNavigator,
    OnDataBindCallback<CoincapListItemsBinding>, SwipeRefreshLayout.OnRefreshListener {

    private var baseRecyclerAdapter: BaseRecyclerViewAdapter<UserProfileResponse, CoincapListItemsBinding>? =
        null

    var mFilterList = ObservableArrayList<UserProfileResponse>()
    var mDataList = ObservableArrayList<UserProfileResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel?.setNavigator(this)
        observerResponse()
        setUpRecyclerview()
        getViewDataBinding().editText.addTextChangedListener {
            editFilter(getViewDataBinding().editText.text.toString())
        }

        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    override fun getBindingVariable(): Int {
        return BR.mainActivityVM
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainActivityVM {
        return ViewModelProviders.of(this).get(MainActivityVM::class.java)
    }

    override fun onClickView(v: View?) {
        when (v?.id) {

        }

    }

    override fun goTo(clazz: Class<*>, mExtras: Bundle?) {
        navigateTo(this, clazz, null)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)

    }

    fun observerResponse(){
        mViewModel?.mShowProgressBar?.observeEvent(this, Observer {
            when {
                it -> showLoadingIndicator()
                else -> dismissLoadingIndicator()
            }
        })
        mViewModel?.getUserProfileResponse?.observe(this, Observer {
            when(it.status){
                Status.SUCCESS->{
                    mViewModel?.mShowProgressBar?.value = false
                    mViewModel?.getUserProfileJob?.cancel()

                    if (it.data?.data != null){
                        //setUpRecyclerview(it.data.data)
                        mDataList.addAll(it.data.data)
                        mFilterList.addAll(it.data.data)
                        baseRecyclerAdapter?.cleatDataSet()
                        baseRecyclerAdapter?.addDataSet(it.data.data)
                        Log.i("coindata","cccc------>${it.data.data}")
                    }

                }
                Status.ERROR, Status.FAILURE -> {
                    mViewModel?.mShowProgressBar?.value = false
                    putToast(""+it.message)
                }
                else -> {

                }
            }

        })
    }

    fun editFilter(getList: String) {
        val data = mDataList.filter {
            !it.name.isNullOrEmpty()

        }.filter {
            it.name?.contains(getList,ignoreCase = true)!!
        }

        Log.i("searchNew", "fffdd--->${data}")

        mFilterList.clear()
        mFilterList.addAll(data)

        baseRecyclerAdapter?.cleatDataSet()
        baseRecyclerAdapter?.addDataSet(mFilterList)

    }

    private fun setUpRecyclerview() {
        baseRecyclerAdapter = BaseRecyclerViewAdapter(
            R.layout.coincap_list_items,
            BR.leaveApprovalItem,
            ArrayList(),
            null,
            this
        )

        getViewDataBinding()?.rvCurrencyData?.adapter = baseRecyclerAdapter
        baseRecyclerAdapter?.notifyDataSetChanged()


    }

    override fun onItemClick(view: View, position: Int, v: CoincapListItemsBinding) {
    }

    override fun onDataBind(
        v: CoincapListItemsBinding,
        onClickListener: View.OnClickListener
    ) {
    }

    override fun onRefresh() {
        Handler().postDelayed(Runnable {
            setUpRecyclerview()
            observerResponse()
            mSwipeRefreshLayout.isRefreshing = false
        }, 2000)
    }
}