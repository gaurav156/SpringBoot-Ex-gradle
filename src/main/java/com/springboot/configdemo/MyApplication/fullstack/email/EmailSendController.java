package com.springboot.configdemo.MyApplication.fullstack.email;

import com.springboot.configdemo.MyApplication.fullstack.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailSendController {
    @Autowired
    private EmailSendService emailSendService;

    @Operation(summary = "Send OTP", description = "Sends OTP to an Email", tags = {"Get", "OTP"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OTP sent",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmailSendService.class))}),
            @ApiResponse(responseCode = "404", description = "Email Not Present",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.GET, value = "/otp/send/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void sendOtp(@PathVariable("email") String email){
        emailSendService.sendOtp(email);
    }

    @Operation(summary = "Verify OTP", description = "Verify OTP sent to an Email", tags = {"Post", "OTP"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OTP verified",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmailSendService.class))}),
            @ApiResponse(responseCode = "404", description = "Wrong OTP",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.POST, value = "/otp/verify", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String verifyOtp(@RequestBody Otp givenOtp){
        return emailSendService.verifyOtp(givenOtp);
    }
}
