package com.alltheducks.bbws.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wiley on 3/06/2016.
 */
public class ErrorsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Exception ex = (Exception) req.getAttribute("javax.servlet.error.exception");
        String exType = (String) req.getAttribute("javax.servlet.error.exception_type");
        String message = (String) req.getAttribute("javax.servlet.error.message");
        String reqUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");

        resp.setStatus(statusCode);

        Error err = new Error();
        err.code = statusCode;
        err.message = message;

        ObjectMapper mapper = new ObjectMapper();

        String jsonVal = mapper.writeValueAsString(err);


        resp.setContentType(MediaType.APPLICATION_JSON_VALUE + ";" +
                javax.ws.rs.core.MediaType.CHARSET_PARAMETER + "=UTF-8");
        resp.getWriter().write(jsonVal);
    }

    public class Error {
        int code;
        String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
