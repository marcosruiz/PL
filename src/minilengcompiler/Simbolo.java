package minilengcompiler;

public class Simbolo {
	public enum TipoSimbolo {
		PROGRAMA, VARIABLE, ACCION, PARAMETRO
	};

	public enum TipoVariable {
		DESCONOCIDO, ENTERO, BOOLEANO, CHAR, CADENA
	};

	public enum ClaseParametro {
		VAL, REF
	};

	private String nombre;
	private int nivel;
	private TipoSimbolo tipo;
	private TipoVariable variable;
	private ClaseParametro parametro;
	private boolean visible;
	private Simbolo[] listaParametros;
	private int dir;

	public Simbolo(String nombre, int nivel, TipoSimbolo tipo,
			TipoVariable variable, ClaseParametro parametro, boolean visible,
			Simbolo[] listaParametros, int dir) {
		this.nombre = nombre;
		this.nivel = nivel;
		this.tipo = tipo;
		this.variable = variable;
		this.parametro = parametro;
		this.visible = visible;
		this.listaParametros = listaParametros;
		this.dir = dir;
	}

	public void introducirParametro(String nombre, TipoVariable variable,
			ClaseParametro parametro, int nivel) {
		this.nombre = nombre;
		this.variable = variable;
		this.parametro = parametro;
		this.nivel = nivel;
		//TODO creo que hay que gestionar la visibilidad
	}
	public void introducirAccion(){
		//TODO
	}
	public void introducirVariable(){
		//TODO
	}
	public boolean esVariable() {
		return tipo == TipoSimbolo.VARIABLE;
	}

	public boolean esParametro() {
		return tipo == TipoSimbolo.PARAMETRO;
	}

	public boolean esAccion() {
		return tipo == TipoSimbolo.ACCION;
	}

	public boolean esValor() {
		return tipo == TipoSimbolo.PARAMETRO && parametro == ClaseParametro.VAL;
	}
	public boolean esReferencia(){
		return tipo == TipoSimbolo.PARAMETRO && parametro == ClaseParametro.REF;
	}

	/*
	 * Getters y setters
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public TipoSimbolo getTipo() {
		return tipo;
	}

	public void setTipo(TipoSimbolo tipo) {
		this.tipo = tipo;
	}

	public TipoVariable getVariable() {
		return variable;
	}

	public void setVariable(TipoVariable variable) {
		this.variable = variable;
	}

	public ClaseParametro getParametro() {
		return parametro;
	}

	public void setParametro(ClaseParametro parametro) {
		this.parametro = parametro;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Simbolo[] getListaParametros() {
		return listaParametros;
	}

	public void setListaParametros(Simbolo[] listaParametros) {
		this.listaParametros = listaParametros;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

}
