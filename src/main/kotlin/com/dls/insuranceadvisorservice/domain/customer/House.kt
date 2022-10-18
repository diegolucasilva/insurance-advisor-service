package com.dls.insuranceadvisorservice.domain.customer

class House(val ownershipStatus: OwnershipStatus) {

    enum class OwnershipStatus {owned,mortgaged, rented}

}