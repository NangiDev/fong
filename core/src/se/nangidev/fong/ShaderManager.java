package se.nangidev.fong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ShaderManager {

	private ShaderProgram normalShader = new ShaderProgram(
	// VertexShader
			"attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
					+ "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
					+ "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE
					+ "0;\n" +

					"uniform mat4 u_projTrans;\n" + " \n"
					+ "varying vec4 vColor;\n" + "varying vec2 vTexCoord;\n"
					+ "//varying vec4 position;\n" + "void main() {\n"
					+ "	vColor = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
					+ "	vTexCoord = " + ShaderProgram.TEXCOORD_ATTRIBUTE
					+ "0;\n" + "	gl_Position =  u_projTrans * "
					+ ShaderProgram.POSITION_ATTRIBUTE + ";\n"
					+ "   //position = gl_Position;\n" + "}",
			// FragmentShader
			// GL ES specific stuff
			"#ifdef GL_ES\n" //
					+ "#define LOWP lowp\n" //
					+ "precision highp float;\n" //
					+ "#else\n" //
					+ "#define LOWP \n" //
					+ "#endif\n"
					+ //
					"//attributes from vertex shader\n"
					+ "//varying vec4 position;\n"
					+ "varying LOWP vec4 vColor;\n"
					+ "varying LOWP vec2 vTexCoord;\n"
					+ "\n"
					+ "//our texture samplers\n"
					+ "uniform sampler2D u_texture;   //diffuse map\n"
					+ "uniform sampler2D u_normals;   //normal map\n"
					+ "\n"
					+ "//values used for shading algorithm...\n"
					+ "uniform LOWP vec2 Resolution;         //resolution of screen\n"
					+ "uniform LOWP vec4 LightColor;    //light RGBA -- alpha is intensity\n"
					+ "uniform LOWP vec4 AmbientColor;  //ambient RGBA -- alpha is intensity \n"
					+ "uniform LOWP vec3 lightPositions[10];\n"
					+ "uniform LOWP vec3 lightColors[10];"
					+ "uniform int nLights;"
					+ "\n"
					+ "void main() {\n"
					+ "	vec3 position = vec3(gl_FragCoord.x/Resolution.x, gl_FragCoord.y/Resolution.y, 0.0);\n"
					+ "	vec3 Ca = AmbientColor.rgb;\n"
					+ "	vec3 La = vec3(0.2, 0.2, 0.2);\n"
					+ "	vec3 Cs = vec3(0.2, 0.2, 0.2);\n"
					+ "	vec4 Cd = texture2D(u_texture, vTexCoord);\n"
					+ "	vec3 NormalMap = texture2D(u_normals, vTexCoord).rgb;\n"
					+ "	vec3 N = normalize(NormalMap);\n"
					+ "	float f = 20.0;\n"
					+ "	vec3 c = Ca*La;\n"
					+ "	vec3 eyePos = vec3(0.5, 0.5, 1000.0);"
					+ "	vec3 Ve = normalize(eyePos - position);\n"
					+ "const int MAX_ITERATIONS = 100;\n"
					+ "   for(int i = 0; i<MAX_ITERATIONS; i++){\n"
					+ "     if (i >= nLights){break;}\n"
					+ "		vec3 Ls = lightColors[i];\n"
					+ "		vec3 Ld = lightColors[i];\n"
					+ "   	vec3 Vl = lightPositions[i] - position;"
					+ "       float distSq = Vl.x*Vl.x + Vl.y*Vl.y + Vl.z*Vl.z;"
					+ "   	Vl = normalize(Vl);"
					+ "   	vec3 rL = reflect(N, Vl);\n"
					+ "   	c +=  1.0/distSq * (Cd.rgb * Ld * (max(0.0, dot(N, Vl))) + Cs*Ls*pow(max(0.0, dot(rL, Ve)), f));\n"
					+ "	}\n" + "	gl_FragColor = vec4(c, Cd.a);\n" + "}");

	private ShaderProgram defaultShader = SpriteBatch.createDefaultShader();

	private ArrayList<se.nangidev.fong.LightSource> lights = new ArrayList<se.nangidev.fong.LightSource>();
	private se.nangidev.fong.LightSource sun;
	private SpriteBatch batch;
	private float[][] lightPositions = new float[10][3];
	private float[][] lightColors = new float[10][3];

	public ShaderManager(SpriteBatch batch) {
		this.batch = batch;
		shaderCompiled(normalShader);

		sun = new se.nangidev.fong.LightSource(0, FongMain.screenHeight, this);
		sun.setSunLight();

		ShaderProgram.pedantic = false;
		normalShader.begin();
		normalShader.setUniformi("u_normals", 1);
		normalShader.setUniformf("AmbientColor", 0.6f, 0.6f, 1.0f);
		normalShader.setUniformf("Resolution", FongMain.screenWidth,
				FongMain.screenHeight);
		normalShader.end();
	}

	public void sortLightsByDistance(Vector2 spritePos) {
		for (se.nangidev.fong.LightSource light : lights)
			light.setDistance(spritePos);
		sortLights();
		int index = lights.indexOf(sun);
		lights.remove(index);
		lights.add(0, sun);
	}

	private void sortLights() {
		Collections.sort(lights, new Comparator<se.nangidev.fong.LightSource>() {
			@Override
			public int compare(se.nangidev.fong.LightSource l1, se.nangidev.fong.LightSource l2) {
				return l1.compareTo(l2);
			}
		});
	}

	public void addLight(se.nangidev.fong.LightSource light) {
		lights.add(light);
	}

	public void removeLight(se.nangidev.fong.LightSource light) {
		lights.remove(light);
	}

	public ArrayList<se.nangidev.fong.LightSource> getLightList() {
		return lights;
	}

	public void updateLightRotation() {
		if (!FongMain.gameRunning)
			sun.updateLight();
	}

	public void setDefaultSunPosition() {
		sun.setPos(0, FongMain.screenHeight, 2.0f);
	}

	public void passLights() {
		int i;
		for (i = 0; i < 10; i++) {
			lightColors[i][0] = 0.0f;
			lightColors[i][1] = 0.0f;
			lightColors[i][2] = 0.0f;
		}
		for (i = 0; i < lights.size() && i < FongMain.lightCounter; i++) {
			LightSource light = lights.get(i);
			lightPositions[i][0] = light.getPos().x;
			lightPositions[i][1] = light.getPos().y;
			lightPositions[i][2] = light.getPos().z;
			lightColors[i][0] = light.getColor().x;
			lightColors[i][1] = light.getColor().y;
			lightColors[i][2] = light.getColor().z;

			/*
			 * normalShader.setUniformf("LightColor", light.getColor().x,
			 * light.getColor().y, light.getColor().z, light.getIntensity());
			 * normalShader.setUniformf("Falloff", light.getFallOff());
			 * normalShader.setUniformf("LightPos", light.getPos());
			 */
		}
		/*
		 * for(int j = i; j < 10; j++){ lightPositions[j][0] = 0.0f;
		 * lightPositions[j][1] = 0.0f; lightPositions[j][2] = 0.0f;
		 * lightPositions[j][3] = 0.0f; lightColors[j][0] = 0.0f;
		 * lightColors[j][1] = 0.0f; lightColors[j][2] = 0.0f; lightColors[j][0]
		 * = 0.0f; }
		 */
		normalShader.begin();
		normalShader.setUniformi("nLights", i);
		// System.out.println("nLights: " + i);
		for (int j = 0; j < i; j++) {
			normalShader.setUniformf("lightPositions[" + j + "]",
					lightPositions[j][0], lightPositions[j][1],
					lightPositions[j][2]);
			normalShader.setUniformf("lightColors[" + j + "]",
					lightColors[j][0], lightColors[j][1], lightColors[j][2]);
			// System.out.println("LightPos: "+lightPositions[j][0]);
			// System.out.println("LightCol: "+lightColors[j][0]);
		}
		normalShader.end();
	}

	public void shaderCompiled(ShaderProgram shader) {
		if (!shader.isCompiled())
			throw new GdxRuntimeException("Could not compile shader: "
					+ shader.getLog());
		else
			System.out.println("Shader copiled!");

		if (shader.getLog().length() != 0)
			System.out.println(shader.getLog());
	}

	public void switchToNormalShader(SpriteBatch batch) {
		batch.setShader(normalShader);
	}

	public void switchToDefaultShader(SpriteBatch batch) {
		batch.setShader(defaultShader);
	}

	public void clearLightSources() {
		if (lights != null) {
			lights.clear();
			lights.add(sun);
		}
	}

	public void draw(se.nangidev.fong.DrawComponent iTick) {
		if(iTick instanceof Shadable && FongMain.lightOn){
			switchToNormalShader(batch);
			sortLightsByDistance(new Vector2(((Shadable) iTick).getX(), ((Shadable) iTick).getY()));
			passLights();
			((Shadable)iTick).bind();
			((Shadable)iTick).draw();
		} //else {
			//switchToDefaultShader(batch);
			//((DrawComponent)iTick).draw();
		//}
		((DrawComponent)iTick).draw();
		switchToDefaultShader(batch);
	}
}
