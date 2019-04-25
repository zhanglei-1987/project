package cn.quickly.project.utility.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import cn.quickly.project.utility.io.Streams;
import cn.quickly.project.utility.lang.Assert;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Iterables;
import cn.quickly.project.utility.lang.Strings;

public final class Servlets {

	private Servlets() {
		throw new UnsupportedOperationException();
	}

	public static Map<String, Object> getParameterMap(HttpServletRequest request, String... excludes) {

		Map<String, Object> map = new HashMap<String, Object>();

		for (Enumeration<?> enume = request.getParameterNames(); enume.hasMoreElements();) {

			String name = String.valueOf(enume.nextElement());

			Object values = request.getParameterValues(name);

			if (values != null) {

				if (Array.getLength(values) == 1) {

					values = Array.get(values, 0);

				}

				map.put(name, values);

			}

		}

		Iterables.forEach(excludes, (i, name) -> {

			map.remove(name);

		});

		return map;
	}

	public static Map<String, Object> getCookieMap(HttpServletRequest request, String... excludes) {

		Map<String, Object> map = new HashMap<String, Object>();

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {

			for (Cookie cookie : cookies) {

				map.put(cookie.getName(), cookie.getValue());

			}

		}

		Iterables.forEach(excludes, (i, name) -> {

			map.remove(name);

		});

		return map;
	}

	public static void setAttributes(HttpServletRequest request, Map<String, Object> map) {

		if (map != null) {

			for (String name : map.keySet()) {

				request.setAttribute(name, map.get(name));

			}

		}

	}

	public static void setAttributes(HttpSession session, Map<String, Object> map) {

		if (map != null) {

			for (String name : map.keySet()) {

				session.setAttribute(name, map.get(name));

			}

		}

	}

	public static void setAttributes(PageContext page, Map<String, Object> map) {

		if (map != null) {

			for (String name : map.keySet()) {

				page.setAttribute(name, map.get(name));

			}

		}

	}

	public static String getBody(HttpServletRequest request) {
		try {
			return new String(getContent(request), request.getCharacterEncoding());
		} catch (IOException e) {
			throw Exceptions.argument(e);
		}
	}

	public static byte[] getContent(HttpServletRequest request) {

		int length = request.getContentLength();

		Assert.isTrue(length < 0, "there are have no data in content.");

		if (length > 0) {

			try {
				return Streams.getBytes(request.getInputStream(), length);
			} catch (IOException e) {
				throw Exceptions.argument(e);
			}

		}

		return new byte[0];
	}

	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {

		long expires = System.currentTimeMillis() + expiresSeconds * 1000L;

		response.setDateHeader("Expires", expires);

		String maxAge = Strings.concat("private, max-age=", expiresSeconds);

		response.setHeader("Cache-Control", maxAge);

	}

	public static void setNoCacheHeader(HttpServletResponse response) {

		response.setDateHeader("Expires", 1L);

		response.addHeader("Pragma", "no-cache");

		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");

	}

	public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	public static void setETag(HttpServletResponse response, String etag) {
		response.setHeader("ETag", etag);
	}

	public static boolean isContainIfModifiedSince(HttpServletRequest request, HttpServletResponse response, long lastModified) {

		long ifModifiedSince = request.getDateHeader("If-Modified-Since");

		if (ifModifiedSince != -1L && lastModified < ifModifiedSince + 1000L) {

			response.setStatus(304);
			return false;

		} else {

			return true;

		}

	}

	public static boolean isContainIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {

		String headerValue = request.getHeader("If-None-Match");

		if (headerValue != null) {

			boolean conditionSatisfied = false;

			if (!"*".equals(headerValue)) {

				StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

				do {
					if (conditionSatisfied || !commaTokenizer.hasMoreTokens())
						break;
					String currentToken = commaTokenizer.nextToken();

					if (currentToken.trim().equals(etag))
						conditionSatisfied = true;

				} while (true);

			} else {
				conditionSatisfied = true;
			}

			if (conditionSatisfied) {
				response.setStatus(304);
				response.setHeader("ETag", etag);
				return false;
			}

		}

		return true;
	}

	public static void setAttachmentResponseHeader(HttpServletRequest request, HttpServletResponse response, String fileName, String encoding,
			Integer fileLength) {

		fileName = fileName.replaceAll("\"", "\\\\\"");
		try {
			fileName = URLEncoder.encode(fileName, encoding);
			if (request.getHeader("User-Agent").indexOf("MSIE") != -1) {
				fileName = fileName.replace("+", " ").replace("%2E", ".");
			} else {
				fileName = Strings.concat("=?", encoding, "?Q?", fileName.replace('%', '='), "?=");
			}
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}

		ServletContext sc = request.getSession().getServletContext();

		String mimetype = sc.getMimeType(fileName);
		response.setCharacterEncoding(encoding);
		if (mimetype != null) {
			response.setContentType(mimetype);
		} else {
			response.setContentType("application/octet-stream");
		}
		if (fileLength != null) {
			response.setContentLength(fileLength);
		}

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "public");
		response.setHeader("Expires", "0");
	}

	public static void redirect(String url, HttpServletResponse response) {
		response.setHeader("Location", url);
		response.setStatus(302);
	}

	public static String getClientIp(HttpServletRequest request, String... headerNames) {

		List<String> names = new ArrayList<String>(headerNames.length + 4);
		names.add("X-Forwarded-For");
		names.add("X-Real-IP");
		names.add("Proxy-Client-IP");
		names.add("WL-Proxy-Client-IP");

		Collections.addAll(names, headerNames);

		for (String headerName : names) {

			String headerValue = request.getHeader(headerName);

			if (!Strings.isEmpty(headerValue) && !"unknown".equals(headerValue)) {
				return headerValue.split(",")[0];
			}

		}

		return request.getRemoteAddr();

	}

}
