
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter; 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths; 

class TreeDiameter { 
	
// 10000 max num de nodos de la grafica
static int diameter[] = new int[10001]; 

 
static int bfs(int init,Vector<Vector<Integer>>arr, int n){ 
	// cola inicial
	Queue<Integer> q = new LinkedList<>(); 
	q.add(init); 

	int visited[] = new int[n + 1]; 
	for (int i = 0; i <= n; i++) { 
		visited[i] = 0; 
		diameter[i] = 0; 
	} 

 
	q.add(init); 

	// Mark the traversed node visited 
	visited[init] = 1; 
	while (q.size() > 0){ 
		int u = q.peek(); 
		q.remove(); 
		for (int i = 0; 
				i < arr.get(u).size(); i++) 
		{ 
			if (visited[arr.get(u).get(i)] == 0) 
			{ 
				visited[arr.get(u).get(i)] = 1; 

				// tomamos cada arista con peso 1 para el diametro 
				diameter[arr.get(u).get(i)] += diameter[u] + 1; 
				q.add(arr.get(u).get(i)); 
			} 
		} 
	} 
	// buscamos el de mayor diametro
	int in = 0; 
	for(int i = 0; i <= n; i++) { 
		if(diameter[i] > diameter[in]) 
		in = i; 
	} 
	
	// regresamos el indice con el valor mas grande en diameto
	return in; 
} 

static int diametro(Vector<Vector<Integer>> arr, int n){ 
	int init = bfs(1, arr, n); 
	int val = bfs(init, arr, n); 
	System.out.println("nodo inicial: "+ init+ "  Nodo final: "+ val);
	return diameter[val]; 
} 

// Driver Code 
public static void main(String args[]){
	// entrada desde archivo
	String f = "";
		if(args.length >= 1) {
			f = args[0];
		} else {
			System.out.println("numero de argumentos incorrecto");
		}

		Vector<Vector<Integer>> arr = new Vector<Vector<Integer>>(); 

		int n =0;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(f))) {
			String line = br.readLine(); 
			n = Integer.parseInt(line);

			//System.out.println(dato[0] +" "+ dato[1]); numero de vertices y numero de aristas
			


			for(int i = 0; i < n + 1; i++){ 
				arr.add(new Vector<Integer>()); 
			} 

			int u,v;
		
			while ((line = br.readLine()) != null) {
				String[] vertices = line.split(" ");

				// System.out.println("Grafica:");
				// System.out.println(vertices[0] +" "+ vertices[1]);
		

				u = Integer.parseInt(vertices[0]);
				v = Integer.parseInt(vertices[1]);

				arr.get(u).add(v);
				arr.get(v).add(u);

			}


		} catch (IOException e) {
		    System.err.format("IOException: %s%n", e);
		}


	System.out.printf("Diametro del arbol:  %d\n", diametro(arr, n)); 
} 
} 

