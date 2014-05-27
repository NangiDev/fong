package com.me.fong;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;


public class ShaderManager {
		
	private ShaderProgram normalShader = new ShaderProgram(
					//VertexShader
					"attribute vec4 "+ShaderProgram.POSITION_ATTRIBUTE+";\n" +
					"attribute vec4 "+ShaderProgram.COLOR_ATTRIBUTE+";\n" +
					"attribute vec2 "+ShaderProgram.TEXCOORD_ATTRIBUTE+"0;\n" +
				
					"uniform mat4 u_projTrans;\n" + 
					" \n" + 
					"varying vec4 vColor;\n" +
					"varying vec2 vTexCoord;\n" +
					"varying vec4 position;\n" + 
					"void main() {\n" +  
					"	vColor = "+ShaderProgram.COLOR_ATTRIBUTE+";\n" +
					"	vTexCoord = "+ShaderProgram.TEXCOORD_ATTRIBUTE+"0;\n" +
					"	gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" +
					"   position = gl_Position;\n"+ 
					"}",
					//FragmentShader
					//GL ES specific stuff
				  	"#ifdef GL_ES\n" //
					+ "#define LOWP lowp\n" //
					+ "precision mediump float;\n" //
					+ "#else\n" //
					+ "#define LOWP \n" //
					+ "#endif\n" + //
					"//attributes from vertex shader\n" + 
					"varying vec4 position;\n" +
					"varying LOWP vec4 vColor;\n" + 
					"varying vec2 vTexCoord;\n" + 
					"\n" + 
					"//our texture samplers\n" + 
					"uniform sampler2D u_texture;   //diffuse map\n" + 
					"uniform sampler2D u_normals;   //normal map\n" + 
					"\n" + 
					"//values used for shading algorithm...\n" + 
					"uniform vec2 Resolution;         //resolution of screen\n" + 
					"uniform vec3 LightPos;           //light position, normalized\n" + 
					"uniform LOWP vec4 LightColor;    //light RGBA -- alpha is intensity\n" + 
					"uniform LOWP vec4 AmbientColor;  //ambient RGBA -- alpha is intensity \n" + 
					"uniform vec3 Falloff;            //attenuation coefficients\n" + 
					"uniform vec4 lightPositions[10];\n" +
					"uniform vec4 lightColors[10];" +
					"uniform int nLights;" +
					"\n" + 
					"void main() {\n" + 
					"	//RGBA of our diffuse color\n" + 
					"	//vec4 DiffuseColor = texture2D(u_texture, vTexCoord);\n" + 
					"	\n" + 
					"	//RGB of our normal map\n" + 
					"	//vec3 NormalMap = texture2D(u_normals, vTexCoord).rgb;\n" + 
					"	\n" + 
					"	//The delta position of light\n" + 
					"	//vec3 LightDir = vec3(LightPos.xy - (gl_FragCoord.xy / Resolution.xy), LightPos.z);\n" + 
					"	\n" + 
					"	//Correct for aspect ratio\n" + 
					"	//LightDir.x *= Resolution.x / Resolution.y;\n" + 
					"	\n" + 
					"	//Determine distance (used for attenuation) BEFORE we normalize our LightDir\n" + 
					"	//float D = length(LightDir);\n" + 
					"	\n" + 
					"	//normalize our vectors\n" + 
					"	//vec3 N = normalize(NormalMap * 2.0 - 1.0);\n" + 
					"	//vec3 L = normalize(LightDir);\n" + 
					"	\n" + 
					"	//Pre-multiply light color with intensity\n" + 
					"	//Then perform \"N dot L\" to determine our diffuse term\n" + 
					"	//vec3 Diffuse = (LightColor.rgb * LightColor.a) * max(dot(N, L), 0.0);\n" + 
					"\n" + 
					"	//pre-multiply ambient color with intensity\n" + 
					"	//vec3 Ambient = AmbientColor.rgb * AmbientColor.a;\n" + 
					"	\n" + 
					"	//calculate attenuation\n" + 
					"	//float Attenuation = 1.0 / ( Falloff.x + (Falloff.y*D) + (Falloff.z*D*D) );\n" + 
					"	\n" + 
					"	//the calculation which brings it all together\n" + 
					"	//vec3 Intensity = Ambient + Diffuse * Attenuation;\n" + 
					"	//vec3 FinalColor = DiffuseColor.rgb * Intensity;\n" + 
					"	//gl_FragColor = vColor * vec4(FinalColor, DiffuseColor.a);\n" + 
					
					"	vec4 Ca = AmbientColor;\n" +
					"	vec4 La = vec4(0.2, 0.2, 0.2, 0.0);\n" +
					"	vec4 ambient = Ca * La;\n" +
					"	vec4 Cs = vec4(1.0, 1.0, 1.0, 0.0);\n" +	
					"	vec3 finalColor = vec3(0.0, 0.0, 0.0);\n" +
					"	vec4 Cd = texture2D(u_texture, vTexCoord);\n" +	
					"	vec4 Ls = vec4(3.0, 3.0, 3.0, 0.0);\n" +
					"	vec4 NormalMap2 = texture2D(u_normals, vTexCoord);\n" +
					"	vec4 N = normalize(NormalMap2 * 2.0 - 1.0);\n" +
					"	float f = 50.0;\n" +			
					"	vec4 spec = Cs * Ls;" + 
					"   for(int i = 0; i<nLights; i++){\n"	+
					"		vec4 Ld = lightColors[i];\n" +											
					"   	vec4 Vl = vec4(normalize(lightPositions[i] - position));" +
					"   	vec4 rL = reflect(N, Vl);\n" +
					"		vec4 Ve = normalize(position);\n" +
					"   	vec4 c =  Ld * (max(dot(N, Vl),0.0)) +  pow(max(dot(rL, Ve), 0.0), f);\n" +
					"		finalColor += c.rgb;\n" +
					"	}\n" +
					"	gl_FragColor = vec4(finalColor, Cd.a) + ambient * spec * Cd ;\n" +
					"}");
	
