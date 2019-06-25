package com.baslogh.baslogh.dto;

import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.model.Referral;

import java.util.Set;

public class ReferralPageDTO {
    Set<Referral> referrals;
    Case refCase;

    public Set<Referral> getReferrals() {
        return referrals;
    }

    public void setReferrals(Set<Referral> referrals) {
        this.referrals = referrals;
    }

    public Case getRefCase() {
        return refCase;
    }

    public void setRefCase(Case refCase) {
        this.refCase = refCase;
    }
}
