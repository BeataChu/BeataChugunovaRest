package entities;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MultipleTestSet {
    private RequestDto requestDto;
    private ResponseDto[][] responseDto;
}