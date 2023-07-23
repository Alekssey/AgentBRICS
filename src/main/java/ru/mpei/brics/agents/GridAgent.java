package ru.mpei.brics.agents;

import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.brics.behaviours.grid.ReceiveAgentsData;
import ru.mpei.brics.behaviours.grid.SpamMeasurement;
import ru.mpei.brics.extention.configirationClasses.GridConfiguration;
import ru.mpei.brics.extention.helpers.DFHelper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Slf4j
public class GridAgent extends Agent {
//    @Getter
    public GridConfiguration cfg = null;
    private DFHelper df = new DFHelper();
    protected void setup() {
        df.registration(this, "grid");
        log.info("{} was born", this.getLocalName());
        String configFileName = this.getLocalName() + "Configuration.xml";

        try{
            JAXBContext context = JAXBContext.newInstance(GridConfiguration.class);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
            cfg = (GridConfiguration) jaxbUnmarshaller.unmarshal(new File("src/main/resources/" + configFileName));
        } catch (JAXBException e){
            e.printStackTrace();
        }

        this.addBehaviour(new SpamMeasurement(this, 1000, this.cfg));
        this.addBehaviour(new ReceiveAgentsData(this));

//        this.addBehaviour(new TestReceiveBehaviour(this));
    }

}