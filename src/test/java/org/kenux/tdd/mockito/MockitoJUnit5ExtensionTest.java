package org.kenux.tdd.mockito;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockitoJUnit5ExtensionTest {

    @Mock
    private GameNumGen genMock;
}
