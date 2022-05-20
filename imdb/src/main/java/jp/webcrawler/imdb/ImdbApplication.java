package jp.webcrawler.imdb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import jp.webcrawler.services.director;

public class ImdbApplication {

	 //INICIANDO APLICAÇÃO CHAMANDO SITE QUE SERÁ EXTRAIDO OS DADOS
	public static void main(String[] args) {
		
		
		Document connSite = null;
		director run = new director();
		
		try {
		System.out.println("Conectando ao site!");	
		connSite = Jsoup.connect("https://www.imdb.com/chart/bottom").get(); //CONECTA AO SITE
		run.listLinks(connSite); //ENVIA CONTEÚDO DO GET PARA FUNÇÃO 'listLinks' EM 'services->director'
		} catch (Exception e) {
		System.out.println("Erro na solicitação!");
		}

		
	}
	
	
	
	

}
