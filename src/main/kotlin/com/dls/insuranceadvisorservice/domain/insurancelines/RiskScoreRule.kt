package com.dls.insuranceadvisorservice.domain.insurancelines

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserProfile


interface RiskScoreRule {

    fun execute(userProfile: UserProfile, profile: RiskProfileLineInsurance)
}