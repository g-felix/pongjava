import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import static java.lang.Math.round;

/**
	Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
	instancia um objeto deste tipo quando a execução é iniciada.
*/

public class Ball {

	private double cx, cy, width, height, speed;
	private Color color;
	private double xDir, yDir;
	private Rectangle2D hitbox;

	/**
		Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
		(em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
		aleatóriamente pelo construtor.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
		@param hitbox dimensões da caixa de colisão da bola.
	*/

	public Ball(double cx, double cy, double width, double height, Color color, double speed) {
		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.color = color;
		this.xDir = 1;
		this.yDir = 1;
		this.hitbox = new Rectangle2D.Double(cx - width/2, cy - height/2, width, height);
	}


	/**
		Método chamado sempre que a bola precisa ser (re)desenhada.
	*/

	public void draw() {
		GameLib.setColor(this.color);
		GameLib.fillRect(this.cx, this.cy, this.width, this.height);
	}

	/**
		Método chamado quando o estado (posição) da bola precisa ser atualizado.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void update(long delta) { //OK
		double distance = (double) delta * this.speed;
		this.cx += round(distance * this.xDir);
		this.cy += round(distance * this.yDir);
		this.hitbox.setRect(this.getCx() - this.getWidth()/2, this.getCy() - this.getHeight()/2, this.getWidth(), this.getHeight());
	}

	/**
		Método que decide aleatoriamente o comportamento da direção vertical da bola após uma colisão com o jogador.

		@return 1 ou -1 o que mudará a direção da bola verticalmente.
	*/

	private double changeDirection() {
		Random randomizer = new Random();
		if(randomizer.nextInt(2) == 1) {
			return 1.0;
		} else {
			return -1.0;
		}
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com um jogador.
	
		@param playerId uma string cujo conteúdo identifica um dos jogadores.
	*/

	public void onPlayerCollision(String playerId){
		if(playerId == "Player 1") {
			this.xDir = -this.xDir;
			this.yDir = this.changeDirection() * this.yDir;
		}
		if(playerId == "Player 2") {
			this.xDir = -this.xDir;
			this.yDir = this.changeDirection() * this.yDir;
		}
		return;
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com uma parede.

		@param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	*/

	public void onWallCollision(String wallId) {
		if(wallId == "Left") {
			this.xDir = 1;
		}
		if(wallId == "Right") {
			this.xDir = -1;
		}
		if(wallId == "Top") {
			this.yDir = 1;
		}
		if(wallId == "Bottom") {
			this.yDir = -1;
		}
	}

	/**
		Método que verifica se houve colisão da bola com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/

	public boolean checkCollision(Wall wall) {
		Rectangle2D wallHitbox = new Rectangle2D.Double(round(wall.getCx() - wall.getWidth()/2), round(wall.getCy() - wall.getHeight()/2), wall.getWidth(), wall.getHeight());
		return this.hitbox.intersects(wallHitbox);
	}

	/**
		Método que verifica se houve colisão da bola com um jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	

	public boolean checkCollision(Player player) {
		Rectangle2D playerHitbox = new Rectangle2D.Double(round(player.getCx() - player.getWidth()/2), round(player.getCy() - player.getHeight()/2), player.getWidth(), player.getHeight());
		return this.hitbox.intersects(playerHitbox);
	}

	/**
		Método que devolve a largura do retângulo que representa a bola.
		@return o valor double da largura do retângulo da bola.
	*/

	public double getWidth() {
		return this.width;
	}

	/**
		Método que devolve a altura do retângulo que representa a bola.
		@return o valor double da altura do retângulo da bola.
	*/

	public double getHeight() {
		return this.height;
	}
	
	/**
		Método que devolve a coordenada x do centro do retângulo que representa a bola.
		@return o valor double da coordenada x.
	*/

	public double getCx() {
		return round(this.cx);
	}

	/**
		Método que devolve a coordenada y do centro do retângulo que representa a bola.
		@return o valor double da coordenada y.
	*/

	public double getCy() {
		return round(this.cy);
	}

	/**
		Método que devolve a velocidade da bola.
		@return o valor double da velocidade.

	*/

	public double getSpeed() {
		return this.speed;
	}

}
