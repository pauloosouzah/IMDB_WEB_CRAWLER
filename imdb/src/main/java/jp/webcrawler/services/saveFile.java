package jp.webcrawler.services;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import jp.webcrawler.models.events;

public class saveFile {

	//SALVA AS INFORMAÇÕES EM .TXT
	public saveFile(List<events> dados){
		
		String caminho = "C:\\Users\\pauloosouzah\\eclipse-workspace\\imdb\\IMDB-MOVIES.txt";
	    FileWriter arq;
	    
		try {	
			arq = new FileWriter(caminho);
		    PrintWriter gravarArq = new PrintWriter(arq);
		    System.out.println("Salvando arquivo!");
	        for (int i = 0; i < dados.size(); i++) {
	        	gravarArq.println("\nNome do filme: " + dados.get(i).infoTitle);
	        	gravarArq.println("Nota tabela: " + dados.get(i).infoNoteTable);
	        	gravarArq.println("Nota interna: " + dados.get(i).infoNote);
	        	gravarArq.println("Diretores: " + dados.get(i).infoDirector);
	        	gravarArq.println("Atores princiais: " + dados.get(i).infoActors);
	        	gravarArq.println("Melhor comentário: " + dados.get(i).infoComment);
	        }
	        arq.close();
		} catch (Exception e) {
			System.out.println("Erro ao salvar arquivo!");
		}
		
		System.out.println("Arquivo salvo em: " + caminho);

		
	}
	
		
	}
	
	


