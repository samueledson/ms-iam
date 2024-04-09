package br.com.blendtecnologia.iam.core.domain.usecases;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class UseCaseExecutorImpl implements UseCaseExecutor {
    @Override
    public <R, I extends UseCase.InputValues, O extends UseCase.OutputValues> CompletableFuture<R> execute(
            UseCase<I, O> useCase, I input, Function<O, R> outputMapper) {
        return CompletableFuture
                .supplyAsync(() -> input)
                .thenApplyAsync(useCase::execute)
                .thenApplyAsync(outputMapper);
    }
}
