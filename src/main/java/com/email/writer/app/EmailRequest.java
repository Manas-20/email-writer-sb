package com.email.writer.app;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class EmailRequest {

       private String emailContent;
    private String tone;
}
