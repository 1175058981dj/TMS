package cn.demo.Util;

import cn.demo.dao.IPrivilageDao;
import cn.demo.entity.SysPrivilage;
import cn.demo.entity.TmsUser;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.List;
public class AuthorizeTag  extends BodyTagSupport {
	   private IPrivilageDao privilageDao;

	    private String URL;  
	  
	    public String getURL() {  
	        return URL;  
	    }  
	      
	    public void setURL(String uRL) {  
	        URL = uRL;  
	    }  
	    @Override  
	    public int doStartTag() { 
	    	  
	        // 如果URL不空就显示URL，否则就不显  
	        if (null != URL) {
				getUserDao();
	        	HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
				TmsUser info=(TmsUser)request.getSession().getAttribute("login");
				//System.out.println(userDao);
	        	List<SysPrivilage> list = privilageDao.getPrivilagelist(info.getUserid());
	        	for (SysPrivilage item : list) {
					if(item.getName().equals(URL)){
						System.out.println(URL);
						System.out.println(item.getName());
						  return EVAL_BODY_INCLUDE;  
					}
				}
	          
	        }  
	        return this.SKIP_BODY;  
	    }
		public void getUserDao() {
			  WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
			privilageDao=(IPrivilageDao)applicationContext.getBean("IPrivilageDao");
		}

}
