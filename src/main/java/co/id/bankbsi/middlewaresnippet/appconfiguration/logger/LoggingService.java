package co.id.bankbsi.middlewaresnippet.appconfiguration.logger;

import co.id.bankbsi.middlewaresnippet.common.util.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoggingService {

    ObjectMapper objectMapper = new ObjectMapper();

    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> parameters = buildParametersMap(httpServletRequest);

        stringBuilder.append("REQUEST ");
        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
        if (!httpServletRequest.getMethod().equals("GET")) {
            stringBuilder.append("headers=[").append(buildHeadersMap(httpServletRequest)).append("]");
        }

        if (!parameters.isEmpty()) {
            stringBuilder.append("parameters=[").append(parameters).append("] ");
        }

        if (body != null) {

            String bodyAsJson = convertBodyToJsonIfPosible(body);

            stringBuilder.append("body=[" + bodyAsJson + "]");
        }

        log.info(stringBuilder.toString());
    }

    private String convertBodyToJsonIfPosible(Object body) {
        String resultBody;
        try {
            resultBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
        } catch (JsonProcessingException e) {
            resultBody = (String) body;
        }
        return resultBody;
    }

    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Object body) {
        StringBuilder stringBuilder = new StringBuilder();
        String bodyResponse;

        bodyResponse = filterResponse(httpServletRequest, body);

        stringBuilder.append("RESPONSE ");
        stringBuilder.append("responseBody=[").append(bodyResponse).append("] ");

        log.info(stringBuilder.toString());
    }

    private String filterResponse(HttpServletRequest httpServletRequest, Object body) {
        String bodyResponse;
        // filter GET request method

        try {
            bodyResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
        } catch (JsonProcessingException e) {
            bodyResponse = body.toString();
        }

        if (httpServletRequest.getMethod().equals("GET")) {
            bodyResponse = "";
        }

        return bodyResponse;
    }

    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }

        return resultMap;
    }

    private Map<String, String> buildHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();

        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }

        return map;
    }
}
