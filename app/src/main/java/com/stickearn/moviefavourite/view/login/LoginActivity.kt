package com.stickearn.moviefavourite.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.stickearn.moviefavourite.R
import com.stickearn.moviefavourite.base.BaseActivity
import com.stickearn.moviefavourite.databinding.ActivityLoginBinding
import com.stickearn.moviefavourite.model.entity.DummyEntity
import com.stickearn.moviefavourite.service.database.dao.DummyEntityDao
import com.stickearn.moviefavourite.utilities.PrefHelper
import com.stickearn.moviefavourite.utilities.extension.setOnSafeClickListener
import com.stickearn.moviefavourite.view.main.activity.MainActivity
import com.stickearn.moviefavourite.viewmodel.LoginViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.inject


class LoginActivity : BaseActivity() {
    private val viewModel: LoginViewModel by viewModel()

    private val prefHelper: PrefHelper by inject()

    private val dummyEntityDao: DummyEntityDao by inject()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
        setupOnClick()
        observeViewModel()
        testDb()
    }

    private fun testDb()
    {
        GlobalScope.launch {
            dummyEntityDao.insert(DummyEntity(1, "test"))
        }

        val lifecycleOwner = this
        MainScope().launch {
            val getAllDataDummyEntity =  dummyEntityDao.getAll()
            getAllDataDummyEntity.observe(lifecycleOwner, Observer {
                Toast.makeText(applicationContext, it[0].text , Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun observeViewModel() {
        val loadingDialog = loadingDialog(this)
        viewModel.isLoading.observe(this, Observer {
            if(it)
            {
                loadingDialog.show()
            }
            else
                loadingDialog.dismiss()
        })
        viewModel.workerLogin.observe(this, Observer {
            Toast.makeText(applicationContext, "Hello ${it.user.nama}", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))

        })
        viewModel.errorLogin.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupOnClick() {
        binding.btnLogin.setOnSafeClickListener  {
            if(viewModel.email.isBlank())
            {
                binding.lblErrorRegisteredPhoneNoLogin.visibility = View.VISIBLE
            }
            else
            {
                binding.lblErrorRegisteredPhoneNoLogin.visibility = View.GONE
            }

            if(viewModel.password.isBlank())
            {
                binding.lblErrorPasswordLogin.visibility = View.VISIBLE
            }
            else
            {
                binding.lblErrorPasswordLogin.visibility = View.GONE
            }

            if(viewModel.email.isNotBlank() && viewModel.password.isNotBlank())
            {
                actionLogin()
            }
        }
    }

    private fun actionLogin()
    {
//        viewModel.loginWorker()
    }
}