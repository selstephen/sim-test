package com.Simudyne;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by selwynstephen on 13/04/16.
 */
public class Agent {
    private String breed;
    private Float policyId;
    private Integer age;
    private Integer socialGrade;
    private Integer paymentAtPurchase;
    private Float attributeBrand;
    private Float attributePrice;
    private Float attributePromotions;
    private Integer autoRenew;
    private Integer intertiaForSwitch;
    private Float affinitySwitch;
    private Integer breedCLost;
    private Integer breedCGained;
    private String previousBreed;
    private String priorPreviousBreed;
    private Integer breedRegained;

    public static Agent create(final String[] args){
        final Agent agent = new Agent();
        agent.setBreed(args[0]);
        agent.setPolicyId(Float.valueOf(args[1]));
        agent.setAge(Integer.valueOf(args[2]));
        agent.setSocialGrade(Integer.valueOf(args[3]));
        agent.setPaymentAtPurchase(Integer.valueOf(args[4]));
        agent.setAttributeBrand(Float.valueOf(args[5]));
        agent.setAttributePrice(Float.valueOf(args[6]));
        agent.setAttributePromotions(Float.valueOf(args[7]));
        agent.setAutoRenew(Integer.valueOf(args[8]));
        agent.setIntertiaForSwitch(Integer.valueOf(args[9]));
        agent.setBreedCGained(0);
        agent.setBreedCLost(0);
        agent.setBreedRegained(0);
        return agent;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Float getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Float policyId) {
        this.policyId = policyId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSocialGrade() {
        return socialGrade;
    }

    public void setSocialGrade(Integer socialGrade) {
        this.socialGrade = socialGrade;
    }

    public Integer getPaymentAtPurchase() {
        return paymentAtPurchase;
    }

    public void setPaymentAtPurchase(Integer paymentAtPurchase) {
        this.paymentAtPurchase = paymentAtPurchase;
    }

    public Float getAttributeBrand() {
        return attributeBrand;
    }

    public void setAttributeBrand(Float attributeBrand) {
        this.attributeBrand = attributeBrand;
    }

    public Float getAttributePrice() {
        return attributePrice;
    }

    public void setAttributePrice(Float attributePrice) {
        this.attributePrice = attributePrice;
    }

    public Float getAttributePromotions() {
        return attributePromotions;
    }

    public void setAttributePromotions(Float attributePromotions) {
        this.attributePromotions = attributePromotions;
    }

    public Integer getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(Integer autoRenew) {
        this.autoRenew = autoRenew;
    }

    public Integer getIntertiaForSwitch() {
        return intertiaForSwitch;
    }

    public void setIntertiaForSwitch(Integer intertiaForSwitch) {
        this.intertiaForSwitch = intertiaForSwitch;
    }

    public Float getAffinitySwitch() {
        return affinitySwitch;
    }

    public void setAffinitySwitch(Float affinitySwitch) {
        this.affinitySwitch = affinitySwitch;
    }

    public Integer getBreedCLost() {
        return breedCLost;
    }

    public void setBreedCLost(Integer breedCLost) {
        this.breedCLost = breedCLost;
    }

    public Integer getBreedCGained() {
        return breedCGained;
    }

    public void setBreedCGained(Integer breedCGained) {
        this.breedCGained = breedCGained;
    }

    public String getPreviousBreed() {
        return previousBreed;
    }

    public void setPreviousBreed(String previousBreed) {
        this.previousBreed = previousBreed;
    }

    public Integer getBreedRegained() {
        return breedRegained;
    }

    public void setBreedRegained(Integer breedRegained) {
        this.breedRegained = breedRegained;
    }

    public String getPriorPreviousBreed() {
        return priorPreviousBreed;
    }

    public void setPriorPreviousBreed(String priorPreviousBreed) {
        this.priorPreviousBreed = priorPreviousBreed;
    }
}
