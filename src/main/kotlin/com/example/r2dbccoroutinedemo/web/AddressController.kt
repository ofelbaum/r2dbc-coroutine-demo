package com.example.r2dbccoroutinedemo.web

import com.example.r2dbccoroutinedemo.entity.Address
import com.example.r2dbccoroutinedemo.repository.AddressRepository
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/addresses")
class AddressController(

) {
    @Autowired
    lateinit var addressRepository: AddressRepository

    @GetMapping("/{id}")
    suspend fun getAddress(@PathVariable("id") id: Long): ResponseEntity<Address> {
        val address = addressRepository.findById(id) ?: throw IllegalStateException("not found")
        return ResponseEntity.ok(address)
    }

    @GetMapping
    suspend fun getAddresses(): ResponseEntity<List<Address>> {
        val addresses = addressRepository.findAll()
        return ResponseEntity.ok(addresses.toList())
    }

    @PostMapping
    suspend fun createAddress(@RequestBody address: Address): ResponseEntity<Address> {
        val savedAddress = addressRepository.save(address)
        return ResponseEntity.ok(savedAddress)
    }
}