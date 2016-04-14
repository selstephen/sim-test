package com.Simudyne;

import com.google.common.base.Preconditions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.Simudyne.AgentBreed.Breed_C;
import static com.Simudyne.AgentBreed.Breed_NC;

/**
 * Created by selwynstephen on 13/04/16.
 */
public class AgentUtils {

    // create agent from each line in file and populate collection
    public static List<Agent> getAgentsFromFile(String filename) throws IOException {
        List<Agent> agents = new ArrayList<Agent>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String line;
        Integer i = 0;
        while ((line = bufferedReader.readLine()) != null) {
            i++;
            if (i>1) {
                Agent agent = getFileContents(line);
                agents.add(agent);
            }

        }
        bufferedReader.close();

        return agents;

    }

    // Get agent row from file row
    private static Agent getFileContents(String line) {
        Agent agent = Agent.create(line.split(","));
        return agent;
    }

    private static void incrementAge(List<Agent> agents) {
        for (Agent agent : agents) {
            agent.setAge(agent.getAge()+1);
        }
    }

    // populate the affinity of each agent based on affinity calculation
    private static void populateAffinity(List<Agent> agentsToProcess) {
        for (Agent agent : agentsToProcess) {
            agent.setAffinitySwitch(calculateAffinity(agent));
        }
    }

    // affinity calculation
    private static Float calculateAffinity(Agent agent) {
        Float result = agent.getPaymentAtPurchase()/agent.getAttributePrice() + (2 * agent.getAttributePromotions() * agent.getIntertiaForSwitch());
        return result;
    }

    // count the number of agents based on breed
    private static Integer countAgentsBasedOnBreed(List<Agent> agents, String name) {
        Integer result = 0;
        for (Agent agent : agents) {
            if (agent.getBreed().equals(name)) result++;
        }
        return result;
    }

    // set new breed based on criteria
    private static void populateBreed(List<Agent> agents, Float brandFactor) {
        int index = 0;
        for (Agent agent : agents) {
            checkBreed(agent, brandFactor);
            if (index>0) {
                agent.setPriorPreviousBreed(agents.get(index-1).getPreviousBreed());
            }
            index++;

        }
    }

    private static void checkBreed(Agent agent, Float brandFactor) {
        // Agent Breed_C lost to Breed_NC
        if (agent.getBreed().equals(Breed_C.name())) {
            if (agent.getAffinitySwitch() < (agent.getSocialGrade() * agent.getAttributeBrand())) {
                agent.setBreed(Breed_NC.name());
                agent.setBreedCLost(1);
                agent.setBreedCGained(0);
                agent.setPreviousBreed(Breed_C.name());
            }
        // Agent Breed_NC gained from Breed_c
        } else if (agent.getBreed().equals(Breed_NC.name())) {
            if (agent.getAffinitySwitch() < (agent.getSocialGrade() * agent.getAttributeBrand()) * brandFactor) {
                agent.setBreed(Breed_C.name());
                agent.setBreedCGained(1);
                agent.setBreedCLost(0);
                agent.setPreviousBreed(Breed_NC.name());
            }
        }
        checkBreedRegained(agent);
    }

    private static void checkBreedRegained(Agent agent) {
        if (agent.getPreviousBreed()!=null
                && agent.getPriorPreviousBreed()!=null
                && agent.getPriorPreviousBreed()==Breed_C.name()
                && agent.getPreviousBreed()==Breed_NC.name()
                && agent.getBreed()==Breed_C.name()) {
            agent.setBreedRegained(1);
        }
    }

    private static List<Agent> getAgentsFromAutoRenew(List<Agent> agentsFromFile, Integer autoRenewValue) {
        List<Agent> agents = new ArrayList<Agent>();
        for (Agent agent : agentsFromFile) {
            if (agent.getAutoRenew()==autoRenewValue) {
                agents.add(agent);
            }
        }
        return agents;
    }

    private static Integer countLostBreedC(List<Agent> agents){
        Integer result = 0;
        for (Agent agent : agents) {
            if (agent.getBreedCLost()==1) {
                result++;
            }
        }
        return result;
    }

    private static Integer countGainedBreedC(List<Agent> agents){
        Integer result = 0;
        for (Agent agent : agents) {
            if (agent.getBreedCGained()==1) {
                result++;
            }
        }
        return result;
    }
    private static Integer countRegainedBreed(List<Agent> agents){
        Integer result = 0;
        for (Agent agent : agents) {
            if (agent.getBreedRegained()==1) {
                result++;
            }
        }
        return result;
    }

    // process agents for number of years and brand factor
    public static List<AgentResult> processAgents(List<Agent> agents, Integer yearsToRun, Float brandFactor){
//        Preconditions.checkArgument(brandFactor != null, "Brand factor cannot be null");
//        Preconditions.checkArgument(brandFactor<0.1, "Brand factor cannot be less than 0.1");
//        Preconditions.checkArgument(brandFactor>2.9, "Brand factor cannot be more than 2.9");

        List<Agent> autoRenewAgents = getAgentsFromAutoRenew(agents, AutoRenew.AUTO_RENEW.getValue());
        List<Agent> agentsToProcess = getAgentsFromAutoRenew(agents, AutoRenew.DO_NOT_AUTO_RENEW.getValue());

        List<AgentResult> agentResults = new ArrayList<AgentResult>();
        for (int i = 1; i <= yearsToRun; i++) {
            Integer totalBreedCCount = 0;
            Integer totalBreedNcCount = 0;
            incrementAge(autoRenewAgents);
            incrementAge(agentsToProcess);
            populateAffinity(agentsToProcess);
            totalBreedCCount += countAgentsBasedOnBreed(autoRenewAgents, Breed_C.name());
            totalBreedCCount += countAgentsBasedOnBreed(agentsToProcess, Breed_C.name());
            totalBreedNcCount += countAgentsBasedOnBreed(autoRenewAgents, Breed_NC.name());
            totalBreedNcCount += countAgentsBasedOnBreed(agentsToProcess, Breed_NC.name());
            populateBreed(agentsToProcess, brandFactor);
            Integer lostBreedC = countLostBreedC(agentsToProcess);
            Integer gainedBreedC = countGainedBreedC(agentsToProcess);
            Integer regainedBreed = countRegainedBreed(agentsToProcess);
            AgentResult agentResult = new AgentResult();
            agentResult.setYear(i);
            agentResult.setBreedCCount(totalBreedCCount);
            agentResult.setBreedNCCount(totalBreedNcCount);
            agentResult.setBreedCLostCount(lostBreedC);
            agentResult.setBreedCGainedCount(gainedBreedC);
            agentResult.setBreedRegained(regainedBreed);
            agentResults.add(agentResult);
        }

        return agentResults;
    }
}
