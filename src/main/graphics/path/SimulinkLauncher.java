package main.graphics.path;

//import java.util.concurrent.Future;

public class SimulinkLauncher {

	/*public double[][] SimulinkLauncher(){
		System.out.println("Launching engine");
		MatlabEngine eng = MatlabEngine.startMatlab();
		System.out.println("engine launched");
		Future<Void>  fLoad = eng.evalAsync("load_system('SimulationFL.mdl')");

		while (!fLoad.isDone()){
			System.out.println("Loading Simulink model...");
			Thread.sleep(10000);
		}
		//SimulationFL
		Future<Void> fSim = eng.evalAsync("simOut = sim('SimulationFL');");
		while (!fSim.isDone()) {
			System.out.println("Running Simulation...");
			Thread.sleep(1000);
		}

		// Return results to Java
		double[][] Traj = eng.getVariable("Traj");

		eng.close();
		return Traj;
	}*/
}
