package com.showhive.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public final class RequestWrapper extends HttpServletRequestWrapper {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private byte[] cachedBody;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        
        InputStream inputStream = request.getInputStream();
        this.cachedBody = inputStream.readAllBytes();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        // JSON Body XSS 필터링
        if (cachedBody.length > 0 && isJsonRequest()) {
            String body = new String(cachedBody, StandardCharsets.UTF_8);
            String cleanedBody = cleanJsonXSS(body);
            cachedBody = cleanedBody.getBytes(StandardCharsets.UTF_8);
        }

        return new CachedBodyServletInputStream(cachedBody);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream(), StandardCharsets.UTF_8));
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }

        String[] encodedValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        return value == null ? null : cleanXSS(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return value == null ? null : cleanXSS(value);
    }

    private boolean isJsonRequest() {
        String contentType = super.getContentType();
        return contentType != null && contentType.contains("application/json");
    }

    private String cleanJsonXSS(String json) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = objectMapper.readValue(json, Map.class);
            Map<String, Object> cleanedMap = cleanMap(map);
            return objectMapper.writeValueAsString(cleanedMap);
        } catch (Exception e) {
            return json; // JSON 파싱 실패 시 원본 반환
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> cleanMap(Map<String, Object> map) {
        Map<String, Object> cleaned = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {
                cleaned.put(entry.getKey(), cleanXSS((String) value));
            } else if (value instanceof Map) {
                cleaned.put(entry.getKey(), cleanMap((Map<String, Object>) value));
            } else {
                cleaned.put(entry.getKey(), value);
            }
        }
        return cleaned;
    }

    /**
     * Jsoup 라이브러리를 사용한 XSS 방어
     */
    private String cleanXSS(String value) {
        if (value == null) {
            return null;
        }

        return Jsoup.clean(value, Safelist.none());
    }

    /**
     * ServletInputStream 구현 (Request Body 재사용을 위해)
     */
    private static class CachedBodyServletInputStream extends ServletInputStream {
        private final ByteArrayInputStream buffer;

        public CachedBodyServletInputStream(byte[] contents) {
            this.buffer = new ByteArrayInputStream(contents);
        }

        @Override
        public int read() {
            return buffer.read();
        }

        @Override
        public boolean isFinished() {
            return buffer.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            throw new UnsupportedOperationException();
        }
    }
}
