package minilengcompiler;

import java.util.LinkedList;
import java.util.Random;

public class TablaDispersionAbierta {
	private int[] tablaPearson;
	private LinkedList<Simbolo>[] tablaAleatoria;
	private int tamano;
	
	public TablaDispersionAbierta(int tamano){
		this.tamano = tamano;
		tablaAleatoria = new LinkedList[tamano];
		tablaPearson = new int[256];
		rellenaYBaraja();
	}
	
	private void rellenaYBaraja() {
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
		return h%tamano;
	}
	
}
