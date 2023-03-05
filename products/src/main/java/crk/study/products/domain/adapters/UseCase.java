package crk.study.products.domain.adapters;

public interface UseCase<T,R>{

    R execute(T command);
}
