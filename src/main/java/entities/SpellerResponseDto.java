package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SpellerResponseDto {
    private int code;
    private int pos;
    private int row;
    private int col;
    private int len;
    private String word;
    private String[] s;

}
