package edu.fiuba.algo3.loaders;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import edu.fiuba.algo3.constants.QuestionType;
import edu.fiuba.algo3.engine.questions.GroupChoiceQuestion;
import edu.fiuba.algo3.exceptions.QuestionsNotLoadedException;
import edu.fiuba.algo3.model.GameOption;
import edu.fiuba.algo3.model.OptionGroup;
import edu.fiuba.algo3.model.Question;
import edu.fiuba.algo3.resources.ResourceLoader;

public class QuestionLoader {
	
	private static final String TYPE = "type";
	private static final String CORRECT_OPTIONS = "correctOptions";
	private static final Gson gson = new Gson();
	
	private QuestionLoader() {}
	
	@SuppressWarnings("unchecked")
	public static List<Question> loadQuestions(String questionsPath) throws QuestionsNotLoadedException{
		String questionJson;
		List<Question> questions = new ArrayList<>();
		try {
			questionJson = ResourceLoader.loadTextFile(questionsPath);
			questions = parseToList(new Gson().fromJson(questionJson, List.class));
		} catch (Exception ex) {			
			throw new QuestionsNotLoadedException(ex.getMessage());
		}
		if(questions.isEmpty()) {
			throw new QuestionsNotLoadedException("Questions couldn't be loaded");
		}
		return questions;
	}
	
	@SuppressWarnings("unchecked")
	private static List<Question> parseToList(List<Map<String, Object>> list){
		List<Question> questionList = new ArrayList<>();	
		list.stream().forEach(element -> {
			String type = element.get(TYPE).toString();
			Question question = (Question) gson.fromJson(gson.toJson(element), QuestionType.valueOf(type).getQuestionClass());
			if(question instanceof GroupChoiceQuestion) {
				loadGroupChoiceQuestion(element, (GroupChoiceQuestion) question);
			}
			questionList.add(question);
		});
		return questionList;
	}	
	
	private static void loadGroupChoiceQuestion(Map<String, Object> element, GroupChoiceQuestion question) {
		List correctOptionsList = (List) element.get(CORRECT_OPTIONS);
		List<GameOption> groupList = new ArrayList<>();
		for(Object correctOption : correctOptionsList) {
			groupList.add(gson.fromJson(gson.toJson(correctOption), OptionGroup.class));
		}
		question.setCorrectOptions(groupList);
	}

}
