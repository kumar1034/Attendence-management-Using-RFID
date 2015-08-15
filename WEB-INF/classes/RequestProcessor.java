package com;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.tiles.TilesRequestProcessor;

public class RequestProcessor extends TilesRequestProcessor {

    @Override
    protected void processContent(HttpServletRequest req, HttpServletResponse res) {
        try {
            req.setCharacterEncoding("UTF-8");

            try {
                res.setCharacterEncoding("UTF-8");
            } catch (Exception ex) {
            }
        } catch (UnsupportedEncodingException ex) {
        }

        super.processContent(req, res);
    }
}