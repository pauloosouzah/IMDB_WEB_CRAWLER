package jp.webcrawler.services;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jp.webcrawler.models.events;



public class director {
	
    //RECEBE DADOS DO SITE NA VAR 'acess'
	public void listLinks(Document acess) {
		List<String> linkMovie = new ArrayList<>();	
		
		//MÉTODO PARA PEGAR LINK DE CADA FILME		
		
	        Element divTable = acess.getElementsByClass("chart full-width").first();
	        List<Element> divMovies = divTable.getElementsByTag("tr");
	        System.out.println("Capturando Links!");
	        for(int i = 10; i >= 1; i--) {
	        Element divTitle= divMovies.get(i).getElementsByClass("titleColumn").first();
	        String divNote = divMovies.get(i).getElementsByClass("ratingColumn imdbRating").text(); //NOTA DA TABELA
	        Element elementTD = divTitle.getElementsByTag("td").get(0);
	        String elementHref = elementTD.getElementsByTag("a").attr("href"); //LINK DOS FILMES
	        linkMovie.add(elementHref + "nota=" +divNote);
	        
	        }
		
	    
		//CHAMA FUNÇÃO QUE ENTRA EM CADA LINK DO FILME 
	    List<events> dados = captureInfo(linkMovie);
	    new saveFile(dados);

    }
		
		

	
	//RECEBE O LINK DOS FILMES
	public List<events> captureInfo(List<String> links) {	
		
		
		List<events> movies = new ArrayList<>();
		Document infoMovie = null;
		String idMovie = null;
		
		System.out.println("Coletando informações dos filmes!");
		//CRIAÇÃO DE FOR PARA ENTRAR EM CADA LINK RECEBIDO NA VAR 'links'
        for(String info: links) {  	
    		String fSplit[] = info.split("nota="); //SEPARA O LINK E A NOTA
    		String link = fSplit[0];
    	    String notaTable = fSplit[1];
    		try {
    		    infoMovie = Jsoup.connect("https://www.imdb.com" + link).get(); //ACESSA SITE DO FILME 
    		    idMovie = info.split("/")[2]; //CAPTURA ID DO FILME EM URL
    		    movies.add(movie(infoMovie, idMovie, notaTable)); //CHAMA FUNÇÃO PARA CAPTURAR INFORMAÇÕES DO FILME
    		} catch (Exception e) {
    			System.out.println("Erro ao acessar link do filme!");
    		}
        }
        
        return movies;
    	      
        
	}
	
	
	public events movie(Document infoMovie, String idMovie, String nota) {
		
		//CARREGA ELEMENTOS PRINCIPAIS DA PÁGINA DO FILME
		
		Element divPrincipal = infoMovie.getElementsByClass("ipc-page-section ipc-page-section--baseAlt ipc-page-section--tp-xs ipc-page-section--bp-xs sc-f1bc5124-1 ZCVpQ").first();	
		Elements divInfoA = divPrincipal.getElementsByClass("sc-94726ce4-0 cMYixt");
		Elements divInfoB = divPrincipal.getElementsByClass("sc-fa02f843-0 fjLeDR");
		
		//ENVIA RESULTADOS PARA MODEL EM 'models->events'
		events run = new events(nameMovie(divInfoA).split("Original title: ")[1], 
								noteMovie(divInfoA),
								nota,
								directionMovie(divInfoB), 
								actorsMovie(divInfoB), 
								commentMovie(idMovie));
		
		
		return run;	

	}
	

	//FUNÇÃO PARA CAPTURAR NOME DO FILME EM INGLÊS
	public String nameMovie(Elements divInfoA) {
		String infoTitle = divInfoA.get(0).getElementsByClass("sc-dae4a1bc-0 gwBsXc").text();	
		return infoTitle;
	}
	
	//FUNÇÃO PARA CAPTURAR NOTA DO FILME
	public String noteMovie(Elements divInfoA) {
		String infoNote = divInfoA.get(0).getElementsByClass("sc-7ab21ed2-1 jGRxWM").text();
		return infoNote;
	}
	
	//FUNÇÃO PARA CAPTURAR DIRETOR(ES) DO FILME
	public List<String> directionMovie(Elements InfoB) {
		List<String> directorInfo = new ArrayList<String>();
		
		Element divDirector = InfoB.get(0).getElementsByClass("ipc-metadata-list__item").get(0);
		List<Element> infoDirector = divDirector.getElementsByClass("ipc-inline-list__item");
		
		
		for(int i= 0; i < infoDirector.size(); i++) {
			directorInfo.add(infoDirector.get(i).text());
		}
		return directorInfo;
	}
	
	//FUNÇÃO PARA CAPTURAR ATOR(ES) DO FILME
	public List<String> actorsMovie(Elements InfoB) {
		
		List<String> actorsInfo = new ArrayList<String>();
		
		Element divActors = InfoB.get(0).getElementsByClass("ipc-metadata-list__item ipc-metadata-list-item--link").first();
		List<Element> infoActors = divActors.getElementsByClass("ipc-inline-list__item");
		
		for(int i= 0; i < infoActors.size(); i++) {
			actorsInfo.add(infoActors.get(i).text());
		}
		
		return actorsInfo;
	}
	
	//FUNÇÃO PARA CAPTURAR MELHOR COMENTÁRIO DO FILME
	public String commentMovie(String idMovie) { //RECEBE O ID DO FILME EXTRAÍDO DA URL
		
		String textComment = null;
		Document infoComment = null;
		String link = "https://www.imdb.com/title/" + idMovie + "/reviews?sort=userRating&dir=desc&ratingFilter=0"; //ACESSA A PÁGINA JÁ FILTRADA PARA MELHORES COMENTÁRIOS
		
		try {
			infoComment = Jsoup.connect(link).get();
			Element divComment = infoComment.getElementsByClass("lister-list").first();
			Element firstComment = divComment.getElementsByClass("lister-item mode-detail imdb-user-review  collapsable").get(0); //CAPTURA PRIMEIRO COMENTÁRIO
			textComment = firstComment.getElementsByClass("text show-more__control").text();	
			
		} catch (Exception e) {
			System.out.println("Erro ao acessar comentário do filme!");
		}
		
		return textComment;
		
		
	}
	
	
	
	
	
}
