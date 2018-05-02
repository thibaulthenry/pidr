package main.graphics.entities;

import main.graphics.path.CSVConverter;
import main.parameters.EntityManager;
import main.parameters.TrajectoryManager;

public class TrajectorySphere extends Entity {
	
	public TrajectorySphere(int index) {
		super(EntityManager.sphereTexturedModel, CSVConverter.getPosition(index), 0, 0, 0, TrajectoryManager.SPHERE_SCALE);
	}

}
