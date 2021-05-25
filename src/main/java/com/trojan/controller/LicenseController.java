package com.trojan.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trojan.util.LicenseUtils;

@RestController
@RequestMapping("/rpc")
public class LicenseController {
 
    @RequestMapping("/obtainTicket")
    public String obtainTicket(String salt, String userName) throws Exception {
        String content = "<ObtainTicketResponse>" +
                "<message></message>" +
                "<prolongationPeriod>607875500</prolongationPeriod>" +
                "<responseCode>OK</responseCode>" +
                "<salt>" + salt + "</salt>" +
                "<ticketId>1</ticketId>" +
                "<ticketProperties>licensee=" + userName + "	licenseType=0	</ticketProperties>" +
                "</ObtainTicketResponse>";
        return LicenseUtils.rsaSign(content);
    }
 
    @RequestMapping("/releaseTicket")
    public void releaseTicket(HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
 
    @RequestMapping("ping")
    public String ping(String salt) throws Exception {
        String content = "<PingResponse>" +
                "<message></message>" +
                "<responseCode>OK</responseCode>" +
                "<salt>" + salt + "</salt>" +
                "</PingResponse>";
        return LicenseUtils.rsaSign(content);
    }
}

