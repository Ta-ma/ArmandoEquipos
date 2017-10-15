package asd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ArmadorDeEquipos
{
	String[] cadenas;
	
	public ArmadorDeEquipos ()
	{
	}
	
	public ArmadorDeEquipos (File archivo) throws FileNotFoundException
	{
		Scanner sc = new Scanner(archivo);
		
		sc.nextInt(); // me salteo el primer num de mierda
		cadenas = new String[sc.nextInt()];
		sc.nextLine();
		
		for (int i = 0; i < cadenas.length; i++)
			cadenas[i] = sc.nextLine();
		
		sc.close();
	}
	
	public String resolver ()
	{
		String cad = new String();
		int maxLongitud = 0;
		String maxCadena = new String();
		
		for(int i = 0; i < cadenas.length; i++)
		{
			for (int j = i + 1; j < cadenas.length; j++)
			{
				cad = maxCadenaRecurrente(cadenas[i], cadenas[j]);
				
				if (cad.length() > maxLongitud)
				{
					maxLongitud = cad.length();
					maxCadena = cad;
				}
			}
		}
		
		return maxCadena;
	}

	public String maxCadenaRecurrente (String cad1, String cad2)
	{
		// Armo una matriz para programación dinámica
		int[][] mat = new int[cad1.length() + 1][cad2.length() + 1];
		int max = 0, max_i = 0, max_j = 0; // necesito esto para recuperar la subcadena más larga
		
		// Se empieza desde la fila 1 y columna 1, necesito la fila 0 y columna 0
		// para que no se vaya a la mierda.
		
		for (int i = 1; i < cad1.length() + 1; i++)
		{
			for (int j = 1; j < cad2.length() + 1; j++)
			{
				if (cad1.charAt(i - 1) == cad2.charAt(j - 1))
				{
					mat[i][j] = mat[i - 1][j - 1] + 1;
					if (mat[i][j] > max)
					{
						max = mat[i][j];
						max_i = i;
						max_j = j;
					}
				}
				else
					mat[i][j] = 0;
			}
		}
		
		// recupero la cadena
		String resultado = "";
		while (max > 0)
		{
			resultado += cad1.charAt(max_i - 1);
			max_i--;
			max--;
		}
		
		return new StringBuffer(resultado).reverse().toString();
	}
}
