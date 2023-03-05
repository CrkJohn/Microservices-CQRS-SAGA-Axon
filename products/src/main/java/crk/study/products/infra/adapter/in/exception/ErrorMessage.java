package crk.study.products.infra.adapter.in.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ErrorMessage {

    private final LocalDateTime date;
    private final String message;

}
