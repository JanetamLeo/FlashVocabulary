package com.flashvocabulary.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.flashvocabulary.dto.WordSentenceView;
import com.flashvocabulary.service.SearchService;
import com.flashvocabulary.service.SentenceService;
import com.flashvocabulary.service.SearchService.searchResult;
import com.flashvocabulary.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class SearchAction implements Action{
    private SearchService searchservice = new SearchService();
    private SentenceService sentenceService = new SentenceService();
    
    @Override
    public String execute() throws Exception {
	// TODO Auto-generated method stub
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	String word = request.getParameter("word");
	searchResult result = null;
	List<WordSentenceView> wsvList = new ArrayList<WordSentenceView>();
	//RequestDispatcher dispatcher = null;
	PrintWriter out = response.getWriter();
	try {
		result = searchservice.findAllWordInfoByWord(word);
		wsvList = sentenceService.getSentencesByWord(word);
//		request.setAttribute("message", "OK!");
//		dispatcher = request.getRequestDispatcher("/message.jsp");
//		dispatcher.forward(request, response);
		request.setAttribute("result", result);
		request.setAttribute("wsvList", wsvList);
		return SUCCESS;
		
	} catch (Exception e) {
		request.setAttribute("message", "获取详细信息失败");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		return IConstants.FAILURE;
	}
    }
    
}