	private ShaderProgram defaultShader = SpriteBatch.createDefaultShader();
	
	private Array<LightSource> lights = new Array<LightSource>();
	private LightSource sun;
	
	public ShaderManager(){
		
		shaderCompiled(normalShader);
		
		sun = new LightSource(0.5f, 0.5f, this);
		//LightSource sun2 = new LightSource(0.0f, 0.0f, this);
		sun.setSunLight();
		//sun2.setBlueLaserLight();
		
		ShaderProgram.pedantic = false;
		normalShader.begin();
		normalShader.setUniformi("u_normals", 1);
		normalShader.setUniformf("AmbientColor", 0.6f, 0.6f, 1.0f);
		normalShader.setUniformf("Resolution", MyGame.screenWidth, MyGame.screenHeight);
		normalShader.end();
		//normalShader.dispose();
		
	}
	
	public void addLight(LightSource light){
		lights.add(light);
	}
	
	public Array<LightSource> getLightList(){
		return lights;
	}
	
	public void passLights(){
		float[][] lightPositions = new float[10][4];
		float[][] lightColors = new float[10][4];
		int i = 0;
		for(LightSource light : lights){
			lightPositions[i][0] = light.getPos().x;
			lightPositions[i][1] = light.getPos().y;
			lightPositions[i][2] = light.getPos().z;
			lightPositions[i][3] = 0.0f;
			lightColors[i][0] = light.getColor().x;
			lightColors[i][1] = light.getColor().y;
			lightColors[i][2] = light.getColor().z;
			lightColors[i][0] = light.getIntensity();
			i++;
			/*normalShader.setUniformf("LightColor", light.getColor().x, light.getColor().y, light.getColor().z, light.getIntensity());
			normalShader.setUniformf("Falloff", light.getFallOff());
			normalShader.setUniformf("LightPos", light.getPos());*/
		}
		/*for(int j = i; j < 10; j++){
			lightPositions[j][0] = 0.0f;
			lightPositions[j][1] = 0.0f;
			lightPositions[j][2] = 0.0f;
			lightPositions[j][3] = 0.0f;
			lightColors[j][0] = 0.0f;
			lightColors[j][1] = 0.0f;
			lightColors[j][2] = 0.0f;
			lightColors[j][0] = 0.0f;	
		}*/
		normalShader.begin();
		normalShader.setUniformi("nLights", i);
		//System.out.println("nLights: " + i);
		for(int j = 0; j < i; j++){
			normalShader.setUniformf("lightPositions[" + j + "]", lightPositions[j][0],lightPositions[j][1], lightPositions[j][2], lightPositions[j][3]);
			normalShader.setUniformf("lightColors[" + j +"]", lightColors[j][0],lightColors[j][1], lightColors[j][2], lightPositions[j][3]);
		}
		normalShader.end();
	}
	
	public void shaderCompiled(ShaderProgram shader){
		if (!shader.isCompiled())
			throw new GdxRuntimeException("Could not compile shader: "+shader.getLog());
		else
			System.out.println("Shader copiled!");
		
		if (shader.getLog().length()!=0)
			System.out.println(shader.getLog());
	}
	
	public void switchToNormalShader(SpriteBatch batch){
		batch.setShader(normalShader);
	}
	
	public void switchToDefaultShader(SpriteBatch batch){
		batch.setShader(defaultShader);
	}
	
	public String getlog(){
		return defaultShader.getLog();
	}

}
