package org.wade.mbus.worker.repository.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.wade.mbus.worker.repository.IValidateCodeRepository;
import org.wade.mbus.worker.repository.uitls.GraphicC6Translator;

@Repository("validateCodeRepositoryImpl")
public class ValidateCodeRepositoryImpl implements IValidateCodeRepository {
    @Override
    public String getImageText(byte[] data) {
        GraphicC6Translator translator = GraphicC6Translator.getInstance();
        return translator.translate(data);
    }



}
