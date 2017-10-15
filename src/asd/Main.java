package asd;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main
{

	public static void main(String[] args) throws IOException
	{
		File dir = new File(".//IN//");
		
		for(File arch : dir.listFiles())
		{
			PrintWriter out = new PrintWriter(new FileWriter(".//OUT//" + arch.getName().replace(".in", ".out")));
			
			ArmadorDeEquipos asd = new ArmadorDeEquipos(arch);
			out.println(asd.resolver());
			out.close();
		}
	}

}
