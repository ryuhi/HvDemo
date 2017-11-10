package com.ryuhi.demo.HvDemo.Controller;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.redkale.convert.json.JsonConvert;
import org.redkale.convert.json.JsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class BaseController {
	protected static final JsonConvert convert = JsonFactory.root().getConvert();
	
	protected boolean noCache = false;
	
	@Autowired
	private FreeMarkerConfigurer freemarkerConfig;

	/**
	 * 无边框布局，无需去除不使用的第三方CSS和JS
	 * @param pagePath 模版路径
	 * @param pars 模版参数
	 * @param request request
	 * @param response response
	 * @throws Exception
	 */
	protected void noBorderLayoutViewer(String pagePath, Map<String, Object> pars, HttpServletRequest request, HttpServletResponse response) throws Exception{  
		noBorderLayoutViewer(pagePath, pars, request, response, null,null);
	}
	
	/**
	 * 无边框布局
	 * @param pagePath 模版路径
	 * @param pars 模版参数
	 * @param request request
	 * @param response response
	 * @param excludeCss 从布局页的第三方Css中去除不需要的
	 * @param excludeJs 从布局页的第三方JS中去除不需要的
	 * @throws Exception
	 */
	protected void noBorderLayoutViewer(String pagePath, Map<String, Object> pars, HttpServletRequest request, HttpServletResponse response, String[] excludeCss, String[] excludeJs) throws Exception{  
		if (pars == null) {
			pars = new ConcurrentHashMap<>();
		}
		//定义 body 页路径参数
        pars.put("body_file_path", pagePath);
        //项目路径，解决路径问题
        pars.put("base", request.getContextPath());
        String layoutPath= "layout/noBorderLayout.ftl";//布局页地址固定
        //获得项目项目根路径
    	File f = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath());
		String parentPath = f.getParent();
		File baseFile = new File(parentPath);
		String basePath = baseFile.getParent();
		//模板（默认加载布局页）
        TemplateLoader loader = new FileTemplateLoader(new File(basePath + "/WEB-INF/templates/"));
        freemarkerConfig.getConfiguration().setTemplateLoader(loader);
        freemarkerConfig.getConfiguration().setDefaultEncoding("utf-8");
        freemarkerConfig.getConfiguration().setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template tmp = freemarkerConfig.getConfiguration().getTemplate(layoutPath);
//        //增加返回头，使返回键无效
//  		response.addDateHeader("Last-Modified", ZonedDateTime.of(LocalDateTime.of(2017, 9, 1,0, 0, 0),ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli());
//  		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
//  		response.addHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//  		response.addHeader("cache-control","max-age=0");
//  		response.addHeader("Keep-Alive","timeout=20");
//  		response.addDateHeader("Expires", ZonedDateTime.of(LocalDateTime.of(1990, 1, 1,0, 0, 0),ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli());
//  		response.addHeader("Pragma", "no-cache");
        //合并模板和数据模型  
        Writer out = new OutputStreamWriter(response.getOutputStream(), "UTF-8");//创建一个输出流到Servlet
        tmp.process(pars, out);  
    }
	
	protected String processValidateMsg(BindingResult result) {
		return processValidateMsg(result, false);
	}
	
	protected String processValidateMsg(BindingResult result, boolean getFirst) {
		List<ObjectError> errors = result.getAllErrors();
		String message = null;
		if (getFirst) {
			message = errors.get(0).getDefaultMessage();
		} else {
	    	List<String> msgs = new CopyOnWriteArrayList<>();
	    	for (ObjectError error : errors) {
				String msg = error.getDefaultMessage();
				msgs.add(msg);
			}
	    	message = StringUtils.join(msgs, "\n");
		}
		return message;
	}
}
