package minilengcompiler;

import java.util.LinkedList;
import java.util.Random;

import minilengcompiler.Simbolo.*;

public class TablaSimbolos {
	private final int TAMANO = 100;
	private int[] tablaPearson;
	private LinkedList<Simbolo>[] tablaDispersionAbierta;
	private String nombrePrograma;
	
	public TablaSimbolos() {
		//tablaDispersionAbierta = new LinkedList[TAMANO];
		tablaPearson = new int[256];
		rellenaYBarajaPearson();
	}
	
	private void rellenaYBarajaPearson() {
		Random r = new Random();
		int aux;
		for(int i=1; i<tablaPearson.length; i++){
			aux = r.nextInt(tablaPearson.length);
			if(tablaPearson[aux] == 0){
				tablaPearson[aux]=i;
			}
			else{
				i--;
			}
		}
	}

	private int pearson(String clave){
		int h = 0;
		for(int i=0; i< clave.length(); i++){
			h = tablaPearson[h^clave.charAt(i)];
		}
		return h%TAMANO;
	}
	
	/**
	 * Crea una tabla de símbolos vacía. Este procedimiento debe invocarse antes
	 * de hacer ninguna operación con la tabla de símbolos.
	 */
	public void inicializarTabla() {
		tablaDispersionAbierta = new LinkedList[TAMANO];
	}

	/**
	 * Busca en la tabla el símbolo de mayor nivel cuyo nombre coincida con el
	 * del parámetro (se distinguen minúsculas y mayúsculas). Si existe,
	 * devuelve un puntero como resultado, de lo contrario lanza una excepción.
	 * 
	 * @param nombre
	 * @return
	 * @throws SimboloNoEncontradoException
	 */
	public Simbolo buscarSimbolo(String nombre) throws SimboloNoEncontradoException{
		LinkedList<Simbolo> tablaAux = tablaDispersionAbierta[pearson(nombre)];
		Simbolo s;
		boolean encontrado = false;
		int i = 0;
		while(i<tablaAux.size() && !encontrado){
			s = tablaAux.get(i);
			if(nombre.equals(s.getNombre())){
				encontrado = true;
			}
			else{
				i++;
			}
		}
		if(encontrado){
			return tablaAux.get(i);
		}
		else{
			throw new SimboloNoEncontradoException();
		}
	}
	private void introducirSimbolo(Simbolo s){
		LinkedList<Simbolo> tablaAux = tablaDispersionAbierta[pearson(s.getNombre())];
		tablaAux.addFirst(s);
	}
	/**
	 * Introduce en la tabla un simbolo PROGRAMA, con el nombre del parametro,
	 * de nivel 0, con la direccion del parametro. Dado que debe ser el primer
	 * simbolo a introducir, NO SE VERIFICA QUE EL SIMBOLO YA EXISTA.
	 * 
	 * @param nombre
	 * @param dir
	 * @return
	 */
	public Simbolo introducirPrograma(String nombre, int dir) {
		nombrePrograma = nombre;
		Simbolo s = new Simbolo(nombre, 0, TipoSimbolo.PROGRAMA, null, null, true, null, dir);
		introducirSimbolo(s);
		return s;
	}
	/**
	 * Si existe un símbolo en la tabla del mismo nivel y con el mismo nombre,
	 * devuelve NULL (o una excepción, esto se deja a vuestra elección. De lo
	 * contrario, introduce un símbolo VARIABLE (simple) con los datos de los
	 * argumentos.
	 * 
	 * @param nombre
	 * @param variable
	 * @param nivel
	 * @param dir
	 * @return
	 */
	public Simbolo introducirVariable(String nombre, TipoVariable variable,
			int nivel, int dir) {
		Simbolo s =null;
		try{
			s = buscarSimbolo(nombre);
			if(s.getNivel() != nivel){
				//Deberia ser: s.getNivel() < nivel
				s = new Simbolo(nombre, nivel, TipoSimbolo.VARIABLE, variable, null, true, null, dir);
				introducirSimbolo(s);
			}
			return s;
			
		}catch(SimboloNoEncontradoException e){
			return s;
		}
	}

