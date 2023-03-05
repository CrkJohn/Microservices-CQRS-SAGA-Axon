package crk.study.products.domain.queries;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class GetProductByTitleQuery {

    private String title;
}
