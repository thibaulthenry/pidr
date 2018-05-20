package main.graphics.renderer;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import main.graphics.entities.Entity;
import main.graphics.entities.Rotor;
import main.graphics.models.RawModel;
import main.graphics.models.TexturedModel;
import main.graphics.path.CSVConverter;
import main.graphics.shaders.StaticShader;
import main.graphics.textures.ModelTexture;
import main.graphics.toolbox.Maths;
import main.parameters.TrajectoryManager;


public class EntityRenderer {
	
	private StaticShader shader;
	private static String[][] rotorRot = CSVConverter.convert("resources/simul/" + TrajectoryManager.CSV_ROTORS + ".csv");
	private int previousi1=0;
	private int previousi2=0;
	private int previousi3=0;
	private int previousi4=0;
	private float angle1=0;
	private float angle2=0;
	private float angle3=0;
	private float angle4=0;

	public EntityRenderer(StaticShader shader, Matrix4f projectionMatrix) {
		this.shader = shader;
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}

	public void render(Map<TexturedModel, List<Entity>> entities) {
		for (TexturedModel model : entities.keySet()) {
			prepareTexturedModel(model);
			List<Entity> batch = entities.get(model);
			for (Entity entity : batch) {
				prepareInstance(entity);
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			}
			unbindTexturedModel();
		}	
	}
	
	private void prepareTexturedModel(TexturedModel model) {
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		ModelTexture texture = model.getTexture();
		
		if (texture.isHasTransparency()) {
			MasterRenderer.disableCulling();
		}
		shader.loadFakeUseLightingVariable(texture.isUseFakeLighting());
		shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
	}
	
	private void unbindTexturedModel() {
		MasterRenderer.enableCulling();
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}
	
	private void prepareInstance(Entity entity) {
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
		if( entity instanceof Rotor) {
			double x;
			double y;
			double z;
			switch(entity.getId()){
			case 1:
				x=7.76;
				y=-5.2;
				z=0.4;
				break;
			case 2:
				x=-7.45;
				y=-6.1;
				z=0.4;
				break;
			case 3:
				x=7.76;
				y=4.60;
				z=0.4;
				break;
			default:
				x=-7.76;
				y=4.85;
				z=0.4;
				break;
			}
			
			float X=(float) x;
			float Y=(float) y;
			float Z=(float) z;
			
			transformationMatrix.translate(new Vector3f(
					X,
					Z,
					Y));
			
			float nbrtourmoyen=0;
			float angle;
			switch(entity.getId()){
			case 1:
				for (int j=previousi1;j<CSVConverter.currentIndex;j++) {
					nbrtourmoyen += Float.parseFloat(rotorRot[2][j]);
				}
				angle1=angle1+ (6 * nbrtourmoyen)/1000;
				this.previousi1=CSVConverter.currentIndex;
				angle=angle1;
				break;
			case 2:
				for (int j=previousi2;j<CSVConverter.currentIndex;j++) {
					nbrtourmoyen += Float.parseFloat(rotorRot[3][j]);
				}
				angle2=angle2- (6 * nbrtourmoyen)/1000;
				this.previousi2=CSVConverter.currentIndex;
				angle=angle2;
				break;
			case 3:
				for (int j=previousi3;j<CSVConverter.currentIndex;j++) {
					nbrtourmoyen += Float.parseFloat(rotorRot[1][j]);
				}
				angle3=angle3- (6 * nbrtourmoyen)/1000;
				this.previousi3=CSVConverter.currentIndex;
				angle=angle3;
				break;
			default:
				for (int j=previousi4;j<CSVConverter.currentIndex;j++) {
					nbrtourmoyen += Float.parseFloat(rotorRot[4][j]);
				}
				angle4=angle4+ (6 * nbrtourmoyen)/1000;
				this.previousi4=CSVConverter.currentIndex;
				angle=angle4;
				break;
			}
			angle=(float) ((float) angle*Math.PI/180);
			
			transformationMatrix.rotate(angle, new Vector3f(
					0,
					1,
					0));
		}
		shader.loadTransformationMatrix(transformationMatrix);
	}

}
