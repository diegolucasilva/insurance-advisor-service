package com.dls.insuranceadvisorservice.domain.customer

data class Customer(
    val age: Int,
    val dependents: Int,
    val income: Int,
    val maritalStatus: MaritalStatus,
    val house: House?,
    val questionScore: List<Int>,
    val vehicle: Vehicle?
){
    enum class MaritalStatus { single,married }

}