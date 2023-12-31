package ru.mpei.brics.behaviours.networkElement.activePowerImbalanceFSMSubbehaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.SybaseAnywhereDialect;
import ru.mpei.brics.agents.NetworkElementAgent;
import ru.mpei.brics.behaviours.networkElement.AnalyzeFrequency;
import ru.mpei.brics.extention.configirationClasses.NetworkElementConfiguration;

@Slf4j
public class LastBehaviour extends OneShotBehaviour {

    private NetworkElementConfiguration cfg = ((NetworkElementAgent) myAgent).getCfg();


    public LastBehaviour(Agent a) {
        super(a);
    }

    @Override
    public void action() {
        log.info("\nFSM end. " +
                "\nFitness list: {}; " +
                "\nDeque map: {} " +
                "\nCurrent power: {}\n\n",
                cfg.getFitnessValues(), cfg.getAgentsQueue(), cfg.getCurrentP());
//        cfg.setNumberOfActiveAgents(0);
        cfg.setPTradeIsOpen(false);
        cfg.getFitnessValues().clear();
        cfg.getAgentsQueue().clear();
        ((NetworkElementAgent) myAgent).setKieSession(null);
        myAgent.addBehaviour(new AnalyzeFrequency(myAgent, ((NetworkElementAgent) myAgent).getCfg().getPeriod()));
        log.error("regulating time: {}", (System.currentTimeMillis() - ((NetworkElementAgent) myAgent).getStartTime()) / 1000);
    }
}
