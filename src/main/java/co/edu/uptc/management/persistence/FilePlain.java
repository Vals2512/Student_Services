package co.edu.uptc.management.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import co.edu.uptc.management.constants.CommonConstants;





public class FilePlain {
	
	public String readFile(String rutaNombre) {
		//Obtener el contenido
		StringBuilder contenido = new StringBuilder();
        try {
        	InputStream inputStream = getClass().getResourceAsStream(rutaNombre);
        	if(inputStream == null) {
        		System.out.println("No se encontró el archivo");
        		return "";
        	}
        	InputStreamReader input = new InputStreamReader(inputStream);
        	BufferedReader br = new BufferedReader(input);
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append(CommonConstants.NEXT_LINE);
            }
            br.close();
        } catch (IOException e) {
        	e.printStackTrace();
        	System.out.println("Se presentó un error al leer el archivo específicado");
        }
        return contenido.toString();
	}
	
	public void writeFile(String rutaNombreArchivo, String content) {
		String rutaAbsoluta=
				"C:/Users/valen/Documents/Eclipse/Student_Web_Project/src/main/resources/data/" + rutaNombreArchivo;	
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaAbsoluta))) {
			writer.write(content);
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}
	
	protected List<String> reader(String rutaNombre){
		List<String> output = new ArrayList<>();
		StringTokenizer tokens = new StringTokenizer(this.readFile(rutaNombre), CommonConstants.NEXT_LINE);
		while (tokens.hasMoreElements()) {
			output.add(tokens.nextToken());	
		}
		return output;
	}
	
	//Guarda el contenido
	protected void writer(String rutaNombre, List<String> file){
		StringBuilder strContent = new StringBuilder();
		for(String record: file) {
			strContent.append(record).append(CommonConstants.NEXT_LINE);
		}
		writeFile(rutaNombre, strContent.toString());
	}

	

}