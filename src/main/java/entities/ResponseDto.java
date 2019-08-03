package entities;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Builder
@Getter
@Accessors(chain = true)
public class ResponseDto {
    private int code;
    private int pos;
    private int row;
    private int col;
    private int len;
    private String word;
    private String[] s;
}
