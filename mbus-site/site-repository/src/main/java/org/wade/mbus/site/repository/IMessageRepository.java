package org.wade.mbus.site.repository;

import java.util.UUID;

public interface IMessageRepository {
    String pub(UUID uuid, Integer type, byte[] bytes);
}