	/**
	 * Si existe un símbolo en la tabla del mismo nivel y con el mismo nombre,
	 * devuelve NULL. De lo contrario, introduce un símbolo ACCION con los datos
	 * de los argumentos.
	 * 
	 * @param nombre
	 * @param nivel
	 * @param dir
	 * @return
	 */
	public Simbolo introducirAccion(String nombre, int nivel, int dir) {
		Simbolo s =null;
		try{
			s = buscarSimbolo(nombre);
			if(s.getNivel() != nivel){
				//Deberia ser: s.getNivel() < nivel
				s = new Simbolo(nombre, nivel, TipoSimbolo.ACCION, null, null, true, null, dir);
				introducirSimbolo(s);
			}
			return s;
			
		}catch(SimboloNoEncontradoException e){
			return s;
		}
	}

	/**
	 * Si existe un símbolo en la tabla del mismo nivel y con el mismo nombre,
	 * devuelve NULL. De lo contrario, introduce un símbolo PARAMETRO con los
	 * datos de los argumentos.
	 * 
	 * @param nombre
	 * @param variable
	 * @param parametro
	 * @param nivel
	 * @param dir
	 * @return
	 */
	public Simbolo introducirParametro(String nombre, TipoVariable variable,
			ClaseParametro parametro, int nivel, int dir) {
		Simbolo s =null;
		try{
			s = buscarSimbolo(nombre);
			if(s.getNivel() != nivel){
				//Deberia ser: s.getNivel() < nivel
				s = new Simbolo(nombre, nivel, TipoSimbolo.PARAMETRO, variable, parametro, true, null, dir);
				introducirSimbolo(s);
			}
			return s;
			
		}catch(SimboloNoEncontradoException e){
			return s;
		}
	}
	/**
	 * Elimina el simbolo cuyo nombre y nivel coincide con los parametros
	 * @param nombre
	 * @param nivel
	 * @throws SimboloNoEncontradoException
	 */
	private void eliminarSimbolo(String nombre, int nivel) throws SimboloNoEncontradoException{
		LinkedList<Simbolo> tablaAux = tablaDispersionAbierta[pearson(nombre)];
		Simbolo s;
		boolean encontrado = false;
		int i = 0;
		while(i<tablaAux.size() && !encontrado){
			s = tablaAux.get(i);
			if(nombre.equals(s.getNombre()) && s.isVisible()){
				encontrado = true;
			}
			else{
				i++;
			}
		}
		if(encontrado){
			tablaAux.remove(i);
		}
		else{
			throw new SimboloNoEncontradoException();
		}
	}
	/**
	 * Elimina de la tabla todos los símbolos PROGRAMA de nivel 0 (debería haber
	 * uno solo).
	 * @throws SimboloNoEncontradoException 
	 */
	public void eliminarPrograma() throws SimboloNoEncontradoException {
		eliminarSimbolo(nombrePrograma, 0);
	}

	/**
	 * Elimina de la tabla todas las variables que sean del nivel del argumento.
	 * NO ELIMINA PARÁMETROS
	 * 
	 * @param nivel
	 */
	public void eliminarVariables(int nivel) {
		for(int i=0; i<tablaDispersionAbierta.length; i++){
			LinkedList<Simbolo> tablaAux = tablaDispersionAbierta[i];
			for(Simbolo s: tablaAux){
				if(s.getNivel() == nivel){
					tablaAux.remove(s);
				}
			}
		}
	}

	/**
	 * Marca todos los parámetros de un nivel como ocultos para que no puedan
	 * ser encontrados, pero se mantenga la definición completa de la acción
	 * donde están declarados para verificación de invocaciones a la acción.
	 * 
	 * @param nivel
	 */
	public void ocultarParametros(int nivel) {
		for(int i=0; i<tablaDispersionAbierta.length; i++){
			LinkedList<Simbolo> tablaAux = tablaDispersionAbierta[i];
			for(Simbolo s: tablaAux){
				if(s.getNivel() == nivel){
					s.setVisible(false);
				}
			}
		}
	}

	/**
	 * Elimina de la tabla todas los parámetros que hayan sido ocultados
	 * previamente. LOS PROCEDIMIENTOS Y FUNCIONES DONDE ESTABAN DECLARADOS
	 * DEBEN SER ELIMINAODS TAMBIEN PARA MANTENER LA COHERENCIA DE LA TABLA.
	 * 
	 * @param nivel
	 */
	public void eliminarParametrosOcultos(int nivel) {
		
	}

	/**
	 * Elimina de la tabla todas los procedimientos de un nivel. LOS PARAMETROS
	 * DE ESTAS ACCIONES DEBEN SER ELIMINADOS TAMBIEN PARA MANTENER LA
	 * COHERENCIA DE LA TABLA.
	 * 
	 * @param nivel
	 */
	public void eliminarAcciones(int nivel) {
		
	}
}
