package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class MainMenuScreen implements Screen {

	private MyGame game;
	private Texture header;
	private Texture headerNormals;
	private Label signature;
	private TextButton startButton;
	private TextButton highscoreButton;
	private TextButton howToPlayButton;
	private TextButton optionsButton;
	private TextButton creditsButton;
	private ShaderProgram shader;
	
	private String vertexShader;
	private String fragmentShader;
	
	//Constants
	public static final float DEFAULT_LIGHT_Z = 0.1f;
	public static final float AMBIENT_INTENSITY = 0.2f;
	public static final float LIGHT_INTENSITY = 5f;
	
	public static final Vector3 LIGHT_POS = new Vector3(0f,0f,DEFAULT_LIGHT_Z);
	
	//Light RGB and intensity (alpha)
	public static final Vector3 LIGHT_COLOR = new Vector3(1f, 1f, 1f);
 
	//Ambient RGB and intensity (alpha)
	public static final Vector3 AMBIENT_COLOR = new Vector3(0.6f, 0.6f, 1f);
 
	//Attenuation coefficients for light falloff
	public static final Vector3 FALLOFF = new Vector3(.4f, 3f, 20f);

	public MainMenuScreen(MyGame myGame) {
		this.game = myGame;
		
		vertexShader = 
					"attribute vec4 "+ShaderProgram.POSITION_ATTRIBUTE+";\n" +
					"attribute vec4 "+ShaderProgram.COLOR_ATTRIBUTE+";\n" +
					"attribute vec2 "+ShaderProgram.TEXCOORD_ATTRIBUTE+"0;\n" +
				
					"uniform mat4 u_projTrans;\n" + 
					" \n" + 
					"varying vec4 vColor;\n" +
					"varying vec2 vTexCoord;\n" +
				
					"void main() {\n" +  
					"	vColor = "+ShaderProgram.COLOR_ATTRIBUTE+";\n" +
					"	vTexCoord = "+ShaderProgram.TEXCOORD_ATTRIBUTE+"0;\n" +
					"	gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" +
					"}";
		
		fragmentShader = 
					//GL ES specific stuff
				  	"#ifdef GL_ES\n" //
					+ "#define LOWP lowp\n" //
					+ "precision mediump float;\n" //
					+ "#else\n" //
					+ "#define LOWP \n" //
					+ "#endif\n" + //
					"//attributes from vertex shader\n" + 
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
					"\n" + 
					"void main() {\n" + 
					"	//RGBA of our diffuse color\n" + 
					"	vec4 DiffuseColor = texture2D(u_texture, vTexCoord);\n" + 
					"	\n" + 
					"	//RGB of our normal map\n" + 
					"	vec3 NormalMap = texture2D(u_normals, vTexCoord).rgb;\n" + 
					"	\n" + 
					"	//The delta position of light\n" + 
					"	vec3 LightDir = vec3(LightPos.xy - (gl_FragCoord.xy / Resolution.xy), LightPos.z);\n" + 
					"	\n" + 
					"	//Correct for aspect ratio\n" + 
					"	LightDir.x *= Resolution.x / Resolution.y;\n" + 
					"	\n" + 
					"	//Determine distance (used for attenuation) BEFORE we normalize our LightDir\n" + 
					"	float D = length(LightDir);\n" + 
					"	\n" + 
					"	//normalize our vectors\n" + 
					"	vec3 N = normalize(NormalMap * 2.0 - 1.0);\n" + 
					"	vec3 L = normalize(LightDir);\n" + 
					"	\n" + 
					"	//Pre-multiply light color with intensity\n" + 
					"	//Then perform \"N dot L\" to determine our diffuse term\n" + 
					"	vec3 Diffuse = (LightColor.rgb * LightColor.a) * max(dot(N, L), 0.0);\n" + 
					"\n" + 
					"	//pre-multiply ambient color with intensity\n" + 
					"	vec3 Ambient = AmbientColor.rgb * AmbientColor.a;\n" + 
					"	\n" + 
					"	//calculate attenuation\n" + 
					"	float Attenuation = 1.0 / ( Falloff.x + (Falloff.y*D) + (Falloff.z*D*D) );\n" + 
					"	\n" + 
					"	//the calculation which brings it all together\n" + 
					"	vec3 Intensity = Ambient + Diffuse * Attenuation;\n" + 
					"	vec3 FinalColor = DiffuseColor.rgb * Intensity;\n" + 
					"	gl_FragColor = vColor * vec4(FinalColor, DiffuseColor.a);\n" + 
					"}";
		
		header = new Texture(Gdx.files.internal("menu/logotype.png"));
		headerNormals = new Texture(Gdx.files.internal("menu/logotypeNormal.png"));
		
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(vertexShader, fragmentShader);
		
		//Kolla så att shadern kompilerar
		if (!shader.isCompiled())
			throw new GdxRuntimeException("Could not compile shader: "+shader.getLog());
		else
			System.out.println("Shader copiled!");
		
		if (shader.getLog().length()!=0)
			System.out.println(shader.getLog());
		
		
		
		//Sätt upp uniformer
		shader.begin();
		shader.setUniformi("u_normals", 1);
		
		shader.setUniformf("LightColor", LIGHT_COLOR.x, LIGHT_COLOR.y, LIGHT_COLOR.z, LIGHT_INTENSITY);
		shader.setUniformf("AmbientColor", AMBIENT_COLOR.x, AMBIENT_COLOR.y, AMBIENT_COLOR.z, AMBIENT_INTENSITY);
		shader.setUniformf("Falloff", FALLOFF);
		shader.end();
		
		game.batch = new SpriteBatch(1000, shader);
		game.batch.setShader(shader);
		
		
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}

	public void update(float delta) {
		if(Gdx.input.isTouched()){
			System.out.println("LIGHT_POS updated");
			LIGHT_POS.x = (Gdx.input.getX() / game.screenWidth);
			LIGHT_POS.y = 1-(Gdx.input.getY() / game.screenHeight);
		}
	}

	public void draw(float delta) {
		game.batch.begin();
		game.batch.setShader(game.batch.createDefaultShader());
		game.drawBackground();
		
		game.batch.setShader(shader);
		
		shader.setUniformf("LightPos", LIGHT_POS);
		
		headerNormals.bind(1);
		
		header.bind(0);
	
		game.batch.draw(header, (MyGame.screenWidth * 0.5f)
				- (header.getWidth() * 0.5f * MyGame.scaleX),
				MyGame.screenHeight * 0.7f, header.getWidth()
						* MyGame.scaleX, header.getHeight()
						* MyGame.scaleY);
		
		game.batch.setShader(game.batch.createDefaultShader());
		
		signature.draw(game.batch, 1);

		game.table.draw(game.batch, 1);

		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		game.camera.setToOrtho(false, width, height);
		game.batch.setProjectionMatrix(game.camera.combined);
		
		shader.begin();
		game.batch.setShader(shader);
		shader.setUniformf("Resolution", width, height);
		shader.end();
	}

	@Override
	public void show() {
		createScreen();
		setupMenuLayout();
	}

	@Override
	public void hide() {
		game.table.clearChildren();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		
	}
	
	private void createScreen(){
		//this.header = new Texture(Gdx.files.internal("menu/logotype.png"));
		//this.headerNormals = new Texture(Gdx.files.internal("menu/logotypeNormal.png"));
		signature = new Label("A game made by\nJoel Setterberg &\nJonatan Elsgard", game.smalllabelStyle);
		signature.setAlignment(Align.center);
		signature.setWidth(MyGame.screenWidth);
		signature.setWrap(true);
		signature.setPosition(0, signature.getHeight() * 0.5f * MyGame.scaleY);
		

		startButton = new MenuButton("Start", game.mediumButtonStyle,
				GameState.Game, game);
		highscoreButton = new MenuButton("Highscore", game.mediumButtonStyle,
				GameState.Highscore, game);
		howToPlayButton = new MenuButton("How To Play", game.mediumButtonStyle,
				GameState.Instructions, game);
		optionsButton = new MenuButton("Options", game.mediumButtonStyle,
				GameState.Options, game);
		creditsButton = new MenuButton("Credits", game.mediumButtonStyle,
				GameState.Credits, game);
		
		System.out.println("new MainMenuScreen created");
	}

	private void setupMenuLayout() {
		game.table.add().row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(startButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(highscoreButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(optionsButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(howToPlayButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(creditsButton);
		
		game.table.padTop(header.getHeight() * 1.5f * MyGame.scaleY);
	}
}
