package com.stickearn.moviefavourite.view.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.stickearn.moviefavourite.R
import com.stickearn.moviefavourite.base.BaseActivity
import com.stickearn.moviefavourite.databinding.ActivityMainBinding
import com.stickearn.moviefavourite.model.usertest.UserTestItem
import com.stickearn.moviefavourite.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        binding.rcyUserMain.layoutManager = LinearLayoutManager(this)

        val listUser = mutableListOf<UserTestItem>()
        val user1 = UserTestItem(
            id =1,
            nama = "Dhaba1",
            email = "dhabawidhikari1@gmail.com"
        )
        val user2 = UserTestItem(
            id =2,
            nama = "Dhaba2",
            email = "dhabawidhikari2@gmail.com"
        )
        val user3 = UserTestItem(
            id =3,
            nama = "Dhaba3",
            email = "dhabawidhikari3@gmail.com"
        )
        listUser.add(user1)
        listUser.add(user2)
        listUser.add(user3)

        val adapter = MainAdapter(listUser)

        binding.rcyUserMain.adapter = adapter
    }
}