package universe;

import universe.laws.commandPattern.Agent;
import universe.laws.commandPattern.DieByCrowdinessLaw;
import universe.laws.commandPattern.DieByLonelinessLaw;
import universe.laws.commandPattern.LifeCycle;

/**
 * Created with IntelliJ IDEA.
 * User: ancuta
 * Date: 5/10/13
 * Time: 11:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class BigBang {

    Universe universe = new Universe();

    public BigBang(){
        CellWorld cw1 = new CellWorld(1);
        CellWorld cw2 = new CellWorld(2);
        CellWorld cw3 = new CellWorld(3);
        CellWorld cw4 = new CellWorld(4);
        cw1.setAlive(true);
        cw1.setAliveForSnapshot(true);
        cw2.setAlive(true);
        cw2.setAliveForSnapshot(true);
        cw3.setAlive(true);
        cw3.setAliveForSnapshot(true);
        cw4.setAlive(true);
        cw4.setAliveForSnapshot(true);
        cw1.setNv(cw2);
        //cw1.setV(cw4);
        cw2.setSe(cw1);
        //cw2.setSv(cw4);
        cw2.setNv(cw3);
        cw3.setSe(cw2);
        cw4.setE(cw1);
        cw4.setNe(cw2);
        universe.addCellWorld(cw1);
        universe.addCellWorld(cw2);
        universe.addCellWorld(cw3);
        universe.addCellWorld(cw4);
    }

    public String ignite(){

    	String output = "";
    	
        for (CellWorld cellWorld: universe.getUniverse()){
            output += cellWorld.isAlive() + " ";
        }
        output += "\n";
        output += "---\n";
        for (int i=0; i<3; i++){
            for (CellWorld cellWorld: universe.getUniverse()){
                Agent agent = new Agent();
                LifeCycle lifeCycle = new LifeCycle(cellWorld);
                if (cellWorld.getNumberOfNeighbors() < 2){
                    agent.addLaw(new DieByLonelinessLaw(lifeCycle));
                } else if (cellWorld.getNumberOfNeighbors() > 3){
                    agent.addLaw(new DieByCrowdinessLaw(lifeCycle));
                }

                output += cellWorld.isAliveForSnapshot() + " ";
            }
            output += "\n";
            output += "---\n";
            for (CellWorld cellWorld: universe.getUniverse()){
                cellWorld.setAlive(cellWorld.isAliveForSnapshot());

            }
        }
		return output;
    }
}
