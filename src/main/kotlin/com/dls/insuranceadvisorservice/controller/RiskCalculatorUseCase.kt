package com.dls.insuranceadvisorservice.controller

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserProfile

interface RiskCalculatorUseCase{
    fun execute(userProfile: UserProfile): List<RiskProfileLineInsurance>
}