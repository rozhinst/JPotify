package Network;

import java.util.ArrayList;

public interface Linker {
     ClientManager findClientManager(String clientName);
     ArrayList<ClientManager> findAllClientManagers();
}
