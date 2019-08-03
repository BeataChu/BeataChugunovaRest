package entities;

import entities.RequestOptions.Languages;
import entities.RequestOptions.Options;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RequestDto {
    private String[] text;
    private Languages language;
    private Options[] options;

}