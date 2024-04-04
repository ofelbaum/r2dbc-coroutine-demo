package com.example.r2dbccoroutinedemo.repository

import com.example.r2dbccoroutinedemo.entity.Address
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository: CoroutineCrudRepository<Address, Long>