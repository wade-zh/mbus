package org.wade.mbus.worker.repository.impl;

import org.springframework.stereotype.Repository;
import org.wade.mbus.worker.repository.IValidateCodeRepository;
import org.wade.mbus.worker.repository.uitls.GraphicC6Translator;

@Repository
public class ValidateCodeRepositoryImpl implements IValidateCodeRepository {
    @Override
    public String getVCodeStr(byte[] data) {
        GraphicC6Translator translator = GraphicC6Translator.getInstance();
        return translator.translate(data);
    }

}
