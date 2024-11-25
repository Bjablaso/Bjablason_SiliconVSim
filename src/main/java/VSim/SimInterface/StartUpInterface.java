package VSim.SimInterface;

import VSim.Core.StartUp;
import VSim.Types.EventType;
import VSim.Types.Quarter;

public interface StartUpInterface {
    void levelUp();
    void applyEventEffect(Quarter event);
     void attack(StartUp target);
}
