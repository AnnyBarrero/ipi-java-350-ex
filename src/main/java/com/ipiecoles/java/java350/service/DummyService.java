package com.ipiecoles.java.java350.service;

import org.hibernate.bytecode.spi.NotInstrumentedException;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class DummyService {
    private Integer doSomething(){
        throw new NotImplementedException();
    }
}
