package br.edu.ifsul.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.commons.io.IOUtils;

public class UploadService {

	public static String upload(String fileName, String base64){
		try {
			// decode: decodifica o Base64 para array de bytes byte[] decodeFile =
			byte[] decodedImg = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
			
			InputStream in = new ByteArrayInputStream(decodedImg);
			
			//Pasta tempor·ria da JVM
			File imagesDir = new File("/home/vagner/images", "carros");
			if(!imagesDir.exists()) {
				//cria a pasta carros se n√£o existe
				imagesDir.mkdir();
			}
			
			//cria o arquivo
			File file = new File(imagesDir, fileName);
			
			//abre o OutputStream para escrever no arquivo
			FileOutputStream out = new FileOutputStream(file);
			
			//Escreve os dados no arquivo
			IOUtils.copy(in, out);
			in.close();
			out.close();
			
			//retorna o caminho do arquivo
			return file.getAbsolutePath();
		
		}catch(IOException e) {
			e.printStackTrace();
			return "Erro ao fazer o upload do arquivo.";
		}
	}
}
