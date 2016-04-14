package com.Simudyne;

import java.io.IOException;
import java.util.List;

public class Main {

    // brand factor input
    public static Float BRAND_FACTOR = 0.5F;

    public static void main(String[] args) throws IOException {

        // years to run to be input parameter in the future
        Integer YEARS_TO_RUN = 15;

        List<Agent> agentsFromFile = AgentUtils.getAgentsFromFile("AgentTest.csv");
        List<AgentResult> agentResults = AgentUtils.processAgents(agentsFromFile, YEARS_TO_RUN, BRAND_FACTOR);

        for (AgentResult agentResult : agentResults) {
            String result = "Year ";
            result += agentResult.getYear();
            result += " : ";
            result += "Breed_C count - " + agentResult.getBreedCCount() + ", ";
            result += "Breed_NC count - " + agentResult.getBreedNCCount() + ", ";
            result += "Breed_C lost - " + agentResult.getBreedCLostCount() + ", ";
            result += "Breed_C gained - " + agentResult.getBreedCGainedCount() + ", ";
            result += "Breed_C regained - " + agentResult.getBreedRegained();

            System.out.println(result);
        }


    }









}
