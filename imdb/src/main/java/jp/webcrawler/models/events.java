package jp.webcrawler.models;

import java.util.List;


public class events {

	public String infoTitle;
	public String infoNoteTable;
	public String infoNote;
	public List<String> infoDirector;
	public List<String> infoActors;
	public String infoComment;
	
	public events(String infoTitle, String infoNote, String infoNoteTable, List<String> infoDirector, List<String> infoActors, String infoComment) {
	
		this.infoTitle = infoTitle;
		this.infoNote = infoNote;
		this.infoNoteTable = infoNoteTable;
		this.infoDirector = infoDirector;
		this.infoActors = infoActors;
		this.infoComment = infoComment;

	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}

	public String getInfoNote() {
		return infoNote;
	}


	public void setInfoNote(String infoNote) {
		this.infoNote = infoNote;
	}
	
	public String getInfoNoteTable() {
		return infoNoteTable;
	}
	
	public void setInfoNoteTable(String infoNoteTable) {
		this.infoNoteTable = infoNoteTable;
	}

	public List<String> getInfoDirector() {
		return infoDirector;
	}

	public void setInfoDirector(List<String> infoDirector) {
		this.infoDirector = infoDirector;
	}

	public List<String> getInfoActors() {
		return infoActors;
	}

	public void setInfoActors(List<String> infoActors) {
		this.infoActors = infoActors;
	}

	public String getInfoComment() {
		return infoComment;
	}

	public void setInfoComment(String infoComment) {
		this.infoComment = infoComment;
	}
	
	

	

}
