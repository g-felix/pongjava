import java.awt.*;
import static java.lang.Math.round;

/**
	Esta classe representa os jogadores (players) do jogo. A classe princial do jogo (Pong)
	instancia dois objetos deste tipo quando a execução é iniciada.
*/

public class Player {

	private double cx, cy, width, height, speed;
	private Color color;
	private String id;
	private double[] v_limit;

	/**
		Construtor da classe Player.

		@param cx coordenada x da posição inicial do player (centro do retangulo que o representa).
		@param cy coordenada y da posição inicial do player (centro do retangulo que o representa).
		@param width largura do retangulo que representa o player.
		@param height altura do retangulo que representa o player.
		@param color cor do player.
		@param id uma string que identifica o player
		@param v_limit um array de double contendo dois valores (em pixels) que determinam os limites verticais da área útil da quadra.   
		@param speed velocidade do movimento vertical do player (em pixels por millisegundo).
	*/

	public Player(double cx, double cy, double width, double height, Color color, String id, double [] v_limit, double speed){
		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.color = color;
		this.id = id;
		this.v_limit = v_limit;
	}

	/**
		Método chamado sempre que o player precisa ser (re)desenhado.
	*/

	public void draw() {
		GameLib.setColor(this.getColor());
		GameLib.fillRect(this.getCx(), this.getCy(), this.getWidth(), this.getHeight());
	}

	/**
		Método chamado quando se deseja mover o player para cima. 
		Este método é chamado sempre que a tecla associada à ação 
		de mover o player para cima estiver pressionada.

		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void moveUp(long delta) {
		if(this.getCy() + this.getHeight()/2 < this.getUpperEdge())
			this.cy += round((double) delta * this.speed);
	}

	/**
		Método chamado quando se deseja mover o player para baixo. 
		Este método é chamado sempre que a tecla associada à ação 
		de mover o player para baixo estiver pressionada.

		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void moveDown(long delta) { //OK
		if(this.getCy() - this.getHeight()/2 > this.getBottomEdge())
			this.cy -= round((double) delta * this.speed);
	}

	/**
		Método que devolve a string de identificação do player.
		@return a String de indentificação.
	*/

	public String getId() { 
		return this.id; 
	}

	/**
		Método que devolve a largura do retangulo que representa o player.
		@return um double com o valor da largura.
	*/

	public double getWidth() { 
		return this.width; 
	}

	/**
		Método que devolve a algura do retangulo que representa o player.
		@return um double com o valor da altura.
	*/

	public double getHeight() { 
		return this.height;
	}

	/**
		Método que devolve a coordenada x do centro do retangulo que representa o player.
		@return o valor double da coordenada x.
	*/

	public double getCx() { 
		return round(this.cx);
	}

	/**
		Método que devolve a coordenada y do centro do retangulo que representa o player.
		@return o valor double da coordenada y.
	*/

	public double getCy() { 
		return round(this.cy);
	}

	/**
		Método que devolve a cor do jogador.
		@return a Color do jogador.
	*/

	private Color getColor() {
		return this.color;
	}

	/**
		Método que retorna o limite superior.
		@return valor em double que representa o limite superior do movimento do jogador.
	*/

	private double getUpperEdge() {
		return this.v_limit[1];
	}

	/**
		Método que retorna o limite inferior.
		@return valor em double que representa o limite inferior do movimento do jogador.
	*/

	private double getBottomEdge() {
		return this.v_limit[0];
	}
}

