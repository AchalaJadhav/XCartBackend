package com.innovation.xcartbackend.service;

import java.util.ArrayList;
import java.util.List;

public class QuestionService 
{
	public List<String> getQuestions() 
	{
		List<String> questions = new ArrayList<>();
		questions.add("Which is your mobile company?");
		questions.add("What is your childhood nickname?");
		questions.add("What is the name of your first pet?");
		questions.add("which was your first vehicle?");
		questions.add("which was your first mobile phone?");
		return questions;
	}

}
