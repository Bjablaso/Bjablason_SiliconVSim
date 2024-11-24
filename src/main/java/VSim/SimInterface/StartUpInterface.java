package VSim.SimInterface;

import VSim.Core.StartUp;
import VSim.Types.EventType;

public interface StartUpInterface {
    void levelUp();
    void applyEventEffect(EventType event);
     void attack(StartUp target);
}
