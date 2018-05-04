package org.wade.mbus.worker.repository;

public interface IValidateCodeRepository {
    String getVCodeStr(byte[] data);
}
