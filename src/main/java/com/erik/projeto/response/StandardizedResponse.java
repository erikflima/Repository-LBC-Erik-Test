package com.erik.projeto.response;
import java.util.ArrayList;
import java.util.List;



//-----Class that serves to be the answer of my services of rest. Serve as a default answer.-----//
public class StandardizedResponse<ClasseRecebida> {

	
	private ClasseRecebida conteudoDoResponse;
	private List<String>   errors;

	
	public StandardizedResponse(){
	}

	
	//-------------------------Getters and Setters----------------------//	

	public ClasseRecebida getConteudoDoResponse() {
		
		return conteudoDoResponse;
	}

	public void setConteudoDoResponse(ClasseRecebida conteudoDoResponse) {
		
		this.conteudoDoResponse = conteudoDoResponse;
	}
	
	
	
	
	
	public List<String> getErrors() {
		
		if (this.errors == null) {
			
			//Nao retorna "null", e sim um objeto vazio.
			this.errors = new ArrayList<String>();
		}
		
		return errors;
	}

	public void setErrors( List<String> errors ) {
		
		this.errors = errors;
	}

}