package com.email.writer.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class EmailGeneratorController {
    private final EmailGeneratorService emailGeneratorService;

    // Constructor injection
    public EmailGeneratorController(EmailGeneratorService emailGeneratorService) {
        this.emailGeneratorService = emailGeneratorService;
    }

    @PostMapping("/generate")
    public ResponseEntity<Object> generateEmail(@RequestBody EmailRequest emailRequest) {
        try {
            // Validate request
            if (emailRequest == null || emailRequest.getEmailContent() == null ||
                    emailRequest.getEmailContent().trim().isEmpty()) {
                return ResponseEntity
                        .badRequest()
                        .body(new ErrorResponse("Email content cannot be empty"));
            }

            // Generate the email
            String response = emailGeneratorService.generateEmailReply(emailRequest);

            // Return successful response
            return ResponseEntity.ok(new EmailResponse(response));
        } catch (ResponseStatusException e) {
            // This handles exceptions thrown from the service with proper status codes
            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(new ErrorResponse(e.getReason()));
        } catch (Exception e) {
            // Handle any unexpected errors
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("An unexpected error occurred: " + e.getMessage()));
        }
    }

    // Health check endpoint
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Service is up and running");
    }

    // Pre-flight options handler (explicit handler for CORS pre-flight requests)
    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }

    // Response classes
    static class EmailResponse {
        private final String generatedEmail;

        public EmailResponse(String generatedEmail) {
            this.generatedEmail = generatedEmail;
        }

        public String getGeneratedEmail() {
            return generatedEmail;
        }
    }

    static class ErrorResponse {
        private final String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}