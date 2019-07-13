package service;

import enums.TypeFilter;

public interface GameService {
	
	int getResponse();
	int addQuestion(String question, TypeFilter filter);
}

