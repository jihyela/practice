package controller;

import java.util.HashMap;

/**
 * 개별 컨트롤러가 실행 후 
 * 결과 정보를 저장하는 객체 
 * 1.이동 경로(viewName) 와 이동방식(forward or redirect)
 * 2.request에 저장하여 view에 공유할 정보 
 * 
 * viewName의 시작이 redirect: 로 구성되면 forward가 아니라 
 * redirect방식으로 이동된다. 
 */
public class ModelAndView {
	private String viewName;
	private HashMap<String,Object> map=new HashMap<String,Object>();
	public ModelAndView() {
		super();		
	}
	public ModelAndView(String viewName) {
		super();
		this.viewName = viewName;
	}
	public ModelAndView(String viewName, HashMap<String, Object> map) {
		super();
		this.viewName = viewName;
		this.map = map;
	}
	public ModelAndView(String viewName,
			String attributeName,Object attributeValue){
		this.viewName=viewName;
		this.map.put(attributeName, attributeValue);
	}
	public void addObject(String attributeName,Object attributeValue){
		this.map.put(attributeName, attributeValue);
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	public HashMap<String, Object> getMap() {
		return map;
	}
	public void setMap(HashMap<String, Object> map) {
		this.map = map;
	}
	
	@Override
	public String toString() {
		return "ModelAndView [viewName=" + viewName + ", map=" + map + "]";
	}
	
}









