package Chinmay.journalApp.Service;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import Chinmay.journalApp.entity.User;

import java.util.stream.Stream;

public class UserArgumentProvider implements ArgumentsProvider{

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception{
        return Stream.of(
                Arguments.of(User.builder().userName("Ram").password("########").build()),
                Arguments.of(User.builder().userName("Chinmay").password("#########").build())
        );
    }
}
