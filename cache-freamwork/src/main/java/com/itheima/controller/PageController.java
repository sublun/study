package com.itheima.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Controller
public class PageController {

	private static Cache<String, Long> lastModifiedCache = 
			CacheBuilder.newBuilder()
			.expireAfterWrite(10, TimeUnit.SECONDS)
			.build();
	
	private long getLastModified() throws ExecutionException {
		return lastModifiedCache.get("lastModified", new Callable<Long>() {

			@Override
			public Long call() throws Exception {
				
				return System.currentTimeMillis();
			}
		});
	}
	
	@RequestMapping("/index.html")
	public ResponseEntity<String> showPage(@RequestHeader(value="If-Modified-Since", required=false) 
		Date ifModifiedSince) throws Exception {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyy HH:mm:ss 'GMT'", Locale.US);
		//文档的最后修改时间，可以是商品数据最后的更新时间，在此只是做测试每10秒生成一个新的时间
		long lastModifiedMillis = getLastModified() / 1000 * 1000;
		long now = System.currentTimeMillis() / 1000 * 1000;
		//文档在浏览器中的缓存时间，单位是秒
		long maxAge = 20;
		
		if ( ifModifiedSince != null && ifModifiedSince.getTime() == lastModifiedMillis) {
			MultiValueMap<String, String> headers = new HttpHeaders();
			headers.add("Date", dateFormat.format(new Date(now)));
			headers.add("Expires", dateFormat.format(new Date(now + maxAge * 1000)));
			headers.add("Cache-Control", "max-age=" + maxAge);
			return new ResponseEntity<>(headers, HttpStatus.NOT_MODIFIED);
		}
		String body = "<html>"
					+ "<head>"
					+ "<script type=\"text/javascript\" src=\"/js/jquery-1.6.4.js\" charset=\"utf-8\"></script>"
					+ "</head>"
					+ "<body>"
					+ "<a href=\"#\">hello</a><p>time:"+System.currentTimeMillis() + "</p>"
					+"</body>"
					+ "</html>";
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Date", dateFormat.format(new Date(now)));
		headers.add("Last-Modified", dateFormat.format(new Date(lastModifiedMillis)));
		headers.add("Expires", dateFormat.format(new Date(now + maxAge * 1000)));
		headers.add("Cache-Control", "max-age=" + maxAge);
		
		return new ResponseEntity<String>(body, headers, HttpStatus.OK);
	}
}
