package br.com.blendtecnologia.iam.core.domain.usecases;

public interface UseCase<I extends UseCase.InputValues, O extends UseCase.OutputValues> {
    
    O execute(I input);
    
    interface InputValues { }
    
    interface OutputValues { }
    
}
