package com.insurance.controller;

import com.insurance.model.Policy;
import java.util.List;

public interface PolicyController {
    void addPolicy(Policy policy);
    void updatePolicy(Policy policy);
    void deletePolicy(int policyId);
    Policy getPolicy(int policyId);
    List<Policy> getAllPolicies();
}
