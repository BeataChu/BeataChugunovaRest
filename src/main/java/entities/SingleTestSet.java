package entities;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SingleTestSet {
    private RequestDto requestDto;
    private ResponseDto[] responseDto;
}
